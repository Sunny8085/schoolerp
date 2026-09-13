package com.company.schoolerp.admission.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.company.schoolerp.admission.entity.AdmissionApplication;

public interface AdmissionApplicationRepo extends JpaRepository<AdmissionApplication, Long>{

	AdmissionApplication findByApplicationNo(String applicationNo);

}
