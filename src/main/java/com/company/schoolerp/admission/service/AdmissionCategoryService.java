package com.company.schoolerp.admission.service;

import java.util.List;

import com.company.schoolerp.admission.dto.AdmissionCategoryRequestDto;
import com.company.schoolerp.admission.entity.AdmissionCategory;

public interface AdmissionCategoryService {

	AdmissionCategory getCategoty(Long id);

	List<AdmissionCategory> getNotification(Long notificationId);

	AdmissionCategory addCategory(AdmissionCategoryRequestDto admRequest);

	String deleteCategory(Long id);

	AdmissionCategory updateCategory(Long id, AdmissionCategoryRequestDto admRequest);

	AdmissionCategory changeNotificationStatus(Long id, String status);

}
