package com.ahana.api.manager.config;

import java.util.List;
import java.util.Map;

import com.ahana.api.domain.common.RoomAndBedType;
import com.ahana.commons.system.domain.common.AhanaVO;
import com.ahana.commons.system.security.exception.AhanaBusinessException;

public interface ConfigurationManager {

	void createOrUpdateConfigData(AhanaVO ahanaVO) throws AhanaBusinessException;

	void deleteConfigData(String entiryName, String oid);

	AhanaVO getConfigDataByOid(String queryName, String columnName, String oid) throws AhanaBusinessException;

	List<Map<String, String>> getAllAlertType() throws AhanaBusinessException;

	List<Map<String, Object>> getAllActiveRoomAndBedType() throws AhanaBusinessException;

	void createOrUpdateMultipleConfig(List<?> ahanaVOs) throws AhanaBusinessException;

	List<Map<String, String>> getAllAlliedCharges() throws AhanaBusinessException;

	List<Map<String,String>> getAllChargesForCategory() throws AhanaBusinessException;

	List<Map<String, Object>> getAllPatientCategory() throws AhanaBusinessException;

	List<Map<String, String>> getAllActiveAlliedCharges() throws AhanaBusinessException;

	List<Map<String, String>> getAllActiveProcedures();

	void createRoomAndBedType(RoomAndBedType roomAndBedType) throws AhanaBusinessException;

	List<Map<String, String>> getDoctorDetails() throws AhanaBusinessException;

	List<Map<String, String>> getAllDoctorDetails() throws AhanaBusinessException;

	List<Map<String, String>> getAllConfigRoomCharges() throws AhanaBusinessException;

	void deleteMultipleObject(String source, String oids) throws AhanaBusinessException;

	List<Map<String, String>> getAllActiveProfessional()throws AhanaBusinessException;

	Map<String,Object> getChargesForCategoryByOidAndType(String oid, String type) throws AhanaBusinessException;

	void updateChangesForCategory(String oid, String filedName, String fieldValue);

}