package com.company.schoolerp.user.service;

import com.company.schoolerp.user.dto.UserRequestDto;
import com.company.schoolerp.user.dto.UserResponseDto;

public interface UserService {

	UserResponseDto createUser(UserRequestDto requestDto);

	UserResponseDto getUserByUsername(String username);

	UserResponseDto updateUser(UserRequestDto requestDto);

	UserResponseDto changePassword(String id, String newPassword);

	UserResponseDto updateStatus(String id, String status);

	UserResponseDto changeRole(String id, String roleId);

	String generateOTP(String username);

	UserResponseDto verifyOTP(String username, String otp);

}
