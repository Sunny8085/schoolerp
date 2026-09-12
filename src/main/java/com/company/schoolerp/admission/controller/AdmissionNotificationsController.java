package com.company.schoolerp.admission.controller;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.company.schoolerp.admission.dto.NotificationRequestDto;
import com.company.schoolerp.admission.entity.AdmissionNotifications;
import com.company.schoolerp.admission.service.AdmissionNotificationsService;
import com.company.schoolerp.common.RestApiResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/adm-notify")
@Tag(name = "AdmissionNotifications", description = "APIs for managing AdmissionNotifications")
public class AdmissionNotificationsController {
	
	private final AdmissionNotificationsService admNotify;
	
	@GetMapping("/{id}")
    @Operation(summary = "Get admission notification by ID",
            description = "Retrieves a specific admission notification using its ID")
	public ResponseEntity<RestApiResponse<AdmissionNotifications>> getNotification(@PathVariable Long id){
		log.info("Fetching admission notification. id={}", id);
		
		return ResponseEntity.ok(new RestApiResponse<>(true, admNotify.getNotification(id)));
	}
	
	@GetMapping("/notification/{notificationNo}")
    @Operation(summary = "Get admission notification by notification number",
            description = "Retrieves admission notifications using the notification number")
	public ResponseEntity<RestApiResponse<List<AdmissionNotifications>>> getNotificationByNo(@PathVariable String notificationNo){
		log.info("Fetching admission notification by notificationNo={}", notificationNo);
		
		return ResponseEntity.ok(new RestApiResponse<>(true, admNotify.getNotificationByNo(notificationNo)));
	}
	
	@GetMapping("/all-notification")
    @Operation(summary = "Get all admission notifications",
            description = "Retrieves paginated list of all admission notifications")
	public ResponseEntity<RestApiResponse<Page<AdmissionNotifications>>> getAllNotification(
			@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size){
        log.info("Fetching all admission notifications. page={}, size={}", page, size);
        
		return ResponseEntity.ok(new RestApiResponse<>(true, admNotify.allNotification(page, size)));
	}
	
	@DeleteMapping("/{id}")
    @Operation(summary = "Delete admission notification by ID",
            description = "Deletes an admission notification using its ID")
	public ResponseEntity<RestApiResponse<String>> deleteNotification(@PathVariable Long id){
		log.info("Deleting admission notification. id={}", id);
		 
		return ResponseEntity.ok(new RestApiResponse<>(true, admNotify.deleteNotification(id)));
	}
	
	@PostMapping
    @Operation(summary = "Create admission notification",
            description = "Creates a new admission notification")
	public ResponseEntity<RestApiResponse<AdmissionNotifications>> addNotification(@Valid @RequestBody NotificationRequestDto notificationRequest){
        log.info("Creating admission notification. notificationRequest={}",notificationRequest);
        
		return new ResponseEntity<>(new RestApiResponse<>
			(true, admNotify.addNotification(notificationRequest)), HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
    @Operation(summary = "Update admission notification",
            description = "Updates an existing admission notification using its ID")
	public ResponseEntity<RestApiResponse<AdmissionNotifications>> updateNotification(
			@PathVariable Long id, 
			@Valid @RequestBody NotificationRequestDto notificationRequest){
		log.info("Updating admission notification. id={}", id);
		
		return ResponseEntity.ok(new RestApiResponse<>(true, admNotify.updateNotification(id, notificationRequest)));
	}
	
	@PatchMapping
	 @Operation(summary = "Change admission notification status",
		        description = "Updates the status of an admission notification")
	public ResponseEntity<RestApiResponse<AdmissionNotifications>> updateNotificationStatus(
			@RequestParam Long id,
			@RequestParam String status){
        log.info("Changing admission notification status. id={}, status={}", id, status);
		
		return ResponseEntity.ok(new RestApiResponse<>(true, admNotify.updateNotificationStatus(id, status)));
	}
	
	@GetMapping("/all-status")
    @Operation(summary = "Get all admission notification statuses",
            description = "Retrieves all available admission notification status values")
	public ResponseEntity<RestApiResponse<Map<String, List<String>>>> getAllStatus(){
		log.info("Fetching all admission notification statuses");
		
		return ResponseEntity.ok(new RestApiResponse<>(true, admNotify.allStatus()));
	}
	
}








