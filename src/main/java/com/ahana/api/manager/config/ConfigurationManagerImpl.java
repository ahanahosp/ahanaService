package com.ahana.api.manager.config;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ahana.api.dao.config.ConfigurationDao;
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
	public List<Map<String, String>> getAllActiveRoomAndBedType() throws AhanaBusinessException {
		List<Map<String,String>> roomAndBedType=configurationDao.getAllActiveRoomAndBedType();
		if(CollectionUtils.isEmpty(roomAndBedType)){
			throw new AhanaBusinessException(CommonErrorConstants.NO_RECORDS_FOUND);
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
		List<Map<String,String>> chargesForCategory=configurationDao.getAllChargesForCategory();
		if(CollectionUtils.isEmpty(chargesForCategory)){
			throw new AhanaBusinessException(CommonErrorConstants.NO_RECORDS_FOUND);
		}
		return chargesForCategory;
	}

	@Override
	public List<Map<String, String>> getAllPatientCategory() throws AhanaBusinessException {
		List<Map<String,String>> patientCategory=configurationDao.getAllPatientCategory();
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
}