package com.ahana.api.manager.common.registration;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ahana.api.common.ErrorConstants;
import com.ahana.api.dao.common.registration.PatientRegistrationDao;
import com.ahana.api.domain.common.PatientRegistration;
import com.ahana.api.system.security.exception.AhanaBusinessException;

@Transactional(propagation = Propagation.REQUIRED)
public class PatientRegistrationManagerImpl implements PatientRegistrationManager{
	
	private static Logger logger = Logger.getLogger(PatientRegistrationManagerImpl.class);
	
	@Autowired
	private PatientRegistrationDao patientRegistrationDao;
	
	@Override
	public PatientRegistration saveBuyer(PatientRegistration patientRegistration) throws AhanaBusinessException {
		if(patientRegistration==null){
			throw new AhanaBusinessException(ErrorConstants.NO_RECORDS_FOUND);
		}
		if(patientRegistration.isSameAsCurrentAddress()){
			patientRegistration.setPermanentAddress(patientRegistration.getAddress());
			patientRegistration.setPermanentCountry(patientRegistration.getCountry());
			patientRegistration.setPermanentState(patientRegistration.getState());
			patientRegistration.setPermanentCity(patientRegistration.getCity());
			patientRegistration.setPermanentZip(patientRegistration.getZip());
		}
		logger.debug("saveBuyer: call for save buyer starts");
		patientRegistrationDao.savePatient(patientRegistration);
		logger.debug("saveBuyer: buyer saved successfully");
		return patientRegistration;
	} 

}