package com.company.schoolerp.admission.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.company.schoolerp.admission.dto.AdmissionCategoryRequestDto;
import com.company.schoolerp.admission.entity.AdmissionCategory;
import com.company.schoolerp.admission.repo.AdmissionCategoryRepo;
import com.company.schoolerp.admission.service.AdmissionCategoryService;
import com.company.schoolerp.common.Status;
import com.company.schoolerp.common.exception.ResourceNotFoundException;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class AdmissionCategoryServiceImpl implements AdmissionCategoryService {
	
	private final AdmissionCategoryRepo admRepo;
	private final AdmissionNotificationsServiceImpl admNservice;
	
	@Override
	public AdmissionCategory getCategoty(Long id) {
		log.info("Fetching admission category. id={}", id);
		
		return checkAdmissionCategory(id);
	}

	@Override
	public List<AdmissionCategory> getNotification(Long notificationId) {
		log.info( "Fetching admission categories by notification. notificationId={}", notificationId );
		
		return admRepo.findByNotification(notificationId);
	}

	@Override
	public AdmissionCategory addCategory(AdmissionCategoryRequestDto admRequest) {
		log.info( "Creating admission category. notificationId={}, className={}, stream={}", admRequest.notificationId(), admRequest.className(), admRequest.stream() );
		
		AdmissionCategory adm = new AdmissionCategory();
		adm.setNotification(admNservice.checkNotification(admRequest.notificationId()));
		adm.setClassName(admRequest.className());
		adm.setStream(admRequest.stream());
		adm.setTotalSeats(admRequest.totalSeats());
		adm.setApplicationFee(admRequest.applicationFee());
		adm.setStatus(Status.ACTIVE);
		
		AdmissionCategory savedCategory = admRepo.save(adm); 
		log.info( "Admission category created successfully. id={}", savedCategory.getId());
		return savedCategory;
	}

	@Override
	public String deleteCategory(Long id) {
		log.info("Deleting admission category. id={}", id);
		
		Boolean status = admRepo.existsById(id);
		if(status) {
			admRepo.deleteById(id);
			
			log.info( "Admission category deleted successfully. id={}", id );
			return "Category Deleted";
		}
		
		log.warn( "Cannot delete admission category. Category not found. id={}", id );
		throw new ResourceNotFoundException("Category not found by ID: " + id);
	}

	@Override
	public AdmissionCategory updateCategory(Long id, AdmissionCategoryRequestDto admRequest) {
		log.info("Updating admission category. id={}", id);
		
		AdmissionCategory cat = checkAdmissionCategory(id);
		cat.setClassName(admRequest.className());
		cat.setStream(admRequest.stream());
		cat.setTotalSeats(admRequest.totalSeats());
		cat.setApplicationFee(admRequest.applicationFee());
		
		AdmissionCategory updatedCategory = admRepo.save(cat); 
		log.info( "Admission category updated successfully. id={}", updatedCategory.getId() ); 
		
		return updatedCategory;
	}

	@Override
	public AdmissionCategory changeNotificationStatus(Long id, String status) {
		log.info( "Changing admission category status. id={}, status={}", id, status );
		
		AdmissionCategory cat = checkAdmissionCategory(id);
		cat.setStatus(Status.valueOf(status));
		
		return admRepo.save(cat);
	}
	
	/** Retrieves an AdmissionCategory by ID or throws ResourceNotFoundException if not found. */
	public AdmissionCategory checkAdmissionCategory(Long id) {
		log.debug("Checking admission existence. id={}", id);
		
		return admRepo.findById(id)
				.orElseThrow(() -> {
					log.error( "AdmissionCategory not found ID={}", id );
					return new ResourceNotFoundException("AdmissionCategory not found ID: " + id);
				});
	}

}
