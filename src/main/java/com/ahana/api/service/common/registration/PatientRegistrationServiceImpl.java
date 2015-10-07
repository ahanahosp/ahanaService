package com.ahana.api.service.common.registration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ahana.api.common.BaseService;
import com.ahana.api.common.mail.LookupConstants;
import com.ahana.api.domain.common.NameValue;
import com.ahana.api.domain.common.PatientRegistration;
import com.ahana.api.manager.common.lookup.LookupManager;
import com.ahana.api.manager.common.registration.PatientRegistrationManager;
import com.ahana.api.system.security.exception.AhanaBusinessException;

@Controller
public class PatientRegistrationServiceImpl extends BaseService implements PatientRegistrationService {

	private static Logger logger = Logger.getLogger(PatientRegistrationServiceImpl.class);

	@Autowired
	private PatientRegistrationManager patientRegistrationManager;
	
	@Autowired
	private LookupManager lookupService;
	
	@Override
	@RequestMapping(value = { "/services/rest/registration/loadLookups" }, method = RequestMethod.GET)
	public @ResponseBody String loadLookups() {
		if (logger.isDebugEnabled()) {
			logger.debug("loadLookups----start--->"	+ System.currentTimeMillis());
		}
		long currentmilli = 0;
		String jsonResponse = null;
		try {
			if (logger.isDebugEnabled()) {
				currentmilli = System.currentTimeMillis();
			}
			Map<String, List<?>> resultObj=new HashMap<String, List<?>>();
			List<NameValue> countryList= lookupService.getLookupsByCategory(LookupConstants.LOOKUP_COUNTRY);
			resultObj.put("countryDetails", countryList);
			List<NameValue> salutations= lookupService.getLookupsByCategory(LookupConstants.LOOKUP_SALUTATION);
			resultObj.put("salutationDetails", salutations);
			List<NameValue> bloodgroups= lookupService.getLookupsByCategory(LookupConstants.LOOKUP_BLOODGROUP);
			resultObj.put("bloodGroupDetails", bloodgroups);
			List<NameValue> careTakers= lookupService.getLookupsByCategory(LookupConstants.LOOKUP_CARETAKER);
			resultObj.put("careTakerDetails", careTakers);
			List<NameValue> categories= lookupService.getLookupsByCategory(LookupConstants.LOOKUP_CATEGORY);
			resultObj.put("categoryDetails", categories);
			List<NameValue> patientTypes= lookupService.getLookupsByCategory(LookupConstants.LOOKUP_PATIENTTYPE);
			resultObj.put("patientTypeDetails", patientTypes);
			jsonResponse = handleSuccess("lookupValues",resultObj);
			if (logger.isDebugEnabled()) {
				logger.debug("loadLookups: Success");
			}
		} catch (Throwable exc) {
			jsonResponse = handleError(exc);
			if (logger.isDebugEnabled()) {
				logger.error("loadLookups::: Error:::", exc);
			}
		}
		if (logger.isDebugEnabled()) {
			logger.debug("loadLookups----end--->"+ (System.currentTimeMillis() - currentmilli));
		}
		currentmilli = 0;
		return jsonResponse;
	}
	
	@Override
	@RequestMapping(value = "/populateState",method=RequestMethod.GET)
	public @ResponseBody String populateState(@RequestParam("countryId") String countryId) throws AhanaBusinessException {
		String responseJson=null;
		List<NameValue> stateDetails= lookupService.getLookupsByCategory("STATE_"+countryId.toUpperCase());
		responseJson=handleSuccess("stateDetails",stateDetails);
		return responseJson;
	}

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