package com.ahana.api.dao.config;

import java.util.List;
import java.util.Map;

import com.ahana.api.domain.common.RoomAndBedType;
import com.ahana.api.domain.config.BedVsRoomType;
import com.ahana.commons.system.domain.common.AhanaVO;

public interface ConfigurationDao {

	void createOrUpdateConfigData(AhanaVO ahanaVO);

	void deleteConfigs(String entiryName, String oid);

	AhanaVO getConfigDetailsItemByOid(String queryName, String columnName, String oid);

	List<Map<String, String>> getAllAlertType();

	List<Map<String, Object>> getAllActiveRoomAndBedType();

	void createOrUpdateMultipleConfig(List<?> ahanaVOs);

	List<Map<String, String>> getAllAlliedCharges();

	List<Map<String, String>> getAllChargesForCategory(String name);

	List<Map<String, Object>> getAllPatientCategory();

	List<Map<String, String>> getAllActiveAlliedCharges();

	List<Map<String, String>> getAllActiveProcedures();

	void createRoomAndBedType(RoomAndBedType roomAndBedType);

	void deleteBedVsRoomType(String oid);

	void createBedVsRoomType(BedVsRoomType bedVsRoomType);

	List<Map<String, String>> enhanceRoomType(String string);

	List<Map<String, String>> getDoctorDetails();

	List<String> getSavedRoomTypeByOid(String oid);

	List<Map<String, String>> getAllDoctorDetails();

	List<Map<String, String>> getAllConfigRoomCharges();

	void deleteMultipleObject(String source, String oids);

	List<Map<String, String>> getAllActiveProfessional();

	List<String> getCategoryNameForChargesForCategory();

	Map<String, Object> getChargesForCategoryByOidAndType(String oid, String type);

	void updateChangesForCategory(String oid, String filedName, String fieldValue);

}