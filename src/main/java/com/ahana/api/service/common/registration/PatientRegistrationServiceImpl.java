package com.ahana.api.service.common.registration;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ahana.api.common.BaseService;
import com.ahana.api.domain.common.PatientRegistration;
import com.ahana.api.domain.common.QuickRegistration;
import com.ahana.api.manager.common.registration.PatientRegistrationManager;

@Controller
@RequestMapping("/services/rest/registration")
public class PatientRegistrationServiceImpl extends BaseService implements PatientRegistrationService {

	private static Logger logger = Logger.getLogger(PatientRegistrationServiceImpl.class);

	@Autowired
	private PatientRegistrationManager patientRegistrationManager;
	
	@Override
	@RequestMapping(value = { "/savePatient" }, method = RequestMethod.POST)
	public @ResponseBody String savePatient(@RequestBody @Valid PatientRegistration patientRegistration,BindingResult result) {
		if (result.hasErrors()) {
			return handleError(result.getAllErrors());
		}
		String jsonResponse = null;
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("savePatient----start--->"	+ System.currentTimeMillis());
			}
			patientRegistration = patientRegistrationManager.savePatient(patientRegistration);
			jsonResponse = handleSuccess("patientRegistration",patientRegistration);
			if (logger.isDebugEnabled()) {
				logger.debug("savePatient: Success");
			}
		} catch (Throwable exc) {
			jsonResponse = handleError(exc);
			logger.error("savePatient::: Error:::", exc);
		}
		return jsonResponse;
	}
	
	@Override
	@RequestMapping(value = { "/saveQuickRegistration" }, method = RequestMethod.POST)
	public @ResponseBody String saveQuickRegistration(@RequestBody @Valid QuickRegistration quickRegistration,BindingResult result) {
		if (result.hasErrors()) {
			return handleError(result.getAllErrors());
		}
		String jsonResponse = null;
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("saveQuickRegistration----start--->"	+ System.currentTimeMillis());
			}
			quickRegistration = patientRegistrationManager.saveQuickRegistration(quickRegistration);
			jsonResponse = handleSuccess("quickRegistration",quickRegistration);
			if (logger.isDebugEnabled()) {
				logger.debug("saveQuickRegistration: Success");
			}
		} catch (Throwable exc) {
			jsonResponse = handleError(exc);
			logger.error("saveQuickRegistration::: Error:::", exc);
		}
		return jsonResponse;
	}
}