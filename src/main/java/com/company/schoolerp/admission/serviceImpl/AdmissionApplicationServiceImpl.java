package com.company.schoolerp.admission.serviceImpl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import com.company.schoolerp.admission.dto.AdmissionApplicationRequest;
import com.company.schoolerp.admission.entity.AdmissionApplication;
import com.company.schoolerp.admission.entity.AdmissionCategory;
import com.company.schoolerp.admission.enums.AdmissionStatus;
import com.company.schoolerp.admission.enums.PaymentStatusCode;
import com.company.schoolerp.admission.repo.AdmissionApplicationRepo;
import com.company.schoolerp.admission.service.AdmissionApplicationService;
import com.company.schoolerp.admission.util.AdmissionApplicationSequence;
import com.company.schoolerp.common.exception.ResourceNotFoundException;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class AdmissionApplicationServiceImpl implements AdmissionApplicationService {
	
	private final AdmissionApplicationRepo appRepo;
	private final AdmissionApplicationSequence sequence;
	private final AdmissionCategoryServiceImpl categoryService;
	
	@Override
	public AdmissionApplication getApplication(Long id) {
		log.info("Fetching Admission Application. id={}", id);
		
		return checkNotification(id);
	}

	@Override
	public AdmissionApplication getApplicationNo(String applicationNo) {
		log.info("Fetching Admission Application. applicationNo={}", applicationNo);
		
		return appRepo.findByApplicationNo(applicationNo);
	}

	@Override
	public Map<String, List<String>> getStatus() {
	    log.info("Fetching Admission Application statuses");
	    
		Map<String, List<String>> status = new HashMap<>();
		status.put("appStatus", Stream.of(AdmissionStatus.values()).map(Enum::name).toList());
		status.put("paymentStatus", Stream.of(PaymentStatusCode.values()).map(Enum::name).toList());
		
		return status;
	}

	@Override
	public AdmissionApplication addApplication(AdmissionApplicationRequest applicationRequest) {
	    log.info("Creating Admission Application. firstName={}, lastName={}, categoryId={}",
	            applicationRequest.firstName(), applicationRequest.lastName(), applicationRequest.admissionCategoryId());

		AdmissionCategory cat = categoryService.getCategoty(applicationRequest.admissionCategoryId());
		
		AdmissionApplication app = new AdmissionApplication();
		app.setApplicationNo(sequence.generateApplicationNo());
		app.setAdmissionCategory(cat);
		app = mapToModel(app, applicationRequest);
		app.setState(AdmissionStatus.APPLICATION_SUBMITTED.toString());
		app.setApplicationFee(cat.getApplicationFee());
		
	    AdmissionApplication savedApp = appRepo.save(app);
	    log.info("Admission Application created successfully. id={}, applicationNo={}",
	            savedApp.getId(), savedApp.getApplicationNo());

	    return savedApp;
	}

	@Override
	public AdmissionApplication updateApplication(Long id, AdmissionApplicationRequest applicationRequest) {
		 log.info("Updating Admission Application. id={}", id);
		 
		AdmissionApplication app = checkNotification(id);
		app = mapToModel(app, applicationRequest);
		
		return appRepo.save(app);
	}

	@Override
	public AdmissionApplication updateApplicationStatus(Long id, String status, String remarks, String rejectionReason) {
	    log.info("Updating Admission Application status. id={}, status={}", id, status);
	    
		AdmissionApplication app = checkNotification(id);
		app.setStatus(AdmissionStatus.valueOf(status).toString());
		app.setRemarks(remarks);
		app.setRejectionReason(rejectionReason);
		return appRepo.save(app);
	}

	@Override
	public AdmissionApplication admissionFee(Long id, BigDecimal admissionFee) {
	    log.info("Updating admission fee. id={}, admissionFee={}", id, admissionFee);

		AdmissionApplication app = checkNotification(id);
		app.setStatus(AdmissionStatus.ADMISSION_CONFIRMED.toString());
		app.setAdmissionFee(admissionFee);
		
		return appRepo.save(app);
	}
	
	@Override
	public AdmissionApplication applicationPayment(Long id, String status) {
	    log.info("Processing application payment. id={}, status={}", id, status);
	    
		//add payment logic
		AdmissionApplication app = checkNotification(id);
		app.setApplicationPaymentStatus(PaymentStatusCode.APPLICATION_SUCCESS.toString());
		app.setApplicationPaymentDate(LocalDateTime.now());
		app.setStatus(PaymentStatusCode.valueOf(status).toString());
		app.setAdmissionPaymentMode("Test");
		app.setApplicationPaymentReceiptNo("Test");
		app.setApplicationPaymentTransactionNo("Test");
		
		return appRepo.save(app);
	}

	@Override
	public AdmissionApplication admissionPayment(Long id, String status) {
	    log.info("Processing admission payment. id={}, status={}", id, status);
	    
	    //add payment logic
		AdmissionApplication app = checkNotification(id);
		app.setAdmissionPaymentStatus(PaymentStatusCode.valueOf(status).toString());
		app.setAdmissionPaymentDate(LocalDateTime.now());
		app.setStatus(AdmissionStatus.ADMISSION_FEE_PAID.toString());
		app.setAdmissionPaymentMode("Test");
		app.setAdmissionPaymentReceiptNo("Test");
		app.setAdmissionPaymentTransactionNo("Test");
		
		return appRepo.save(app);
	}

	
	/** Retrieves an Admission Application by ID or throws ResourceNotFoundException if not found. */
	public AdmissionApplication checkNotification(Long id) {
		log.debug("Checking Admission Application existence. id={}", id);
		
		return appRepo.findById(id)
				.orElseThrow(() -> {
					log.error( "Admission Application not found ID={}", id );
					return new ResourceNotFoundException("Admission Application not found ID: " + id);
				});
	}
	
	/**
	 * Maps the fields from the admission application request to the admission application entity.
	 * @param app the admission application entity to be populated
	 * @param applicationRequest the request containing admission application details
	 * @return the populated admission application entity
	 */
	private AdmissionApplication mapToModel(AdmissionApplication app , AdmissionApplicationRequest applicationRequest) {
		
		app.setAcademicSessionId(applicationRequest.academicSessionId());
		app.setFirstName(applicationRequest.firstName());
		app.setLastName(applicationRequest.lastName());
		app.setDateOfBirth(applicationRequest.dateOfBirth());
		app.setGender(applicationRequest.gender());
		app.setNationality(applicationRequest.nationality());
		app.setReligion(applicationRequest.religion());
		app.setCategory(applicationRequest.category());
		app.setAadhaarNo(applicationRequest.aadhaarNo());
		app.setApaarId(applicationRequest.apaarId());
		app.setAbcId(applicationRequest.abcId());
		app.setFatherName(applicationRequest.fatherName());
		app.setMotherName(applicationRequest.motherName());
		app.setGuardianName(applicationRequest.guardianName());
		app.setGuardianRelation(applicationRequest.guardianRelation());
		app.setMobile(applicationRequest.mobile());
		app.setAlternateMobile(applicationRequest.alternateMobile());
		app.setEmail(applicationRequest.email());
		app.setAddress(applicationRequest.address());
		app.setVillage(applicationRequest.village());
		app.setCity(applicationRequest.city());
		app.setDistrict(applicationRequest.district());
		app.setState(applicationRequest.state());
		app.setPincode(applicationRequest.pincode());
		app.setPreviousSchoolName(applicationRequest.previousSchoolName());
		app.setPreviousClass(applicationRequest.previousClass());
		app.setPreviousSchoolBoard(applicationRequest.previousSchoolBoard());
		app.setPreviousRollNo(applicationRequest.previousRollNo());
		app.setPreviousPercentage(applicationRequest.previousPercentage());
		
		return app;
	}

	
}
