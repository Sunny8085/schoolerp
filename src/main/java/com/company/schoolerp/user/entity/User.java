package com.company.schoolerp.user.entity;

import java.time.LocalDateTime;

import com.company.schoolerp.common.Auditable;
import com.company.schoolerp.common.Status;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
public class User extends Auditable{

	@Id
	@Column(length = 100)
	private String username;

	@Column(length = 150, unique = true, nullable = false)
	private String email;

	@Column(length = 50, unique = true, nullable = false)
	private String phone;

	@Column(name = "password_hash", nullable = false, length = 255)
	private String passwordHash;
	
	private String acessID;
	
	private String otp;
	
	@Column(name="otp_time")
	private LocalDateTime otpTime;
	
	@Column(length = 20)
	@Builder.Default
	@Enumerated(EnumType.STRING)
	private Status status = Status.PENDING;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name ="role_id", referencedColumnName = "role_id")
	private Role role;

}
