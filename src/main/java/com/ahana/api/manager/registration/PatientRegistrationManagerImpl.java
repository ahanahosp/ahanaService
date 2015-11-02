package com.ahana.api.manager.registration;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ahana.api.dao.registration.PatientRegistrationDao;
import com.ahana.api.domain.common.PatientRegistration;
import com.ahana.commons.system.security.error.CommonErrorConstants;
import com.ahana.commons.system.security.exception.AhanaBusinessException;

@Transactional(propagation = Propagation.REQUIRED)
public class PatientRegistrationManagerImpl implements PatientRegistrationManager{
	
	private static Logger logger = Logger.getLogger(PatientRegistrationManagerImpl.class);
	
	@Autowired
	private PatientRegistrationDao patientRegistrationDao;
	
	@Override
	public PatientRegistration savePatient(PatientRegistration patientRegistration) throws AhanaBusinessException {
		if(patientRegistration==null){
			throw new AhanaBusinessException(CommonErrorConstants.NO_RECORDS_FOUND);
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

	@Override
	public List<Map<String, String>> searchPatientByName(String name) throws AhanaBusinessException {
		List<Map<String, String>> patients=patientRegistrationDao.searchPatientByName(name);
		if(CollectionUtils.isEmpty(patients)){
			throw new AhanaBusinessException(CommonErrorConstants.NO_RECORDS_FOUND);
		}
		return patients;
	}

}