package com.ahana.api.dao.config;

import java.util.List;
import java.util.Map;

import com.ahana.commons.system.domain.common.AhanaVO;

public interface ConfigurationDao {

	void createOrUpdateConfigData(AhanaVO ahanaVO);

	void deleteConfigs(String entiryName, String oid);

	AhanaVO getConfigDetailsItemByOid(String queryName, String columnName, String oid);

	List<Map<String, String>> getAllAlertType();

	List<Map<String, String>> getAllActiveRoomAndBedType();

	void createOrUpdateMultipleConfig(List<AhanaVO> ahanaVOs);

}
