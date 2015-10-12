package com.ahana.api.manager.registration;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ahana.api.common.ErrorConstants;
import com.ahana.api.dao.registration.PatientRegistrationDao;
import com.ahana.api.domain.common.PatientRegistration;
import com.ahana.api.system.security.exception.AhanaBusinessException;

@Transactional(propagation = Propagation.REQUIRED)
public class PatientRegistrationManagerImpl implements PatientRegistrationManager{
	
	private static Logger logger = Logger.getLogger(PatientRegistrationManagerImpl.class);
	
	@Autowired
	private PatientRegistrationDao patientRegistrationDao;
	
	@Override
	public PatientRegistration savePatient(PatientRegistration patientRegistration) throws AhanaBusinessException {
		if(patientRegistration==null){
			throw new AhanaBusinessException(ErrorConstants.NO_RECORDS_FOUND);
		}
		if(logger.isDebugEnabled()){
			logger.debug("savePatient: Starts");
		}
		patientRegistrationDao.savePatient(patientRegistration);
		if(logger.isDebugEnabled()){
			logger.debug("savePatient: Ends");
		}
		return patientRegistration;
	}

}