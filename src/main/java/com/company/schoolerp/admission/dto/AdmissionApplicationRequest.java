package com.company.schoolerp.admission.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record AdmissionApplicationRequest(

        @NotNull(message = "Admission category is required")
        Long admissionCategoryId,

        @NotNull(message = "Academic session is required")
        Long academicSessionId,

        @NotBlank(message = "First name is required")
        String firstName,

        @NotBlank(message = "Last name is required")
        String lastName,

        @NotNull(message = "Date of birth is required")
        String dateOfBirth,

        @NotBlank(message = "Gender is required")
        String gender,

        @Size(max = 50, message = "Nationality must not exceed 50 characters")
        String nationality,

        @Size(max = 50, message = "Religion must not exceed 50 characters")
        String religion,

        @Size(max = 50, message = "Category must not exceed 50 characters")
        String category,

        @Pattern(regexp = "^[0-9]{12}$", message = "Aadhaar number must contain exactly 12 digits")
        String aadhaarNo,

        @Size(max = 20, message = "APAAR ID must not exceed 20 characters")
        String apaarId,

        @Size(max = 20, message = "ABC ID must not exceed 20 characters")
        String abcId,

        @Size(max = 100, message = "Father name must not exceed 100 characters")
        String fatherName,

        @Size(max = 100, message = "Mother name must not exceed 100 characters")
        String motherName,

        @Size(max = 100, message = "Guardian name must not exceed 100 characters")
        String guardianName,

        @Size(max = 50, message = "Guardian relation must not exceed 50 characters")
        String guardianRelation,

        @NotBlank(message = "Mobile number is required")
        @Pattern(regexp = "^[6-9][0-9]{9}$",message = "Mobile number must be a valid 10 digit Indian mobile number")
        String mobile,

        @Pattern(regexp = "^[6-9][0-9]{9}$", message = "Alternate mobile number must be a valid 10 digit Indian mobile number")
        String alternateMobile,

        @Email(message = "Email must be valid")
        String email,

        @Size(max = 500, message = "Address must not exceed 500 characters")
        String address,

        @Size(max = 100, message = "Village must not exceed 100 characters")
        String village,

        @Size(max = 100, message = "City must not exceed 100 characters")
        String city,

        @Size(max = 100, message = "District must not exceed 100 characters")
        String district,

        @Size(max = 100, message = "State must not exceed 100 characters")
        String state,

        @NotBlank(message = "Mobile number is required")
        String pincode,

        @Size(max = 100, message = "Previous school name must not exceed 100 characters")
        String previousSchoolName,

        @Size(max = 50, message = "Previous class must not exceed 50 characters")
        String previousClass,

        @Size(max = 50, message = "Previous school board must not exceed 50 characters")
        String previousSchoolBoard,

        @Size(max = 50, message = "Previous roll number must not exceed 50 characters")
        String previousRollNo,

        @DecimalMin(value = "0.0", message = "Percentage cannot be negative")
        @DecimalMax(value = "100.0", message = "Percentage cannot exceed 100")
        BigDecimal previousPercentage

) {
}