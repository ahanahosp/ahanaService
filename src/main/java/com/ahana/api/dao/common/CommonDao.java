package com.ahana.api.dao.common;

import java.util.List;
import java.util.Map;

import com.ahana.api.common.AhanaVO;
import com.ahana.api.domain.common.AccountHead;
import com.ahana.api.domain.common.Floor;
import com.ahana.api.domain.common.Procedures;
import com.ahana.api.domain.common.Room;
import com.ahana.api.domain.common.RoomChargeItem;
import com.ahana.api.domain.common.Ward;

public interface CommonDao {

	Floor createFloor(Floor floor);

	Floor getFloorByOid(String floorOid);

	Ward getWardByOid(String wardOid);

	List<Map<String,String>> getAllActiveFloord();

	Room createRoom(Room room);

	Room getRoomByOid(String roomOid);

	void deleteFloor(String floorOid);

	void deleteWard(String wardOid);

	List<Map<String, String>> getAllWards();

	List<Map<String, String>> getAllRooms();

	void deleteRoom(String roomOid);

	void deleteAccountHead(String accountHeadOid);

	AccountHead createAccountHead(AccountHead accountHead);

	AccountHead getAccountHeadByOid(String accountHeadOid);

	List<Map<String, String>> getAllAccountHead();

	Procedures createProcedures(Procedures procedures);

	void deleteProcedures(String proceduresOid);

	Procedures getProceduresByOid(String proceduresOid);

	List<Map<String, String>> getAllProcedures();

	RoomChargeItem getRoomChargeItemByOid(String roomChargeItemOid);

	List<Map<String, String>> getAllRoomChargeItem();

	void deleteRoomChargeItem(String roomChargeItemOid);

	RoomChargeItem createRoomChargeItem(RoomChargeItem roomChargeItem);

	AhanaVO createOrUpdateConfigs(AhanaVO ahanaVO);

	void deleteConfigs(String entiryName, String oid);

	AhanaVO getConfigDetailsItemByOid(String queryName, String columnName, String oid);

	List<Map<String, String>> getAllSpeciality();

	List<Map<String, String>> getAllRoomType();

	List<Map<String, String>> getAllRoomMaintenance();

	List<Map<String, Object>> getAllRoomCharges();

	List<Map<String, String>> getFloorValues();

	Ward createWard(Ward ward);

}
