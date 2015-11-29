package com.ahana.api.service.config;

import java.util.ArrayList;
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
import com.ahana.api.domain.config.AlliedCharges;
import com.ahana.api.domain.config.ChargesForCategory;
import com.ahana.api.domain.config.ConfigWrapper;
import com.ahana.api.domain.config.PatientCategory;
import com.ahana.api.manager.config.ConfigurationManager;
import com.ahana.api.util.BusinessConstants;
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
	
	@Override
	@RequestMapping(value = "/createAlliedCharges",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> createAlliedCharges(@Valid @RequestBody AlliedCharges alliedCharges) throws AhanaBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("createAlliedCharges----start--->"	+ System.currentTimeMillis());
		}
		configurationManager.createOrUpdateConfigData(alliedCharges);
		if (logger.isDebugEnabled()) {
			logger.debug("createAlliedCharges: Success");
		}
		return handleSuccess("alliedCharges",alliedCharges);
	}
	
	@Override
	@RequestMapping(value = "/getAlliedChargesByOid",method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getAlliedChargesByOid(@RequestParam("oid") String alliedChargesOid) throws AhanaBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getAlliedChargesByOid----start--->"	+ System.currentTimeMillis());
		}
		AhanaVO alliedCharges=configurationManager.getConfigDataByOid("getAlliedChargesByOid", "alliedChargesOid", alliedChargesOid);
		if (logger.isDebugEnabled()) {
			logger.debug("getAlliedChargesByOid: Success");
		}
		return handleSuccess("alliedCharges",(AlliedCharges)alliedCharges);
	}
	
	@Override
	@RequestMapping(value = "/getAllAlliedCharges",method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getAllAlliedCharges() throws AhanaBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getAllActiveRoomAndBedType----start--->"	+ System.currentTimeMillis());
		}
		List<Map<String,String>> alliedChangesDetails=configurationManager.getAllAlliedCharges();
		if (logger.isDebugEnabled()) {
			logger.debug("getAllActiveRoomAndBedType: Success");
		}
		return handleSuccess("alliedChangesDetails",alliedChangesDetails);
	}
	
	@Override
	@RequestMapping(value = "/deleteAlliedCharges",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> deleteAlliedCharges(@RequestParam("oid") String alliedChargesOid) throws AhanaBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("deleteAlliedCharges----start--->"	+ System.currentTimeMillis());
		}
		configurationManager.deleteConfigData("AlliedCharges", alliedChargesOid);
		if (logger.isDebugEnabled()) {
			logger.debug("deleteAlliedCharges: Success");
		}
		return handleStatus();
	}
	
	@Override
	@RequestMapping(value = "/createChargesForCategory",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> createChargesForCategory(@Valid @RequestBody ChargesForCategory chargesForCategory) throws AhanaBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("createChargesForCategory----start--->"	+ System.currentTimeMillis());
		}
		configurationManager.createOrUpdateConfigData(chargesForCategory);
		if (logger.isDebugEnabled()) {
			logger.debug("createChargesForCategory: Success");
		}
		return handleSuccess("chargesForCategory",chargesForCategory);
	}
	
	@Override
	@RequestMapping(value = "/getChargesForCategoryByOid",method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getChargesForCategoryByOid(@RequestParam("oid") String chargesForCategoryOid) throws AhanaBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getChargesForCategoryByOid----start--->"	+ System.currentTimeMillis());
		}
		AhanaVO chargesForCategory=configurationManager.getConfigDataByOid("getChargesForCategoryByOid", "chargesForCategoryOid", chargesForCategoryOid);
		if (logger.isDebugEnabled()) {
			logger.debug("getChargesForCategoryByOid: Success");
		}
		return handleSuccess("chargesForCategory",(ChargesForCategory)chargesForCategory);
	}
	
	@Override
	@RequestMapping(value = "/getActiveChargesForCategory",method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getAllChargesForCategory() throws AhanaBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getAllChargesForCategory----start--->"	+ System.currentTimeMillis());
		}
		List<Map<String,String>> chargesForCategory=configurationManager.getAllChargesForCategory();
		if (logger.isDebugEnabled()) {
			logger.debug("getAllChargesForCategory: Success");
		}
		return handleSuccess("chargesForCategory",chargesForCategory);
	}
	
	@Override
	@RequestMapping(value = "/deleteChargesForCategory",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> deleteChargesForCategory(@RequestParam("oid") String chargesForCategoryOid) throws AhanaBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("deleteChargesForCategory----start--->"	+ System.currentTimeMillis());
		}
		configurationManager.deleteConfigData("ChargesForCategory", chargesForCategoryOid);
		if (logger.isDebugEnabled()) {
			logger.debug("deleteChargesForCategory: Success");
		}
		return handleStatus();
	}
	
	@Override
	@RequestMapping(value = "/createPatientCategory",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> createPatientCategory(@Valid @RequestBody PatientCategory patientCategory) throws AhanaBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("createPatientCategory----start--->"	+ System.currentTimeMillis());
		}
		configurationManager.createOrUpdateConfigData(patientCategory);
		if (logger.isDebugEnabled()) {
			logger.debug("createPatientCategory: Success");
		}
		return handleSuccess("patientCategory",patientCategory);
	}
	
	@Override
	@RequestMapping(value = "/getPatientCategoryByOid",method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getPatientCategoryByOid(@RequestParam("oid") String patientCategoryOid) throws AhanaBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getPatientCategoryByOid----start--->"	+ System.currentTimeMillis());
		}
		AhanaVO patientCategory=configurationManager.getConfigDataByOid("getPatientCategoryByOid", "patientCategoryOid", patientCategoryOid);
		if (logger.isDebugEnabled()) {
			logger.debug("getPatientCategoryByOid: Success");
		}
		return handleSuccess("patientCategory",(PatientCategory)patientCategory);
	}
	
	@Override
	@RequestMapping(value = "/getAllPatientCategory",method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getAllPatientCategory() throws AhanaBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getAllPatientCategory----start--->"	+ System.currentTimeMillis());
		}
		List<Map<String,String>> patientCategoryDetails=configurationManager.getAllPatientCategory();
		if (logger.isDebugEnabled()) {
			logger.debug("getAllPatientCategory: Success");
		}
		return handleSuccess("patientCategoryDetails",patientCategoryDetails);
	}
	
	@Override
	@RequestMapping(value = "/deletePatientCategory",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> deletePatientCategory(@RequestParam("oid") String patientCategoryOid) throws AhanaBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("deletePatientCategory----start--->"	+ System.currentTimeMillis());
		}
		configurationManager.deleteConfigData("PatientCategory", patientCategoryOid);
		if (logger.isDebugEnabled()) {
			logger.debug("deletePatientCategory: Success");
		}
		return handleStatus();
	}
	
	@Override
	@RequestMapping(value = "/getSubCategoryByCategory",method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getSubCategoryByCategory(@RequestParam("category") String category) throws AhanaBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getSubCategoryByCategory----start--->"	+ System.currentTimeMillis());
		}
		List<Map<String,String>> subCategoryDetails=new ArrayList<Map<String,String>>();
		if(category.equalsIgnoreCase(BusinessConstants.ALLIED_CHARGES)){
			subCategoryDetails=configurationManager.getAllActiveAlliedCharges();
		}else if(category.equalsIgnoreCase(BusinessConstants.ROOM_CHARGES)){
			
		}else if(category.equalsIgnoreCase(BusinessConstants.PROFESSIONAL_CHARGES)){
			
		}else if(category.equalsIgnoreCase(BusinessConstants.PROCEDURES_CHARGES)){
			subCategoryDetails=configurationManager.getAllActiveProcedures();
		}else if(category.equalsIgnoreCase(BusinessConstants.LABORATORY_CHARGES)){
			
		}
		if (logger.isDebugEnabled()) {
			logger.debug("getSubCategoryByCategory: Success");
		}
		return handleSuccess("subCategoryDetails",subCategoryDetails);
	}
		
}