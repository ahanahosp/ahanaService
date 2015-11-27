package com.ahana.api.service.config;

import java.util.Map;

import com.ahana.api.domain.common.RoomAndBedType;
import com.ahana.api.domain.config.AlertType;
import com.ahana.api.domain.config.ConfigWrapper;
import com.ahana.commons.system.security.exception.AhanaBusinessException;

public interface ConfigurationService {

	Map<String, Object> createAlertTypes(AlertType alertType) throws AhanaBusinessException;

	Map<String, Object> getAlertTypeByOid(String alertTypeOid) throws AhanaBusinessException;

	Map<String, Object> deleteAlertType(String alertTypeOid) throws AhanaBusinessException;

	Map<String, Object> getAllAlertType() throws AhanaBusinessException;

	Map<String, Object> createRoomAndBedType(RoomAndBedType roomAndBedType) throws AhanaBusinessException;

	Map<String, Object> getRoomAndBedTypeByOid(String floorOid) throws AhanaBusinessException;

	Map<String, Object> getAllActiveRoomAndBedType() throws AhanaBusinessException;

	Map<String, Object> deleteRoomAndBedType(String roomBedTypeOid) throws AhanaBusinessException;

	Map<String, Object> createOrUpdateMultipleConfig(ConfigWrapper configWrapper) throws AhanaBusinessException;

}
