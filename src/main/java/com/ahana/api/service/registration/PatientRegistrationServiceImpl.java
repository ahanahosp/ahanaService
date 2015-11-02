package com.ahana.api.service.registration;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ahana.api.domain.common.PatientRegistration;
import com.ahana.api.manager.registration.PatientRegistrationManager;
import com.ahana.commons.system.security.exception.AhanaBusinessException;
import com.ahana.commons.system.service.BaseService;

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
	
	@Override
	@RequestMapping(value = "/searchPatientByName",method=RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> searchPatientByName(@RequestParam("name") String name) throws AhanaBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("searchPatientByName----start--->"	+ System.currentTimeMillis());
		}
		List<Map<String,String>> roleDetails = patientRegistrationManager.searchPatientByName(name);
		if (logger.isDebugEnabled()) {
			logger.debug("searchPatientByName: Success");
		}
		return handleSuccess("rightsDetails",roleDetails);
	}
}