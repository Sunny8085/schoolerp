package com.company.schoolerp.admission.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.company.schoolerp.admission.entity.AdmissionNotifications;

public interface AdmissionNotificationsRepo extends JpaRepository<AdmissionNotifications, Long>{

	List<AdmissionNotifications> findBynotificationNo(String notificationNo);

}
