package com.company.schoolerp.user.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.company.schoolerp.common.exception.ResourceNotFoundException;
import com.company.schoolerp.user.dto.RolesDto;
import com.company.schoolerp.user.entity.Role;
import com.company.schoolerp.user.repository.RoleRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService{

	private final RoleRepository roleRepository;
	
	@Override
	public RolesDto getRoleById(String id) {
		Role role = roleRepository.findById(id)
			.orElseThrow(() -> {
				log.error("Role not found. roleId={}", id);
				return new ResourceNotFoundException("Role not found with ID: " + id);
				});
		
		log.info( "Role retrieved successfully. roleId={}, roleName={}", role.getRoleId(), role.getRoleName());
		return mapToResponseDto(role);
	}

	@Override
	public List<RolesDto> getAllRoles() {
		log.info( "Fetching roles from database.");
		List<Role> roles = roleRepository.findAll();
		
		log.info("Roles retrieved successfully. count={}", roles.size());
		return roles.stream().map(this::mapToResponseDto).toList();
	}

	private RolesDto mapToResponseDto(Role role) {
		return RolesDto.builder().roleId(role.getRoleId()).roleName(role.getRoleName()).build();
	}
}
