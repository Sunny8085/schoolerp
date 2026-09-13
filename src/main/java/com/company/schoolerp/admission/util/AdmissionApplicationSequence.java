package com.company.schoolerp.admission.util;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class AdmissionApplicationSequence {
	
	private final EntityManager entityManager;
	
    /**
     * Generates a unique admission application number.
     * @return generated unique application number
     */
	public String generateApplicationNo() {

	    Long sequenceNo = ((Number) entityManager.createNativeQuery(
	                    "SELECT nextval('admission_application_seq')")
	            .getSingleResult()).longValue();

	    LocalDate today = LocalDate.now();

	    String yearMonth = String.format("%02d%02d", 
	            today.getYear() % 100,
	            today.getMonthValue()
	    );
	    String applicationNo = String.format("%s%05d", yearMonth, sequenceNo);
	    
	    log.debug("Generated admission application number: {}", applicationNo);
	    return applicationNo;
	}
	
}
