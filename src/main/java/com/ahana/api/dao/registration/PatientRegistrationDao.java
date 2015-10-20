package com.ahana.api.dao.registration;

import java.util.List;
import java.util.Map;

import com.ahana.api.domain.common.PatientRegistration;

public interface PatientRegistrationDao {

	PatientRegistration savePatient(PatientRegistration patientRegistration);

	List<Map<String, String>> searchPatientByName(String name);

}
