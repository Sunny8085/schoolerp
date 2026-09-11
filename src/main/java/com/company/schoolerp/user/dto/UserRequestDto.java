package com.company.schoolerp.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserRequestDto(
	@NotBlank(message = "Username is required")
	@Size(min = 3, max = 100, message = "Username must be between 3 and 100 characters")
	String username,

	@NotBlank(message = "Email is required")
	@Email(message = "Email should be valid")
	@Size(max = 150, message = "Email must not exceed 150 characters")
	String email,

	@Size(max = 50, message = "Phone must not exceed 50 characters")
	@Pattern(regexp = "^[0-9+\\-() ]*$", message = "Phone number format is invalid")
	String phone,

	@NotBlank(message = "Password is required")
	@Size(min = 6, max = 255, message = "Password must be between 6 and 255 characters")
	String password

) {
}
