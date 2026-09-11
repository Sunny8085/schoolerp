package com.company.schoolerp.user.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.company.schoolerp.user.dto.UserRequestDto;
import com.company.schoolerp.user.dto.UserResponseDto;
import com.company.schoolerp.user.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/users")
@Tag(name = "Users", description = "APIs for managing users")
public class UserController {

	private final UserService userService;

	@PostMapping
	@Operation(summary = "Create a new user", description = "Creates a new user with the provided credentials and role assignment")
	public ResponseEntity<RestApiResponse<UserResponseDto>> createUser(
			@Valid @RequestBody UserRequestDto requestDto) {
		log.info( "Received request to create user. username={}", requestDto.username());
		
		UserResponseDto userDto = userService.createUser(requestDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(new RestApiResponse<>(true, userDto));
	}

	@GetMapping("/username/{username}")
	@Operation(summary = "Get user by username", description = "Retrieves a user by its username")
	public ResponseEntity<RestApiResponse<UserResponseDto>> getUserByUsername(@PathVariable String username) {
		log.info("Received request to fetch user. username={}", username);
		
		UserResponseDto userDto = userService.getUserByUsername(username);
		return ResponseEntity.ok(new RestApiResponse<>(true, userDto));
	}

	@PutMapping
	@Operation(summary = "Update a user", description = "Updates an existing user with new details")
	public ResponseEntity<RestApiResponse<UserResponseDto>> updateUser(@Valid @RequestBody UserRequestDto requestDto) {
		log.info("Received request to update user. userId={}", requestDto.username());
		
		UserResponseDto userDto = userService.updateUser(requestDto);
		return ResponseEntity.ok(new RestApiResponse<>(true, userDto));
	}

	@PutMapping("/{username}/password")
	@Operation(summary = "Change user password", description = "Changes the password for an existing user")
	public ResponseEntity<RestApiResponse<UserResponseDto>> changePassword(
			@PathVariable String username, @RequestParam String newPassword) {
		log.info("Received request to change password. userId={}", username);
		
		UserResponseDto userDto = userService.changePassword(username, newPassword);
		return ResponseEntity.ok(new RestApiResponse<>(true, userDto));
	}

	@PutMapping("/{username}/status")
	@Operation(summary = "Update user status", description = "Updates the status of an existing user")
	public ResponseEntity<RestApiResponse<UserResponseDto>> updateStatus(
			@PathVariable String username, @RequestParam String status) {
		log.info( "Received request to update user status. userId={}, status={}", username, status );
		
		UserResponseDto userDto = userService.updateStatus(username, status);
		return ResponseEntity.ok(new RestApiResponse<>(true, userDto));
	}

	@PutMapping("/{username}/role/{roleId}")
	@Operation(summary = "Change user role", description = "Changes the role assignment for an existing user")
	public ResponseEntity<RestApiResponse<UserResponseDto>> changeRole(
			@PathVariable String username, @PathVariable String roleId) {
		log.info( "Received request to change user role. userId={}, roleId={}", username, roleId );
		
		UserResponseDto userDto = userService.changeRole(username, roleId);
		return ResponseEntity.ok(new RestApiResponse<>(true, userDto));
	}
	
	@GetMapping("/{username}/get-otp")
	@Operation(summary = "Generate OTP", description = "Generate OTP by Phone No.")
	public ResponseEntity<RestApiResponse<String>> getOTP(@PathVariable String username) {
		log.info("Received OTP generation request. username={}", username);
		
		return ResponseEntity.ok(new RestApiResponse<>(true, userService.generateOTP(username)));
	}
	
	@PatchMapping("/verify-user")
	@Operation(summary = "Verify User", description = "Verify User by OTP")
	public ResponseEntity<RestApiResponse<UserResponseDto>> getOTP(
			@RequestParam String username, @RequestParam String otp) {
		log.info("Received OTP verification request. username={}", username);
		
		return ResponseEntity.ok(new RestApiResponse<>(true, userService.verifyOTP(username, otp)));
	}

}
