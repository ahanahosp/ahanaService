package com.ahana.api.dao;

import java.util.List;
import java.util.Map;

import com.ahana.api.domain.common.Floor;
import com.ahana.api.domain.common.Room;
import com.ahana.api.domain.common.Ward;

public interface CommonDao {

	Floor createFloor(Floor floor);

	Ward createFloor(Ward ward);

	Floor getFloorByOid(String floorOid);

	Ward getWardByOid(String wardOid);

	List<Map<String,String>> getAllActiveFloord();

	Room createRoom(Room room);

	Room getRoomByOid(String roomOid);

}
