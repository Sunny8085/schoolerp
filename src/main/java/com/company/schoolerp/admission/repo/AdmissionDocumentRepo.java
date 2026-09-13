package com.company.schoolerp.admission.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.company.schoolerp.admission.entity.AdmissionDocument;

public interface AdmissionDocumentRepo extends JpaRepository<AdmissionDocument, Long>{
	
	@Query("SELECT a FROM AdmissionDocument a WHERE a.application.id = :applicationId")
	List<AdmissionDocument> findByapplicationId(Long applicationId);

}
