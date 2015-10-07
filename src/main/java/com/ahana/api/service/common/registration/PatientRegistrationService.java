package com.ahana.api.service.common.registration;

import org.springframework.validation.BindingResult;

import com.ahana.api.domain.common.PatientRegistration;
import com.ahana.api.system.security.exception.AhanaBusinessException;

public interface PatientRegistrationService {

	String savePatient(PatientRegistration patientRegistration,BindingResult result);

	String loadLookups();

	String populateState(String countryId) throws AhanaBusinessException;

}
