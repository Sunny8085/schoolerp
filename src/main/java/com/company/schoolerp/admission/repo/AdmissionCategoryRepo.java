package com.company.schoolerp.admission.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.company.schoolerp.admission.entity.AdmissionCategory;

public interface AdmissionCategoryRepo extends JpaRepository<AdmissionCategory, Long>{

	@Query("SELECT a FROM AdmissionCategory a WHERE a.notification.id = :notificationId")
	List<AdmissionCategory> findByNotification(@Param("notificationId") Long notificationId);

}
