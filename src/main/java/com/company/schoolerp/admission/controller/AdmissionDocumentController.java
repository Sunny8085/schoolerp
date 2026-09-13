package com.company.schoolerp.admission.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.company.schoolerp.admission.dto.AdmissionDocumentRequest;
import com.company.schoolerp.admission.entity.AdmissionDocument;
import com.company.schoolerp.admission.service.AdmissionDocumentService;
import com.company.schoolerp.common.RestApiResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("api/v1/adm-doc")
@Tag( name = "Admission Document", description = "APIs for managing admission documents" )
public class AdmissionDocumentController {
	
	private final AdmissionDocumentService admService;
	
	@GetMapping("/{id}")
	@Operation( summary = "Get admission document by ID", description = "Fetch an admission document using its document ID" )
	public ResponseEntity<RestApiResponse<AdmissionDocument>> getDocument(@PathVariable Long id){
		log.info("Fetching admission document, id={}", id);
		
		return ResponseEntity.ok(new RestApiResponse<>(true, admService.getDocument(id)));
	}
	
	@GetMapping("/application-no/{applicationId}")
	@Operation( summary = "Get documents by application", description = "Fetch all admission documents associated with an application" )
	public ResponseEntity<RestApiResponse<List<AdmissionDocument>>> getDocumentByApplication(@PathVariable Long applicationId){
		log.info( "Fetching admission documents for applicationId={}", applicationId );
		
		return ResponseEntity.ok(new RestApiResponse<>(true, admService.getDocumentByApplication(applicationId)));
	}
	
	@PostMapping
	@Operation( summary = "Upload admission document", description = "Upload a document for an admission application" )
	public ResponseEntity<RestApiResponse<AdmissionDocument>> addDocument(
			@Valid @ModelAttribute AdmissionDocumentRequest admDoc){
		log.info( "Uploading admission document, documentType={}", admDoc.documentType() );
		
		return new ResponseEntity<>(new RestApiResponse<>(true, admService.addDocument(admDoc)), HttpStatus.CREATED);
	}
	
	@PutMapping
	@Operation( summary = "Update document verification status", description = "Update the verification status of an admission document" )
	public ResponseEntity<RestApiResponse<AdmissionDocument>> updateDocStatus(
			@RequestParam Long id,
			@RequestParam String status,
			@RequestParam String verificationRemarks){
		log.info( "Updating admission document status, id={}, status={}", id, status );
		
		return ResponseEntity.ok(new RestApiResponse<>(true, admService.updateDocStatus(id, status, verificationRemarks)));
	}
	
	@DeleteMapping("/{id}")
	@Operation( summary = "Delete admission document", description = "Delete an admission document using its ID" )
	public ResponseEntity<RestApiResponse<String>> deleteDocument(@PathVariable Long id){
		log.info("Deleting admission document, id={}", id);
		
		return ResponseEntity.ok(new RestApiResponse<>(true, admService.deleteDocument(id)));
	}
	
}





