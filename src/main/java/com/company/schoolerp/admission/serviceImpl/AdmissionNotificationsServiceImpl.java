package com.company.schoolerp.admission.serviceImpl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.company.schoolerp.admission.dto.NotificationRequestDto;
import com.company.schoolerp.admission.entity.AdmissionNotifications;
import com.company.schoolerp.admission.repo.AdmissionNotificationsRepo;
import com.company.schoolerp.admission.service.AdmissionNotificationsService;
import com.company.schoolerp.common.Status;
import com.company.schoolerp.common.exception.ResourceNotFoundException;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Validated
@AllArgsConstructor
public class AdmissionNotificationsServiceImpl implements AdmissionNotificationsService{
	
	private final AdmissionNotificationsRepo admNotifyRepo;
	
	@Override
	public AdmissionNotifications getNotification(Long id) {
		log.info("Fetching admission notification. id={}", id);
		
		return checkNotification(id);
	}

	@Override
	public List<AdmissionNotifications> getNotificationByNo(String notificationNo) {
		log.info( "Fetching admission notifications by notificationNo={}", notificationNo );
		
		return admNotifyRepo.findBynotificationNo(notificationNo);
	}

	@Override
	public Page<AdmissionNotifications> allNotification(int page, int size) {
		log.info( "Fetching admission notifications. page={}, size={}", page, size );
		
		Pageable pageable = PageRequest.of(page, size,
                Sort.by("createdDate").descending());
		return admNotifyRepo.findAll(pageable);
	}

	@Override
	public String deleteNotification(Long id) {
		log.info("Deleting admission notification. id={}", id);
		
		Boolean status = admNotifyRepo.existsById(id);
		if(!status) {
			throw new ResourceNotFoundException("Notification not found");
		}
		admNotifyRepo.deleteById(id);
		return "Notification deleted";
	}

	@Override
	public AdmissionNotifications addNotification(NotificationRequestDto notificationRequest) {
		log.info("Creating admission notification. notificationNo={}", notificationRequest.notificationNo());
		
		AdmissionNotifications adm = new AdmissionNotifications();
		adm.setNotificationNo(notificationRequest.notificationNo());
		adm.setTitle(notificationRequest.title());
		adm.setDescription(notificationRequest.description());
		adm.setApplicationStartDate(notificationRequest.applicationStartDate());
		adm.setApplicationEndDate(notificationRequest.applicationEndDate());
		adm.setStatus(Status.ACTIVE);
		adm.setSession(notificationRequest.notificationNo());
		
		AdmissionNotifications savedNotification = admNotifyRepo.save(adm);
		log.info( "Admission notification created successfully. id={}, notificationNo={}", savedNotification.getId(), savedNotification.getNotificationNo() );
		
		return savedNotification;
	}

	@Override
	public AdmissionNotifications updateNotification(Long id, NotificationRequestDto notificationRequest) {
		log.info("Updating admission notification. id={}", id);
		
		AdmissionNotifications adm = checkNotification(id);
		adm.setTitle(notificationRequest.title());
		adm.setDescription(notificationRequest.description());
		adm.setApplicationStartDate(notificationRequest.applicationStartDate());
		adm.setApplicationEndDate(notificationRequest.applicationEndDate());
		adm.setSession(notificationRequest.session());
		
		AdmissionNotifications updatedNotification = admNotifyRepo.save(adm);
		
		log.info( "Admission notification updated successfully. id={}", id );
		
		return updatedNotification;
	}

	@Override
	public AdmissionNotifications updateNotificationStatus(Long id, String status) {
		log.info( "Updating admission notification status. id={}, status={}", id, status );
		
		AdmissionNotifications admNoti = checkNotification(id);
		admNoti.setStatus(Status.valueOf(status));
		
		return admNotifyRepo.save(admNoti);
	}

	@Override
	public Map<String, List<String>> allStatus() {
		log.info("Fetching all admission notification statuses");
		
		Map<String, List<String>> notificationStatus = new LinkedHashMap<>();
		notificationStatus.put("status", Stream.of(Status.values()).map(Status::name).toList());
		return notificationStatus;
	}
	
	
	/** Retrieves an admission notification by ID or throws ResourceNotFoundException if not found. */
	public AdmissionNotifications checkNotification(Long id) {
		log.debug("Checking admission notification existence. id={}", id);
		
		return admNotifyRepo.findById(id)
				.orElseThrow(() -> {
					log.error( "Admission Notification not found ID={}", id );
					return new ResourceNotFoundException("Admission Notification not found ID: " + id);
				});
	}
}
