package com.company.schoolerp.admission.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.company.schoolerp.admission.dto.AdmissionDocumentRequest;
import com.company.schoolerp.admission.entity.AdmissionApplication;
import com.company.schoolerp.admission.entity.AdmissionDocument;
import com.company.schoolerp.admission.repo.AdmissionDocumentRepo;
import com.company.schoolerp.admission.service.AdmissionDocumentService;
import com.company.schoolerp.common.Status;
import com.company.schoolerp.common.exception.ResourceNotFoundException;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class AdmissionDocumentServiceImpl implements AdmissionDocumentService {
	
	private final AdmissionDocumentRepo docRepo;
	private final AdmissionApplicationServiceImpl appService;
	
	@Override
	public AdmissionDocument getDocument(Long id) {
		log.info("Fetching admission document. id={}", id);
		
		return checkAdmissionDocument(id);
	}

	@Override
	public List<AdmissionDocument> getDocumentByApplication(Long applicationId) {
		log.info( "Fetching admission documents. applicationId={}", applicationId );
		
		return docRepo.findByapplicationId(applicationId);
	}

	@Override
	public AdmissionDocument addDocument(AdmissionDocumentRequest admDoc) {
		log.info("Adding admission document. documentType={}", admDoc.documentType());
		
		AdmissionApplication app  = appService.getApplication(admDoc.applicationId());
		log.info("Featch Student Application By ID={}", admDoc.documentType());
		
		AdmissionDocument newDoc = new AdmissionDocument();
		newDoc.setApplication(app);
		newDoc.setDocumentType(admDoc.documentType());
		newDoc.setDocumentNumber(admDoc.documentNumber());
		newDoc.setVerificationStatus(Status.PENDING.toString());
		
	    String fileName = admDoc.file().getOriginalFilename();
	    if (fileName == null || !fileName.contains(".")) {
	    		log.error( "Invalid file received. documentType={}", admDoc.documentType() );
	        throw new ResourceNotFoundException("File not found.");
	    }
	    fileName = fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
	    
		newDoc.setFileName(admDoc.documentType().toString()+fileName);
		newDoc.setFilePath("Test");
		AdmissionDocument savedDocument = docRepo.save(newDoc);
		
		log.info( "Admission document added successfully. documentId={}, documentType={}", savedDocument.getId(), savedDocument.getDocumentType() ); 
		return savedDocument;
	}

	@Override
	public AdmissionDocument updateDocStatus(Long id, String status, String verificationRemarks) {
		log.info( "Updating admission document status. id={}, status={}, remark={}", id, status, verificationRemarks);
		
		AdmissionDocument admDoc = checkAdmissionDocument(id);
		admDoc.setVerificationStatus(Status.valueOf(status).toString());
		admDoc.setVerificationRemarks(verificationRemarks);
		return docRepo.save(admDoc);
	}

	@Override
	public String deleteDocument(Long id) {
		log.info( "Deleting admission document. id={}", id );
		
		checkAdmissionDocument(id);
		docRepo.deleteById(id);
		return "Document Deleted";
	}
	
	/** Retrieves an admission notification by ID or throws ResourceNotFoundException if not found. */
	public AdmissionDocument checkAdmissionDocument(Long id) {
		log.debug("Checking Admission Document existence. id={}", id);
		
		return docRepo.findById(id)
				.orElseThrow(() -> {
					log.error( "Admission Document not found ID={}", id );
					return new ResourceNotFoundException("Admission Document not found ID: " + id);
				});
	}

}
