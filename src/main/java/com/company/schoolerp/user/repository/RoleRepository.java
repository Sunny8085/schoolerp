package com.company.schoolerp.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.company.schoolerp.user.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
	
}
