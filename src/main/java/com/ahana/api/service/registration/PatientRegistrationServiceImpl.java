package com.ahana.api.service.registration;

import java.util.Map;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ahana.api.common.BaseService;
import com.ahana.api.domain.common.PatientRegistration;
import com.ahana.api.manager.registration.PatientRegistrationManager;
import com.ahana.api.system.security.exception.AhanaBusinessException;

@Controller
@RequestMapping("/services/rest/secure/registration")
public class PatientRegistrationServiceImpl extends BaseService implements PatientRegistrationService {

	private static Logger logger = Logger.getLogger(PatientRegistrationServiceImpl.class);

	@Autowired
	private PatientRegistrationManager patientRegistrationManager;
	
	@Override
	@RequestMapping(value = { "/savePatient" }, method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> savePatient(@RequestBody @Valid PatientRegistration patientRegistration) throws AhanaBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("savePatient----start--->"	+ System.currentTimeMillis());
		}
		patientRegistration = patientRegistrationManager.savePatient(patientRegistration);
		if (logger.isDebugEnabled()) {
			logger.debug("savePatient: Success");
		}
		return handleSuccess("patientRegistration",patientRegistration);
	}
}