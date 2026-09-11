package com.company.schoolerp.user.dto;

import lombok.Builder;

@Builder
public record UserResponseDto(
	String username,
	String email,
	String phone,
	String status,
	RolesDto role
) {
}
