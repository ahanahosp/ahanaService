package com.ahana.api.manager.common.registration;

import com.ahana.api.domain.common.PatientRegistration;
import com.ahana.api.system.security.exception.AhanaBusinessException;

public interface PatientRegistrationManager  {

	PatientRegistration saveBuyer(PatientRegistration patientRegistration)throws AhanaBusinessException;

}
