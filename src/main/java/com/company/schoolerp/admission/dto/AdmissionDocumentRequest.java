package com.company.schoolerp.admission.dto;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record AdmissionDocumentRequest(
		
		@NotNull(message = "Application Id is required")
		Long applicationId,
		
        @NotBlank(message = "Document type is required")
        @Size(max = 100, message = "Document type must not exceed 100 characters")
        String documentType,

        @Size(max = 100, message = "Document number must not exceed 100 characters")
        String documentNumber,
        
        @NotNull(message = "Document file is required")
        MultipartFile file
) {
}