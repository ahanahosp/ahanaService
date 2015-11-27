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

import com.ahana.api.domain.common.RoomAndBedType;
import com.ahana.api.domain.config.AlertType;
import com.ahana.api.domain.config.ConfigWrapper;
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
	
	@Override
	@RequestMapping(value = "/createRoomAndBedType",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> createRoomAndBedType(@Valid @RequestBody RoomAndBedType roomAndBedType) throws AhanaBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("createRoomAndBedType----start--->"	+ System.currentTimeMillis());
		}
		configurationManager.createOrUpdateConfigData(roomAndBedType);
		if (logger.isDebugEnabled()) {
			logger.debug("createRoomAndBedType: Success");
		}
		return handleSuccess("roomAndBedType",roomAndBedType);
	}
	
	@Override
	@RequestMapping(value = "/getRoomAndBedTypeByOid",method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getRoomAndBedTypeByOid(@RequestParam("oid") String roomBedTypeOid) throws AhanaBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getRoomAndBedTypeByOid----start--->"	+ System.currentTimeMillis());
		}
		AhanaVO roomAndBedType=configurationManager.getConfigDataByOid("getvRoomAndBedTypeByOid", "roomBedTypeOid", roomBedTypeOid);
		if (logger.isDebugEnabled()) {
			logger.debug("getRoomAndBedTypeByOid: Success");
		}
		return handleSuccess("roomAndBedType",(RoomAndBedType)roomAndBedType);
	}
	
	@Override
	@RequestMapping(value = "/getAllActiveRoomAndBedType",method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getAllActiveRoomAndBedType() throws AhanaBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getAllActiveRoomAndBedType----start--->"	+ System.currentTimeMillis());
		}
		List<Map<String,String>> roomAndBedTypeDetails=configurationManager.getAllActiveRoomAndBedType();
		if (logger.isDebugEnabled()) {
			logger.debug("getAllActiveRoomAndBedType: Success");
		}
		return handleSuccess("roomAndBedTypeDetails",roomAndBedTypeDetails);
	}
	
	@Override
	@RequestMapping(value = "/deleteRoomAndBedType",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> deleteRoomAndBedType(@RequestParam("oid") String roomBedTypeOid) throws AhanaBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("deleteRoomAndBedType----start--->"	+ System.currentTimeMillis());
		}
		configurationManager.deleteConfigData("RoomAndBedType", roomBedTypeOid);
		if (logger.isDebugEnabled()) {
			logger.debug("deleteRoomAndBedType: Success");
		}
		return handleStatus();
	}
	
	@Override
	@RequestMapping(value = "/createOrUpdateMultipleConfig",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> createOrUpdateMultipleConfig(@Valid @RequestBody ConfigWrapper configWrapper) throws AhanaBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("createOrUpdateMultipleConfig----start--->"	+ System.currentTimeMillis());
		}
		configurationManager.createOrUpdateMultipleConfig(configWrapper.getAhanaVOs());
		if (logger.isDebugEnabled()) {
			logger.debug("createOrUpdateMultipleConfig: Success");
		}
		return handleSuccess("multipleConfig",configWrapper);
	}
	
}