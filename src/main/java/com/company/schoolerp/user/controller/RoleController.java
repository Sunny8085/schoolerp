package com.company.schoolerp.user.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.schoolerp.common.RestApiResponse;
import com.company.schoolerp.user.dto.RolesDto;
import com.company.schoolerp.user.service.RoleService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/roles")
@Tag(name = "Roles", description = "APIs for managing roles")
public class RoleController {

	private final RoleService roleService;

	@GetMapping("/{id}")
	@Operation(summary = "Get role by ID", description = "Retrieves a role by its unique identifier with all associated permissions")
	public ResponseEntity<RestApiResponse<RolesDto>> getRoleById(@PathVariable String id) {
		log.info("Received request to fetch role. roleId={}", id);
		RolesDto roleDto = roleService.getRoleById(id);
		return ResponseEntity.ok(new RestApiResponse<>(true, roleDto));
	}

	@GetMapping
	@Operation(summary = "Get all roles", description = "Retrieves all roles with pagination support")
	public ResponseEntity<RestApiResponse<List<RolesDto>>> getAllRoles() {
		log.info( "Received request to fetch all roles.");
		List<RolesDto> roles = roleService.getAllRoles();
		return ResponseEntity.ok(new RestApiResponse<>(true, roles));
	}

}
