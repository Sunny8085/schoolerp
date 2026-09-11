package com.company.schoolerp.fee.serviceImpl;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.company.schoolerp.common.Status;
import com.company.schoolerp.common.exception.InvalidInputException;
import com.company.schoolerp.common.exception.ResourceNotFoundException;
import com.company.schoolerp.fee.dto.AdmissionFeeStructureRequest;
import com.company.schoolerp.fee.entity.AdmissionFeeStructure;
import com.company.schoolerp.fee.enums.AdmissionFeeType;
import com.company.schoolerp.fee.enums.LateFineType;
import com.company.schoolerp.fee.repository.AdmissionFeeStructureRepo;
import com.company.schoolerp.fee.service.AdmissionFeeStructureService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class AdmissionFeeStructureServiceImpl implements AdmissionFeeStructureService {
	
	private final AdmissionFeeStructureRepo admFeeRepo;

	@Override
	public List<AdmissionFeeStructure> getAllFee() {
		log.info("Fetching all admission fee structures");
		return admFeeRepo.findAll();
	}

	@Override
	public AdmissionFeeStructure getByFeeId(Long admFeeId) {
		log.info("Fetching admission fee structure. admFeeId={}", admFeeId);
		return getFeeStructure(admFeeId);
	}

	@Override
	public List<AdmissionFeeStructure> getByCatIdFee(String admCatId) {
		log.info("Fetching admission fee structures by category. admCatId={}", admCatId);
		return admFeeRepo.findByadmCatId(admCatId);
	}

	@Override
	public String deleteByFeeId(Long admFeeId) {
		log.info("Deleting admission fee structure. admFeeId={}", admFeeId);
		
		getFeeStructure(admFeeId);
		admFeeRepo.deleteById(admFeeId);
		log.info("Admission fee structure deleted successfully. admFeeId={}", admFeeId);
		return "Record deleted successfully";
	}

	@Override
	public AdmissionFeeStructure addFeeStructure(AdmissionFeeStructureRequest admFee) {
		log.info("Creating new admission fee structure. admCatId={}, className={}, feeType={}", 
				admFee.admCatId(), admFee.className(), admFee.feeType());
		
		AdmissionFeeStructure newAdmFee = new AdmissionFeeStructure();
		newAdmFee.setAdmCatId(admFee.admCatId());
		newAdmFee.setClassName(admFee.className());
		newAdmFee.setStream(admFee.stream());
		newAdmFee.setCategory(admFee.category());
		newAdmFee.setFeeType(AdmissionFeeType.valueOf(admFee.feeType()));
		newAdmFee.setAmount(admFee.amount());
		newAdmFee.setLateFineType(LateFineType.valueOf(admFee.lateFineType()));
		newAdmFee.setLateFineAmount(admFee.lateFineAmount());
		newAdmFee.setLateFineStatus(admFee.lateFineStatus());
		newAdmFee.setStatus(Status.valueOf(admFee.status()));
		
		AdmissionFeeStructure savedFee = admFeeRepo.save(newAdmFee);
		log.info("Admission fee structure created successfully. admFeeId={}", savedFee.getAdmFeeId());
		return savedFee;
	}

	@Override
	public AdmissionFeeStructure changeFeeStructure(Long admFeeId, AdmissionFeeStructureRequest admFee) {
		log.info("Updating admission fee structure. admFeeId={}", admFeeId);
		
		AdmissionFeeStructure adm = getFeeStructure(admFeeId);
		adm.setClassName(admFee.className());
		adm.setStream(admFee.stream());
		adm.setCategory(admFee.category());
		adm.setFeeType(AdmissionFeeType.valueOf(admFee.feeType()));
		adm.setAmount(admFee.amount());
		adm.setStatus(Status.valueOf(admFee.status()));
		
		AdmissionFeeStructure updatedFee = admFeeRepo.save(adm);
		log.info("Admission fee structure updated successfully. admFeeId={}", admFeeId);
		return updatedFee;
	}

	@Override
	public AdmissionFeeStructure changeFeeStatus(Long admFeeId, String status) {
		log.info("Changing admission fee status. admFeeId={}, status={}", admFeeId, status);
		
		AdmissionFeeStructure adm = getFeeStructure(admFeeId);
		adm.setStatus(Status.valueOf(status));
		
		AdmissionFeeStructure updatedFee = admFeeRepo.save(adm); 
		log.info("Admission fee status changed successfully. admFeeId={}, status={}", admFeeId, updatedFee.getStatus());
		return updatedFee;
	}

	@Override
	public AdmissionFeeStructure changeLateFeeStructure(Long admFeeId, AdmissionFeeStructureRequest admFee) {
		log.info("Updating late fee structure. admFeeId={}, lateFineAmount={}", admFeeId, admFee.lateFineAmount());
		
		if(admFee.lateFineAmount().compareTo(BigDecimal.ZERO) > 0) {
			AdmissionFeeStructure adm = getFeeStructure(admFeeId);
			adm.setLateFineType(LateFineType.valueOf(admFee.lateFineType()));
			adm.setLateFineStatus(admFee.lateFineStatus());
			adm.setLateFineAmount(admFee.lateFineAmount());
			
			AdmissionFeeStructure updatedFee = admFeeRepo.save(adm);
			log.info("Late fee structure updated successfully. admFeeId={}", admFeeId);
			return updatedFee;
		}else {
			throw new InvalidInputException("Input are Invalid.");
		}
	}

	@Override
	public Map<String, List<String>> getAllStatus() {
		log.debug("Fetching admission fee status and type options");
		
		Map<String, List<String>> admissionFeeStatus = new LinkedHashMap<>();
		List<String> statusList = Arrays.stream(Status.values()).map(Enum::name).toList();
		admissionFeeStatus.put("status", statusList);
		List<String> admStatus = Arrays.stream(AdmissionFeeType.values()).map(Enum::name).toList();
		admissionFeeStatus.put("admissionFeeStatus", admStatus);
		List<String> lateFeeType = Arrays.stream(LateFineType.values()).map(Enum::name).toList();
		admissionFeeStatus.put("lateFeeType", lateFeeType);
		
		log.debug("Admission fee status and type options fetched successfully");
		return admissionFeeStatus;
	}
	
	/**
	 * Check Admission Fee By ID from AdmissionFeeStructure table
	 */
	private AdmissionFeeStructure getFeeStructure(Long admFeeId) {
		AdmissionFeeStructure admfee = admFeeRepo.findById(admFeeId)
				.orElseThrow(() -> {
					log.error( "Admission fee structure not found. admFeeId={}", admFeeId );
					return new ResourceNotFoundException("Fee structure not found ID: " + admFeeId);
				});
		return admfee;
	}
	
}
