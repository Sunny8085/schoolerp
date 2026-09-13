package com.company.schoolerp.admission.controller;

import java.util.List;

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

import com.company.schoolerp.admission.dto.AdmissionCategoryRequestDto;
import com.company.schoolerp.admission.entity.AdmissionCategory;
import com.company.schoolerp.admission.service.AdmissionCategoryService;
import com.company.schoolerp.common.RestApiResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("api/v1/adm-catergory")
@Tag( name = "Admission Category", description = "APIs for managing admission categories" )
public class AdmissionCategoryController {
	
	private final AdmissionCategoryService admService;
	
	@GetMapping("/{id}")
	@Operation(summary = "Get admission category by ID", description = "Fetches an admission category using its ID" )
	public ResponseEntity<RestApiResponse<AdmissionCategory>> getCategory(@PathVariable Long id){
		log.info("Fetching admission category with id: {}", id);
		
		return ResponseEntity.ok(new RestApiResponse<>(true, admService.getCategoty(id)));
	}
	
	@GetMapping("/notification/{notificationId}")
	@Operation( summary = "Get admission categories by notification", description = "Fetches all admission categories associated with a notification" )
	public ResponseEntity<RestApiResponse<List<AdmissionCategory>>> getNotification(@PathVariable Long notificationId){
		log.info( "Fetching admission categories for notificationId: {}", notificationId );
		
		return ResponseEntity.ok(new RestApiResponse<>(true, admService.getNotification(notificationId)));
	}
	
	@PostMapping
	@Operation( summary = "Create admission category", description = "Creates a new admission category" )
	public ResponseEntity<RestApiResponse<AdmissionCategory>> addCategory(@Valid @RequestBody AdmissionCategoryRequestDto admRequest){
		log.info("Creating new admission category");
		
		return new ResponseEntity<>(new RestApiResponse<>(true, admService.addCategory(admRequest)), HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	@Operation( summary = "Delete admission category", description = "Deletes an admission category using its ID" )
	public ResponseEntity<RestApiResponse<String>> deleteCategory(@PathVariable Long id){
		log.info("Deleting admission category with id: {}", id);
		
		return ResponseEntity.ok(new RestApiResponse<>(true, admService.deleteCategory(id)));
	}
	
	@PutMapping("/{id}")
	@Operation( summary = "Update admission category", description = "Updates an existing admission category" )
	public ResponseEntity<RestApiResponse<AdmissionCategory>> updateCategory(
			@PathVariable Long id,
			@Valid @RequestBody AdmissionCategoryRequestDto admRequest){
		log.info("Updating admission category with id: {}", id);
		
		return new ResponseEntity<>(new RestApiResponse<>(true, admService.updateCategory(id, admRequest)), HttpStatus.OK);
	}
	
	@PatchMapping
	@Operation( summary = "Change admission category notification status", description = "Changes the notification status of an admission category" )
	public ResponseEntity<RestApiResponse<AdmissionCategory>> changeNotificationStatus(
			@RequestParam Long id, @RequestParam String status){
		log.info( "Changing notification status for admission category id: {} to status: {}", id, status );
		
		return ResponseEntity.ok(new RestApiResponse<>(true, admService.changeNotificationStatus(id, status)));
	}
	
}








