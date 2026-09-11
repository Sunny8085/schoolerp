package com.company.schoolerp.fee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.company.schoolerp.fee.entity.AdmissionFeeStructure;

public interface AdmissionFeeStructureRepo extends JpaRepository<AdmissionFeeStructure, Long>{

	List<AdmissionFeeStructure> findByadmCatId(String admCatId);

}
