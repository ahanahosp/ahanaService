package com.ahana.api.dao.common.registration;

import com.ahana.api.domain.common.PatientRegistration;

public interface PatientRegistrationDao {

	PatientRegistration savePatient(PatientRegistration patientRegistration);

}
