package com.ahana.api.manager.registration;

import java.util.List;
import java.util.Map;

import com.ahana.api.domain.common.PatientRegistration;
import com.ahana.commons.system.security.exception.AhanaBusinessException;

public interface PatientRegistrationManager  {

	PatientRegistration savePatient(PatientRegistration patientRegistration) throws AhanaBusinessException;

	List<Map<String, String>> searchPatientByName(String name) throws AhanaBusinessException;

}
