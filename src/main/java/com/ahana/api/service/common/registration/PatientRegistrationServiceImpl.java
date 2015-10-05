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
import com.ahana.api.manager.common.registration.PatientRegistrationManager;

@Controller
public class PatientRegistrationServiceImpl extends BaseService implements
		PatientRegistrationService {

	private static Logger logger = Logger
			.getLogger(PatientRegistrationServiceImpl.class);

	@Autowired
	private PatientRegistrationManager patientRegistrationManager;

	@Override
	@RequestMapping(value = { "/services/rest/registration/savePatient" }, method = RequestMethod.POST)
	public @ResponseBody String savePatient(@RequestBody @Valid PatientRegistration patientRegistration,BindingResult result) {
		if (result.hasErrors()) {
			return handleError(result.getAllErrors());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("savePatient----start--->"	+ System.currentTimeMillis());
		}
		long currentmilli = 0;
		String jsonResponse = null;
		try {
			if (logger.isDebugEnabled()) {
				currentmilli = System.currentTimeMillis();
			}
			patientRegistration = patientRegistrationManager.saveBuyer(patientRegistration);
			jsonResponse = handleSuccess("patientRegistration",patientRegistration);
			if (logger.isDebugEnabled()) {
				logger.debug("savePatient: Success");
			}
		} catch (Throwable exc) {
			jsonResponse = handleError(exc);
			if (logger.isDebugEnabled()) {
				logger.error("savePatient::: Error:::", exc);
			}
		}
		if (logger.isDebugEnabled()) {
			logger.debug("savePatient----end--->"+ (System.currentTimeMillis() - currentmilli));
		}
		currentmilli = 0;
		return jsonResponse;
	}
}