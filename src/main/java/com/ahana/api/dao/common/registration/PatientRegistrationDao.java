package com.ahana.api.dao.common.registration;

import com.ahana.api.domain.common.PatientRegistration;
import com.ahana.api.domain.common.QuickRegistration;

public interface PatientRegistrationDao {

	PatientRegistration savePatient(PatientRegistration patientRegistration);

	QuickRegistration saveQuickRegistration(QuickRegistration quickRegistration);

}
