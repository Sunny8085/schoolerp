package com.company.schoolerp.admission.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.schoolerp.admission.service.AdmissionNotificationsService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/adm-notify")
@Tag(name = "AdmissionNotifications", description = "APIs for managing AdmissionNotifications")
public class AdmissionNotificationsController {
	
	private final AdmissionNotificationsService admNotify;
	
	
	
}
