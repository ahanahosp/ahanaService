package com.ahana.api.dao.common.registration;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ahana.api.dao.common.AhanaDaoSupport;
import com.ahana.api.domain.common.PatientRegistration;

@Transactional(readOnly = false)
public class PatientRegistrationDaoImpl extends AhanaDaoSupport implements PatientRegistrationDao{

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public PatientRegistration savePatient(PatientRegistration patientRegistration) {
		saveOrUpdate(patientRegistration);
		return patientRegistration;
	}
}
