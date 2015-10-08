package com.ahana.api.service.common.registration;

import org.springframework.validation.BindingResult;

import com.ahana.api.domain.common.PatientRegistration;
import com.ahana.api.domain.common.QuickRegistration;

public interface PatientRegistrationService {

	String savePatient(PatientRegistration patientRegistration,BindingResult result);

	String saveQuickRegistration(QuickRegistration quickRegistration,BindingResult result);

}
