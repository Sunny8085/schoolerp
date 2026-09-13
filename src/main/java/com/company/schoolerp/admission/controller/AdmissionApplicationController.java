package com.company.schoolerp.admission.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.company.schoolerp.admission.dto.AdmissionApplicationRequest;
import com.company.schoolerp.admission.entity.AdmissionApplication;
import com.company.schoolerp.admission.service.AdmissionApplicationService;
import com.company.schoolerp.common.RestApiResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("api/v1/application")
@Tag(name = "Admission Application", description = "APIs for managing admission applications")
public class AdmissionApplicationController {
	
	private final AdmissionApplicationService appService;
	
	@GetMapping("{id}")
	@Operation(summary = "Get admission application by ID",
	        description = "Retrieves an admission application using its database ID")
	public ResponseEntity<RestApiResponse<AdmissionApplication>> getApplication(@PathVariable Long id){
		log.info("Fetching admission application by id: {}", id);
		
		return ResponseEntity.ok(new RestApiResponse<>(true, appService.getApplication(id)));
	}
	
	@GetMapping("/by-app/{applicationNo}")
	@Operation(summary = "Get admission application by application number",
	        description = "Retrieves an admission application using the generated application number")
	public ResponseEntity<RestApiResponse<AdmissionApplication>> getApplicationNo(@PathVariable String applicationNo){
		log.info("Fetching admission application by application number: {}", applicationNo);
		
		return ResponseEntity.ok(new RestApiResponse<>(true, appService.getApplicationNo(applicationNo)));
	}
	
	@GetMapping("/all-status")
    @Operation(summary = "Get admission application statuses", description = "Returns all available admission and payment statuses")
	public ResponseEntity<RestApiResponse<Map<String, List<String>>>> getStatus(){
		log.info("Fetching admission application statuses");
		
		return ResponseEntity.ok(new RestApiResponse<>(true, appService.getStatus()));
	}
	
	@PostMapping
	@Operation(summary = "Create admission application", description = "Creates a new admission application")
	public ResponseEntity<RestApiResponse<AdmissionApplication>> addApplication(
			@Valid @RequestBody AdmissionApplicationRequest applicationRequest){
		log.info("Creating new admission application for student: {} {}", applicationRequest.firstName(),applicationRequest.lastName());

		return new ResponseEntity<>(new RestApiResponse<>(true, appService.addApplication(applicationRequest)), HttpStatus.CREATED);
	}
	
	@PutMapping("{id}")
	@Operation(summary = "Update admission application", description = "Updates admission application details using application ID")
	public ResponseEntity<RestApiResponse<AdmissionApplication>> updateApplication(
			@PathVariable Long id,
			@Valid @RequestBody AdmissionApplicationRequest applicationRequest){
		 log.info("Updating admission application, id: {}", id);
		 
		return ResponseEntity.ok(new RestApiResponse<>(true, appService.updateApplication(id, applicationRequest)));
	}
	
	@PutMapping("/app-status")
	@Operation(summary = "Update admission application status",
		        description = "Updates the workflow status, remarks and rejection reason of an admission application")
	public ResponseEntity<RestApiResponse<AdmissionApplication>> updateApplicationStatus(
			@RequestParam Long id,
			@RequestParam String status,
			@RequestParam String remarks,
			@RequestParam String rejectionReason){
		log.info("Updating application status, id: {}, status: {}", id, status);

		return ResponseEntity.ok(new RestApiResponse<>(true, appService.updateApplicationStatus(id, status, remarks, rejectionReason)));
	}
	
	@PutMapping("/adm-fee")
    @Operation(summary = "Set admission fee", description = "Sets the admission fee for an admission application")
	public ResponseEntity<RestApiResponse<AdmissionApplication>> admissionFee(
			@RequestParam Long id,
			@RequestParam BigDecimal admissionFee){
		log.info("Updating admission fee, id: {}, amount: {}", id, admissionFee);
		
		return ResponseEntity.ok(new RestApiResponse<>(true, appService.admissionFee(id, admissionFee)));
	}
	
	@PutMapping("/app-payment")
	@Operation(summary = "Update application payment status", description = "Updates the payment status of the application fee")
	public ResponseEntity<RestApiResponse<AdmissionApplication>> applicationPayment(
			@RequestParam Long id,
			@RequestParam String status){
		log.info("Updating application payment, id: {}, status: {}", id, status);
		
		return ResponseEntity.ok(new RestApiResponse<>(true, appService.applicationPayment(id, status)));
	}
	
	@PutMapping("/adm-payment")
    @Operation(summary = "Update admission payment status", description = "Updates the payment status of the admission fee")
	public ResponseEntity<RestApiResponse<AdmissionApplication>> admissionPayment(
			@RequestParam Long id,
			@RequestParam String status){
		log.info("Updating admission payment, id: {}, status: {}",id, status);
		
		return ResponseEntity.ok(new RestApiResponse<>(true, appService.admissionPayment(id, status)));
	}
	
	
}






