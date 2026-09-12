package com.company.schoolerp.admission.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.company.schoolerp.admission.dto.NotificationRequestDto;
import com.company.schoolerp.admission.entity.AdmissionNotifications;

public interface AdmissionNotificationsService {

	AdmissionNotifications getNotification(Long id);

	List<AdmissionNotifications> getNotificationByNo(String notificationNo);

	Page<AdmissionNotifications> allNotification(int page, int size);

	String deleteNotification(Long id);

	AdmissionNotifications addNotification(NotificationRequestDto notificationRequest);

	AdmissionNotifications updateNotification(Long id, NotificationRequestDto notificationRequest);

	AdmissionNotifications updateNotificationStatus(Long id, String status);

	Map<String, List<String>> allStatus();

}
