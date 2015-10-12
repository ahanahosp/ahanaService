package com.ahana.api.manager.registration;

import com.ahana.api.domain.common.PatientRegistration;
import com.ahana.api.system.security.exception.AhanaBusinessException;

public interface PatientRegistrationManager  {

	PatientRegistration savePatient(PatientRegistration patientRegistration) throws AhanaBusinessException;

}
