package com.ahana.api.manager.common;

import java.util.List;
import java.util.Map;

import com.ahana.api.domain.common.Floor;
import com.ahana.api.domain.common.Room;
import com.ahana.api.domain.common.Ward;
import com.ahana.api.system.security.exception.AhanaBusinessException;

public interface CommonManager {

	Floor createFloor(Floor floor) throws AhanaBusinessException;

	Ward createWard(Ward ward) throws AhanaBusinessException;

	Floor getFloorByOid(String floorOid) throws AhanaBusinessException;

	Ward getWardByOid(String wardOid) throws AhanaBusinessException;

	List<Map<String,String>> getAllActiveFloord() throws AhanaBusinessException;

	Room createRoom(Room room) throws AhanaBusinessException;

	Room getRoomByOid(String roomOid) throws AhanaBusinessException;

	void deleteFloor(String floorOid);

	void deleteWard(String wardOid);

	List<Map<String, String>> getAllWards() throws AhanaBusinessException;

}
