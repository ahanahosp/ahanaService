package com.ahana.api.service.lookup;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ahana.api.common.BaseService;
import com.ahana.api.common.mail.LookupConstants;
import com.ahana.api.domain.common.NameValue;
import com.ahana.api.manager.lookup.LookupManager;
import com.ahana.api.system.security.exception.AhanaBusinessException;

@RestController
@RequestMapping("/services/rest/secure/lookup")
public class LookupServiceImpl extends BaseService implements LookupService {
	
	private static Logger logger = Logger.getLogger(LookupServiceImpl.class);

	@Autowired
	private LookupManager lookupCacheManager;
	
	@Override
	@RequestMapping(value = { "/loadLookupByName" }, method = RequestMethod.GET)
	public @ResponseBody Map<String,Object> loadLookupByName(@RequestParam("lookupNames")String... lookupNames) {
		if (logger.isDebugEnabled()) {
			logger.debug("loadLookupByName----start--->"	+ System.currentTimeMillis());
		}
		if(lookupNames!=null && lookupNames.length>0){
			Map<String, List<?>> resultObj=new HashMap<String, List<?>>();
			for(String lookupName:lookupNames){
				List<NameValue> lookupValues= lookupCacheManager.getLookupsByCategory(lookupName.trim().toUpperCase());
				resultObj.put(lookupName+"Details", lookupValues);
			}
			return handleSuccess("lookupValues", resultObj);
		}
		if (logger.isDebugEnabled()) {
			logger.debug("loadLookupByName----End's Success--->"+System.currentTimeMillis());
		}
		return null;
	}
	
	@Override
	@RequestMapping(value = "/populateState",method=RequestMethod.GET)
	public @ResponseBody Map<String,Object> populateState(@RequestParam("countryId") String countryId) throws AhanaBusinessException {
		List<NameValue> stateDetails= lookupCacheManager.getLookupsByCategory(LookupConstants.LOOKUP_STATE+countryId.toUpperCase());
		return handleSuccess("stateDetails", stateDetails);
	}
	
	@Override
	@RequestMapping(value = "/populateCity",method=RequestMethod.GET)
	public @ResponseBody Map<String,Object> populateCity(@RequestParam("stateId") String stateId) throws AhanaBusinessException {
		List<NameValue> cityDetails= lookupCacheManager.getLookupsByCategory(LookupConstants.LOOKUP_CITY+stateId.toUpperCase());
		return handleSuccess("cityDetails", cityDetails);
	}
}