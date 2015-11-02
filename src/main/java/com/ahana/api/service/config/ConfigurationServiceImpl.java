package com.ahana.api.service.config;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ahana.api.domain.config.AlertType;
import com.ahana.api.manager.config.ConfigurationManager;
import com.ahana.commons.system.domain.common.AhanaVO;
import com.ahana.commons.system.security.exception.AhanaBusinessException;
import com.ahana.commons.system.service.BaseService;

@RestController
@RequestMapping("/services/rest/secure/config/")
public class ConfigurationServiceImpl extends BaseService implements ConfigurationService {
	
	private static Logger logger = Logger.getLogger(ConfigurationServiceImpl.class);

	@Autowired
	private ConfigurationManager configurationManager;
	
	@Override
	@RequestMapping(value = "/createAlertTypes",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> createAlertTypes(@Valid @RequestBody AlertType alertType) throws AhanaBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("createAlertTypes----start--->"	+ System.currentTimeMillis());
		}
		configurationManager.createOrUpdateConfigData(alertType);
		if (logger.isDebugEnabled()) {
			logger.debug("createAlertTypes: Success");
		}
		return handleSuccess("floor",alertType);
	}
	
	@Override
	@RequestMapping(value = "/getAlertTypeByOid",method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getAlertTypeByOid(@RequestParam("oid") String alertTypeOid) throws AhanaBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getAlertTypeByOid----start--->"	+ System.currentTimeMillis());
		}
		AhanaVO ahanaVO=configurationManager.getConfigDataByOid("getAlertTypeByOid", "alertTypeOid", alertTypeOid);
		if (logger.isDebugEnabled()) {
			logger.debug("getAlertTypeByOid: Success");
		}
		return handleSuccess("alertType",(AlertType)ahanaVO);
	}
	
	@Override
	@RequestMapping(value = "/getAllAlertType",method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getAllAlertType() throws AhanaBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getAllAlertType----start--->"	+ System.currentTimeMillis());
		}
		List<Map<String,String>> alertDetails=configurationManager.getAllAlertType();
		if (logger.isDebugEnabled()) {
			logger.debug("getAllAlertType: Success");
		}
		return handleSuccess("alertDetails",alertDetails);
	}
	
	@Override
	@RequestMapping(value = "/deleteFloor",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> deleteAlertType(@RequestParam("oid") String alertTypeOid) throws AhanaBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("deleteFloor----start--->"	+ System.currentTimeMillis());
		}
		configurationManager.deleteConfigData("AlertType", alertTypeOid);
		if (logger.isDebugEnabled()) {
			logger.debug("deleteFloor: Success");
		}
		return handleStatus();
	}
	
	
}
