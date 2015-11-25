package com.ahana.api.manager.config;

import java.util.List;
import java.util.Map;

import com.ahana.commons.system.domain.common.AhanaVO;
import com.ahana.commons.system.security.exception.AhanaBusinessException;

public interface ConfigurationManager {

	void createOrUpdateConfigData(AhanaVO ahanaVO) throws AhanaBusinessException;

	void deleteConfigData(String entiryName, String oid);

	AhanaVO getConfigDataByOid(String queryName, String columnName, String oid) throws AhanaBusinessException;

	List<Map<String, String>> getAllAlertType() throws AhanaBusinessException;

}