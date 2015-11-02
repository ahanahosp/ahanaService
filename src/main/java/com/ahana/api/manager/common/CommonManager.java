package com.ahana.api.manager.common;

import java.util.List;
import java.util.Map;

import com.ahana.api.domain.common.AccountHead;
import com.ahana.api.domain.common.Floor;
import com.ahana.api.domain.common.Procedures;
import com.ahana.api.domain.common.Room;
import com.ahana.api.domain.common.RoomChargeItem;
import com.ahana.api.domain.common.Ward;
import com.ahana.commons.system.domain.common.AhanaVO;
import com.ahana.commons.system.security.exception.AhanaBusinessException;

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

	List<Map<String, String>> getAllRooms() throws AhanaBusinessException;

	void deleteRoom(String roomOid);

	AccountHead createAccountHead(AccountHead accountHead) throws AhanaBusinessException;

	void deleteAccountHead(String accountHeadOid);

	AccountHead getAccountHeadByOid(String accountHeadOid) throws AhanaBusinessException;

	List<Map<String, String>> getAllAccountHead() throws AhanaBusinessException;

	Procedures createProcedures(Procedures procedures) throws AhanaBusinessException;

	void deleteProcedures(String proceduresOid);

	Procedures getProceduresByOid(String proceduresOid) throws AhanaBusinessException;

	List<Map<String, String>> getAllProcedures() throws AhanaBusinessException;

	void deleteRoomChargeItem(String roomChargeItemOid);

	RoomChargeItem getRoomChargeItemByOid(String roomChargeItemOid) throws AhanaBusinessException;

	List<Map<String, String>> getAllRoomChargeItem() throws AhanaBusinessException;

	RoomChargeItem createRoomChargeItem(RoomChargeItem roomChargeItem) throws AhanaBusinessException;

	AhanaVO createOrUpdateConfigs(AhanaVO ahanaVO) throws AhanaBusinessException;

	void deleteConfigs(String string, String specialityDetailsOid);

	AhanaVO getConfigDetailsItemByOid(String queryName, String columnName, String oid) throws AhanaBusinessException;

	List<Map<String, String>> getAllSpeciality() throws AhanaBusinessException;

	List<Map<String, String>> getAllRoomType()throws AhanaBusinessException;

	List<Map<String, String>> getAllRoomMaintenance() throws AhanaBusinessException;

	List<Map<String, Object>> getAllRoomCharges() throws AhanaBusinessException;

	List<Map<String, String>> getFloorValues() throws AhanaBusinessException;

	List<Map<String, String>> getAllOrganizationModule() throws AhanaBusinessException;

	List<Map<String, String>> getSpecialityValues() throws AhanaBusinessException;

	List<Map<String, String>> getWardValues() throws AhanaBusinessException;

	List<Map<String, String>> getCategoryValues() throws AhanaBusinessException;

	List<Map<String, String>> getRoomTypesValues() throws AhanaBusinessException;

	List<Map<String, String>> getRoomChargeItemValues() throws AhanaBusinessException;

}
