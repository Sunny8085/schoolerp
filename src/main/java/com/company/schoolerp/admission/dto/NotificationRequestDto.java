package com.company.schoolerp.admission.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record NotificationRequestDto(

        @NotBlank
        @Size(max = 50)
        String notificationNo,

        @NotBlank
        @Size(max = 200)
        String title,

        String description,

        @NotNull
        LocalDate applicationStartDate,

        @NotNull
        LocalDate applicationEndDate,

        @NotBlank
        @Size(max = 255)
        String session
) {
}
