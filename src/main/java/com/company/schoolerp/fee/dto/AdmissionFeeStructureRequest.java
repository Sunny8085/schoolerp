package com.company.schoolerp.fee.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AdmissionFeeStructureRequest(
		
		@NotBlank(message = "Admission category ID is required")
        String admCatId,

        String className,
        String stream,
        String category,

        @NotBlank(message = "Fee type is required")
        String feeType,
        
        @NotNull(message = "Amount is required")
        @DecimalMin(value = "0.00", inclusive = false, message = "Amount must be greater than 0")
        BigDecimal amount,

        String lateFineType,
        BigDecimal lateFineAmount,
        String lateFineStatus,

        @NotBlank(message = "Status is required")
        String status

		) {}

