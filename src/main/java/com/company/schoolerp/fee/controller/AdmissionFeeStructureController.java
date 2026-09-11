package com.company.schoolerp.fee.controller;

import java.util.List;
import java.util.Map;

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

import com.company.schoolerp.common.RestApiResponse;
import com.company.schoolerp.fee.dto.AdmissionFeeStructureRequest;
import com.company.schoolerp.fee.entity.AdmissionFeeStructure;
import com.company.schoolerp.fee.service.AdmissionFeeStructureService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("api/v1/admission-fee-structure")
@Tag(name = "AdmissionFeeStructure", description = "APIs for managing AdmissionFeeStructure")
public class AdmissionFeeStructureController {

	private final AdmissionFeeStructureService admFeeService;
	
	@GetMapping("/all-fee")
	@Operation(summary = "List of all AdmissionFeeStructure", description = "Retrieves all AdmissionFeeStructure")
	public ResponseEntity<RestApiResponse<List<AdmissionFeeStructure>>> getAllFee(){
		log.info("Fetching all admission fee structures");
		
		return new ResponseEntity<>(new RestApiResponse<>(true, admFeeService.getAllFee()), HttpStatus.OK);
	}
	
	@GetMapping("/{admFeeId}")
	@Operation(summary = "Get AdmissionFeeStructure By Id", description = "Get AdmissionFeeStructure By Id")
	public ResponseEntity<RestApiResponse<AdmissionFeeStructure>> getByFeeId(@PathVariable Long admFeeId){
		log.info("Fetching admission fee structure. admFeeId={}", admFeeId);
		
		return new ResponseEntity<>(new RestApiResponse<>(true, admFeeService.getByFeeId(admFeeId)), HttpStatus.OK);
	}
	
	@GetMapping("/adm-cat/{admCatId}")
	@Operation(summary = "Get AdmissionFeeStructure By Admission Category Id", 
				description = "Get AdmissionFeeStructure By Admission Category Id")
	public ResponseEntity<RestApiResponse<List<AdmissionFeeStructure>>> getByCatIdFee(@PathVariable String admCatId){
		log.info("Fetching admission fee structures by admission category. admCatId={}",admCatId);
		
		return new ResponseEntity<>(new RestApiResponse<>(true, admFeeService.getByCatIdFee(admCatId)), HttpStatus.OK);
	}
	
	@DeleteMapping("/{admFeeId}")
	@Operation(summary = "Delete AdmissionFeeStructure By ID", description = "Delete AdmissionFeeStructure By ID")
	public ResponseEntity<RestApiResponse<String>> deleteByFeeId(@PathVariable Long admFeeId){
		log.info("Deleting admission fee structure. admFeeId={}", admFeeId);
		
		return new ResponseEntity<>(new RestApiResponse<>(true, admFeeService.deleteByFeeId(admFeeId)), HttpStatus.OK);
	}
	
	@PostMapping
	@Operation(summary = "Add new AdmissionFeeStructure", description = "Add new AdmissionFeeStructure")
	public ResponseEntity<RestApiResponse<AdmissionFeeStructure>> addAdmFee(
				@Valid @RequestBody AdmissionFeeStructureRequest admFee){
		log.info("Creating new admission fee structure admFee={}" ,admFee);

		return new ResponseEntity<>(new RestApiResponse<>(true, admFeeService.addFeeStructure(admFee)), HttpStatus.CREATED);
	}
	
	@PutMapping("/change-fee/{admFeeId}")
	@Operation(summary = "Change fee structure", description = "Change Admission fee structure except late Fee or status")
	public ResponseEntity<RestApiResponse<AdmissionFeeStructure>> changeFeeStructure(
			@PathVariable Long admFeeId, @Valid @RequestBody AdmissionFeeStructureRequest admFee){
		log.info("Updating admission fee structure. admFeeId={}", admFeeId);
		
		return new ResponseEntity<>(new RestApiResponse<>
					(true, admFeeService.changeFeeStructure(admFeeId, admFee)), HttpStatus.OK);
	}
	
	@PatchMapping("/chage-status")
	@Operation(summary = "Change admission fee status", description = "Change admission fee status")
	public ResponseEntity<RestApiResponse<AdmissionFeeStructure>> changeFeeStatus(
			@RequestParam Long admFeeId, @RequestParam String status){
		log.info("Changing admission fee status. admFeeId={}, status={}", admFeeId,status);
		
		return new ResponseEntity<>(new RestApiResponse<>
					(true, admFeeService.changeFeeStatus(admFeeId, status)), HttpStatus.OK);
	}
	
	@PutMapping("/change-late-fee/{admFeeId}")
	@Operation(summary = "Change Admission late fee Structure", description = "Change Admission late fee Structure only")
	public ResponseEntity<RestApiResponse<AdmissionFeeStructure>> changeLateFeeStructure(
			@PathVariable Long admFeeId, @Valid @RequestBody AdmissionFeeStructureRequest admFee){
		log.info("Updating late fee structure. admFeeId={}", admFeeId);
		
		return new ResponseEntity<>(new RestApiResponse<>
					(true, admFeeService.changeLateFeeStructure(admFeeId, admFee)), HttpStatus.OK);
	}
	
	@GetMapping("/get-all-status")
	@Operation(summary = "Get all fee structure status", description = "Get all fee structure enums status")
	public ResponseEntity<RestApiResponse<Map<String, List<String>>>> getAllStatus(){
		log.info("Fetching all admission fee structure statuses");
		
		return new ResponseEntity<>(new RestApiResponse<>(true, admFeeService.getAllStatus()), HttpStatus.OK);
	}
	
}












