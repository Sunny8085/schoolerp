package com.company.schoolerp.admission.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record AdmissionCategoryRequestDto(
        @NotNull
        Long notificationId,

        @NotBlank
        @Size(max = 50)
        String className,

        @Size(max = 50)
        String stream,

        Integer totalSeats,

        @NotNull
        @DecimalMin(value = "0.00")
        BigDecimal applicationFee
) {}
