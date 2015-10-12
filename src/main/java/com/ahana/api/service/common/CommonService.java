package com.ahana.api.service.common;

import java.util.Map;

import com.ahana.api.domain.common.Floor;
import com.ahana.api.domain.common.Room;
import com.ahana.api.domain.common.Ward;
import com.ahana.api.system.security.exception.AhanaBusinessException;

public interface CommonService {

	Map<String, Object> createFloor(Floor floor)throws AhanaBusinessException;

	Map<String, Object> createWard(Ward ward)throws AhanaBusinessException;

	Map<String, Object> getFloorByOid(String floorOid) throws AhanaBusinessException;

	Map<String, Object> getWardByOid(String wardOid) throws AhanaBusinessException;

	Map<String, Object> getAllActiveFloor() throws AhanaBusinessException;

	Map<String, Object> getRoomByOid(String roomOid) throws AhanaBusinessException;

	Map<String, Object> createRoom(Room room) throws AhanaBusinessException;

}
