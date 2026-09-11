package com.company.schoolerp.user.service;

import java.util.List;

import com.company.schoolerp.user.dto.RolesDto;

public interface RoleService {

	RolesDto getRoleById(String id);

	List<RolesDto> getAllRoles();

}
