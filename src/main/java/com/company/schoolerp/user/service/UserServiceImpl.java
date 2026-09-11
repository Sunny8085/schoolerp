package com.company.schoolerp.user.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.company.schoolerp.common.Status;
import com.company.schoolerp.common.exception.DuplicateResourceException;
import com.company.schoolerp.common.exception.InvalidInputException;
import com.company.schoolerp.common.exception.ResourceNotFoundException;
import com.company.schoolerp.common.util.OtpUtil;
import com.company.schoolerp.user.dto.Roles;
import com.company.schoolerp.user.dto.RolesDto;
import com.company.schoolerp.user.dto.UserRequestDto;
import com.company.schoolerp.user.dto.UserResponseDto;
import com.company.schoolerp.user.entity.Role;
import com.company.schoolerp.user.entity.User;
import com.company.schoolerp.user.repository.RoleRepository;
import com.company.schoolerp.user.repository.UserRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{

	private final UserRepository userRepository;
	private final RoleRepository roleRepo;

	@Override
	public UserResponseDto createUser(UserRequestDto requestDto) {
		log.info("Creating new user. username={}", requestDto.username());

		if (userRepository.existsById(requestDto.username())) {
			log.error( "User creation rejected because username already exists. username={}", requestDto.username());
			
			throw new DuplicateResourceException("Username '" + requestDto.username() + "' already exists");
		}
		Role role = roleRepo.findById(Roles.GUEST.getCode())
				.orElseThrow(() -> {
					log.error( "Default GUEST role is not configured. roleId={}", Roles.GUEST.getCode() );
					return new ResourceNotFoundException("Role not found.");
					});
		
		User user = User.builder()
			.username(requestDto.username())
			.email(requestDto.email())
			.phone(requestDto.phone())
			.passwordHash(requestDto.password())
			.role(role)
			.build();
		
		User saved = userRepository.save(user);
		
		log.info( "User created successfully. username={}, role={}", saved.getUsername(), role.getRoleName() );
		return mapToResponseDto(saved);
	}
	
	@Override
	public UserResponseDto getUserByUsername(String username) {
		log.info("Fetching user. username={}", username);
		
		User user = getUser(username);
		return mapToResponseDto(user);
	}
	
	@Override
	public UserResponseDto updateUser(UserRequestDto requestDto) {
		log.info("Updating user. userId={}", requestDto.username());
		
		User user = getUser(requestDto.username());
		user.setEmail(requestDto.email());
		user.setPhone(requestDto.phone());
		User updated = userRepository.save(user);
		
		log.info("User updated successfully. userId={}", requestDto.username());
		return mapToResponseDto(updated);
	}
	
	@Override
	public UserResponseDto changePassword(String id, String newPassword) {
		log.info("Changing password. userId={}", id);
		
		User user = getUser(id);
		//password encoder logic
		user.setPasswordHash(newPassword);
		User updated = userRepository.save(user);
		
		log.info("Password changed successfully. userId={}", id);
		return mapToResponseDto(updated);
	}
	
	@Override
	public UserResponseDto updateStatus(String id, String status) {
		log.info( "Updating user status. userId={}, requestedStatus={}", id, status );
		User user = getUser(id);
		
		try {
			user.setStatus(Status.valueOf(status.toUpperCase()));
		}catch(IllegalArgumentException e) {
			log.error( "Invalid user status requested. userId={}, status={}", id, status );
			throw new InvalidInputException("Invalid user status: "+status);
		}
		User updated = userRepository.save(user);
		
		log.info( "User status updated successfully. userId={}, status={}", id, updated.getStatus() );
		return mapToResponseDto(updated);
	}
	
	@Override
	public UserResponseDto changeRole(String id, String roleId) {
		User user = getUser(id);
		log.info( "Changing user role. userId={}, roleId={}", id, roleId );
		
		Role role = roleRepo.findById(roleId)
			.orElseThrow(() -> {
				log.error( "Requested role not found. roleId={}", roleId );
				return new ResourceNotFoundException("Role not found with ID: " + roleId);
			});

		user.setRole(role);
		User updated = userRepository.save(user);
		
		log.info( "User role changed successfully. userId={}, roleId={}", id, roleId );
		return mapToResponseDto(updated);
	}
	
	@Override
	public String generateOTP(String username) {
		User user = getUser(username);
		log.info("Generating OTP. username={}", username);
		//opt send logic
		
		String otp = OtpUtil.generateOtp(4);
		user.setOtp(otp);
		user.setOtpTime(LocalDateTime.now().plusMinutes(15));
		userRepository.save(user);
		
		log.info( "OTP generated successfully. username={}, expiresInMinutes=15", username );
		return otp;
	}

	@Override
	public UserResponseDto verifyOTP(String username, String otp) {
		User user = getUser(username);
		log.info( "Verifying OTP. username={}", username );
		
		if(user.getOtp().equals(otp) && user.getOtpTime().isAfter(LocalDateTime.now())) {
			user.setStatus(Status.ACTIVE);
			log.info( "User verified successfully");
			
			return mapToResponseDto(userRepository.save(user));
		}else {
			log.error( "OTP verification failed. username={}, otp={}", username, otp);
			throw new InvalidInputException("OTP is Invalid or expired: " + otp);
		}	
	}
	
	/**
	 * Get User from user table by username
	 */
	private User getUser(String username) {
		return userRepository.findById(username)
				.orElseThrow(() -> {
				log.error( "User not found. username={}", username );
				return new ResourceNotFoundException("User not found with ID: " + username);
				});
	}
	
	/**
	 * Map User entity to UserResponseDto
	 */
	private UserResponseDto mapToResponseDto(User user) {
		RolesDto role = RolesDto.builder().roleId(user.getRole().getRoleId()).roleName(user.getRole().getRoleName()).build();
		return UserResponseDto
				.builder()
				.username(user.getUsername())
				.email(user.getEmail())
				.phone(user.getPhone())
				.status(user.getStatus().toString())
				.role(role).build();
	}

}
