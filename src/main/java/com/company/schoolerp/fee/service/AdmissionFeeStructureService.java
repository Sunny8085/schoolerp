package com.company.schoolerp.fee.service;

import java.util.List;
import java.util.Map;

import com.company.schoolerp.fee.dto.AdmissionFeeStructureRequest;
import com.company.schoolerp.fee.entity.AdmissionFeeStructure;

public interface AdmissionFeeStructureService {

	public List<AdmissionFeeStructure> getAllFee();

	public AdmissionFeeStructure getByFeeId(Long admFeeId);

	public List<AdmissionFeeStructure> getByCatIdFee(String admCatId);

	public String deleteByFeeId(Long admFeeId);

	public AdmissionFeeStructure addFeeStructure(AdmissionFeeStructureRequest admFee);

	public AdmissionFeeStructure changeFeeStructure(Long admFeeId, AdmissionFeeStructureRequest admFee);

	public AdmissionFeeStructure changeFeeStatus(Long admFeeId, String status);

	public AdmissionFeeStructure changeLateFeeStructure(Long admFeeId, AdmissionFeeStructureRequest admFee);

	public Map<String, List<String>> getAllStatus();

}
