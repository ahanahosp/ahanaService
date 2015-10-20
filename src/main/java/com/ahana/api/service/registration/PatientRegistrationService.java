package com.ahana.api.service.registration;

import java.util.Map;

import com.ahana.api.domain.common.PatientRegistration;
import com.ahana.api.system.security.exception.AhanaBusinessException;

public interface PatientRegistrationService {

	Map<String, Object> savePatient(PatientRegistration patientRegistration) throws AhanaBusinessException;

	Map<String, Object> searchPatientByName(String name) throws AhanaBusinessException;

}
