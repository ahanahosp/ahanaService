package com.ahana.api.manager.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ahana.api.dao.config.ConfigurationDao;
import com.ahana.api.domain.common.RoomAndBedType;
import com.ahana.api.domain.config.BedVsRoomType;
import com.ahana.commons.system.domain.common.AhanaVO;
import com.ahana.commons.system.security.error.CommonErrorConstants;
import com.ahana.commons.system.security.exception.AhanaBusinessException;

@Transactional(propagation = Propagation.REQUIRED)
public class ConfigurationManagerImpl implements ConfigurationManager {
	
	@Autowired
	private ConfigurationDao configurationDao;

	@Override
	public void createOrUpdateConfigData(AhanaVO ahanaVO) throws AhanaBusinessException {
		if(ahanaVO==null){
			throw new AhanaBusinessException(CommonErrorConstants.NO_RECORDS_FOUND);
		}
		configurationDao.createOrUpdateConfigData(ahanaVO);
	}	
	
	@Override
	public void deleteConfigData(String entiryName, String oid) {
		configurationDao.deleteConfigs(entiryName,oid);
	}

	@Override
	public AhanaVO getConfigDataByOid(String queryName, String columnName, String oid) throws AhanaBusinessException {
		AhanaVO ahanaVO=configurationDao.getConfigDetailsItemByOid(queryName,columnName,oid);
		if(ahanaVO==null){
			throw new AhanaBusinessException(CommonErrorConstants.NO_RECORDS_FOUND);
		}
		if(ahanaVO instanceof RoomAndBedType){
			List<String> savedRoomTypeOids=configurationDao.getSavedRoomTypeByOid(((RoomAndBedType)ahanaVO).getOid());
			if(CollectionUtils.isNotEmpty(savedRoomTypeOids)){
				((RoomAndBedType)ahanaVO).setSavedRoomTyes(savedRoomTypeOids);
			}
		}
		return ahanaVO;
	}

	@Override
	public List<Map<String, String>> getAllAlertType() throws AhanaBusinessException {
		List<Map<String,String>> alertTypeDetails=configurationDao.getAllAlertType();
		if(CollectionUtils.isEmpty(alertTypeDetails)){
			throw new AhanaBusinessException(CommonErrorConstants.NO_RECORDS_FOUND);
		}
		return alertTypeDetails;
	}

	@Override
	public List<Map<String, Object>> getAllActiveRoomAndBedType() throws AhanaBusinessException {
		List<Map<String,Object>> roomAndBedType=configurationDao.getAllActiveRoomAndBedType();
		if(CollectionUtils.isEmpty(roomAndBedType)){
			throw new AhanaBusinessException(CommonErrorConstants.NO_RECORDS_FOUND);
		}
		List<Map<String, String>> roomTypes=null;
		for(Map<String, Object> mapObj:roomAndBedType){
			roomTypes=new ArrayList<Map<String, String>>();
			roomTypes=configurationDao.enhanceRoomType((String)mapObj.get("oid"));
			mapObj.put("roomTypes", roomTypes);
		}
		return roomAndBedType;
	}

	@Override
	public void createOrUpdateMultipleConfig(List<?> ahanaVOs) throws AhanaBusinessException {
		if(CollectionUtils.isEmpty(ahanaVOs)){
			throw new AhanaBusinessException(CommonErrorConstants.NO_RECORDS_FOUND);
		}
		configurationDao.createOrUpdateMultipleConfig(ahanaVOs);
	}

	@Override
	public List<Map<String, String>> getAllAlliedCharges() throws AhanaBusinessException {
		List<Map<String,String>> alliedCharges=configurationDao.getAllAlliedCharges();
		if(CollectionUtils.isEmpty(alliedCharges)){
			throw new AhanaBusinessException(CommonErrorConstants.NO_RECORDS_FOUND);
		}
		return alliedCharges;
	}

	@Override
	public List<Map<String, String>> getAllChargesForCategory() throws AhanaBusinessException {
		List<String> categoryNames=configurationDao.getCategoryNameForChargesForCategory();
		if(CollectionUtils.isEmpty(categoryNames)){
			throw new AhanaBusinessException(CommonErrorConstants.NO_RECORDS_FOUND);
		}
		List<Map<String,List<Map<String,String>>>> result=null;
		Map<String,String> temp=null;
		List<Map<String,String>> chargesForCategory=null;
		for(String categoryName:categoryNames){
			chargesForCategory=configurationDao.getAllChargesForCategory(categoryName);
			temp=new HashMap<String,String>();
			for(Map<String, String> mapObject:chargesForCategory){
				populateValue(temp,mapObject.get("op"));
				if(temp!=null && temp.size()>0){
					temp.put("opOrIp", "OP");
				}
				populateValue(temp,mapObject.get("ip"));
				if(temp!=null && temp.size()>0){
					temp.put("opOrIp", "IP");
				}
				temp.put("", mapObject.get(""));
				temp.put("", mapObject.get(""));
				temp.put("", mapObject.get(""));
			}
		}
		return chargesForCategory;
	}

	private void populateValue(Map<String, String> temp, String opOrIp) {
		if(StringUtils.isNotBlank(opOrIp)){
			String[] strArray=opOrIp.split("#");
			for(String innerOb:strArray){
				String[] tempArr=innerOb.split(":");
				if(Integer.valueOf(tempArr[1])>0){
					temp.put(tempArr[0], tempArr[1]);
				}
			}
		}
	}

	@Override
	public List<Map<String, Object>> getAllPatientCategory() throws AhanaBusinessException {
		List<Map<String,Object>> patientCategory=configurationDao.getAllPatientCategory();
		if(CollectionUtils.isEmpty(patientCategory)){
			throw new AhanaBusinessException(CommonErrorConstants.NO_RECORDS_FOUND);
		}
		return patientCategory;
	}

	@Override
	public List<Map<String, String>> getAllActiveAlliedCharges() throws AhanaBusinessException {
		return configurationDao.getAllActiveAlliedCharges();
	}

	@Override
	public List<Map<String, String>> getAllActiveProcedures() {
		return configurationDao.getAllActiveProcedures();
	}

	@Override
	public void createRoomAndBedType(RoomAndBedType roomAndBedType) throws AhanaBusinessException {
		if(roomAndBedType==null){
			throw new AhanaBusinessException(CommonErrorConstants.NO_RECORDS_FOUND);
		}
		configurationDao.createRoomAndBedType(roomAndBedType);
		if(StringUtils.isNotBlank(roomAndBedType.getRoomTypeOids())){
			configurationDao.deleteBedVsRoomType(roomAndBedType.getOid());
			List<String> roomTypes = Arrays.asList(roomAndBedType.getRoomTypeOids().split("\\s*,\\s*"));
			for(String roomTypeOid:roomTypes){
				BedVsRoomType bedVsRoomType=new BedVsRoomType();
				bedVsRoomType.setRoomAndBedTypeOid(roomAndBedType.getOid());
				bedVsRoomType.setRoomTypeOid(roomTypeOid);
				configurationDao.createBedVsRoomType(bedVsRoomType);
			}
		}
	}

	@Override
	public List<Map<String, String>> getDoctorDetails() throws AhanaBusinessException {
		List<Map<String, String>> doctorDetails=configurationDao.getDoctorDetails();
		if(CollectionUtils.isEmpty(doctorDetails)){
			throw new AhanaBusinessException(CommonErrorConstants.NO_RECORDS_FOUND);
		}
		return doctorDetails;
	}

	@Override
	public List<Map<String, String>> getAllDoctorDetails() throws AhanaBusinessException {
		List<Map<String, String>> doctorDetails=configurationDao.getAllDoctorDetails();
		if(CollectionUtils.isEmpty(doctorDetails)){
			throw new AhanaBusinessException(CommonErrorConstants.NO_RECORDS_FOUND);
		}
		return doctorDetails;
	}

	@Override
	public List<Map<String, String>> getAllConfigRoomCharges() throws AhanaBusinessException {
		List<Map<String, String>> configRoomChargesDetails=configurationDao.getAllConfigRoomCharges();
		if(CollectionUtils.isEmpty(configRoomChargesDetails)){
			throw new AhanaBusinessException(CommonErrorConstants.NO_RECORDS_FOUND);
		}
		return configRoomChargesDetails;
	}

	@Override
	public void deleteMultipleObject(String source, String oids) throws AhanaBusinessException {
		if(StringUtils.isBlank(oids) && StringUtils.isBlank(source)){
			throw new AhanaBusinessException(CommonErrorConstants.NO_RECORDS_FOUND);
		}
		configurationDao.deleteMultipleObject(source,oids);
	}

	@Override
	public List<Map<String, String>> getAllActiveProfessional() throws AhanaBusinessException {
		List<Map<String, String>> professionalDetails=configurationDao.getAllActiveProfessional();
		if(CollectionUtils.isEmpty(professionalDetails)){
			throw new AhanaBusinessException(CommonErrorConstants.NO_RECORDS_FOUND);
		}
		return professionalDetails;
	}
}