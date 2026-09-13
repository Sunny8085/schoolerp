package com.company.schoolerp.admission.service;

import java.util.List;

import com.company.schoolerp.admission.dto.AdmissionDocumentRequest;
import com.company.schoolerp.admission.entity.AdmissionDocument;

public interface AdmissionDocumentService {

	AdmissionDocument getDocument(Long id);

	List<AdmissionDocument> getDocumentByApplication(Long applicationId);

	AdmissionDocument addDocument(AdmissionDocumentRequest admDoc);

	AdmissionDocument updateDocStatus(Long applicationId, String status, String verificationRemarks);

	String deleteDocument(Long id);
	
	

}
