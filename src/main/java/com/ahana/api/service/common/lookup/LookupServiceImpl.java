package com.ahana.api.service.common.lookup;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ahana.api.common.BaseService;
import com.ahana.api.domain.common.NameValue;
import com.ahana.api.manager.common.lookup.LookupManager;
import com.ahana.api.system.security.exception.AhanaBusinessException;

@RestController
public class LookupServiceImpl extends BaseService implements LookupService {
	
	private static Logger logger = Logger.getLogger(LookupServiceImpl.class);

	@Autowired
	private LookupManager lookupCacheManager;
	
	@Override
	@RequestMapping(value = { "/services/rest/lookup/loadLookupByName" }, method = RequestMethod.GET)
	public @ResponseBody String loadLookupByName(@RequestParam("lookupNames")String... lookupNames) {
		String jsonResponse = null;
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("loadLookupByName----start--->"	+ System.currentTimeMillis());
			}
			if(lookupNames!=null && lookupNames.length>0){
				Map<String, List<?>> resultObj=new HashMap<String, List<?>>();
				for(String lookupName:lookupNames){
					List<NameValue> lookupValues= lookupCacheManager.getLookupsByCategory(lookupName.trim().toUpperCase());
					resultObj.put(lookupName+"Details", lookupValues);
				}
				jsonResponse = handleSuccess("lookupValues",resultObj);
			}
			if (logger.isDebugEnabled()) {
				logger.debug("loadLookupByName----End's Success--->"+System.currentTimeMillis());
			}
		} catch (Throwable exc) {
			jsonResponse = handleError(exc);
			logger.error("loadLookupByName::: Error:::", exc);
		}
		return jsonResponse;
	}
	
	@Override
	@RequestMapping(value = "/services/rest/lookup/populateState",method=RequestMethod.GET)
	public @ResponseBody String populateState(@RequestParam("countryId") String countryId) throws AhanaBusinessException {
		String responseJson=null;
		List<NameValue> stateDetails= lookupCacheManager.getLookupsByCategory("STATE_"+countryId.toUpperCase());
		responseJson=handleSuccess("stateDetails",stateDetails);
		return responseJson;
	}
	
	public static void main(String arg[]){
		System.out.println(StringUtils.capitalize("lookpservices"));
	}
}