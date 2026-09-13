package com.company.schoolerp.admission.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.company.schoolerp.admission.dto.AdmissionApplicationRequest;
import com.company.schoolerp.admission.entity.AdmissionApplication;

public interface AdmissionApplicationService {

	AdmissionApplication getApplication(Long id);

	AdmissionApplication getApplicationNo(String applicationNo);

	Map<String, List<String>> getStatus();

	AdmissionApplication addApplication(AdmissionApplicationRequest applicationRequest);

	AdmissionApplication updateApplication(Long id, AdmissionApplicationRequest applicationRequest);

	AdmissionApplication updateApplicationStatus(Long id, String status, String remarks, String rejectionReason);

	AdmissionApplication admissionFee(Long id, BigDecimal admissionFee);

	AdmissionApplication applicationPayment(Long id, String status);

	AdmissionApplication admissionPayment(Long id, String status);
	
	
	
}
