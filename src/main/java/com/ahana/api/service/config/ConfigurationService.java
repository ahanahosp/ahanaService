package com.ahana.api.service.config;

import java.util.Map;

import com.ahana.api.domain.config.AlertType;
import com.ahana.commons.system.security.exception.AhanaBusinessException;

public interface ConfigurationService {

	Map<String, Object> createAlertTypes(AlertType alertType) throws AhanaBusinessException;

	Map<String, Object> getAlertTypeByOid(String alertTypeOid) throws AhanaBusinessException;

	Map<String, Object> deleteAlertType(String alertTypeOid) throws AhanaBusinessException;

	Map<String, Object> getAllAlertType() throws AhanaBusinessException;

}
