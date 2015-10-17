package com.ahana.api.service.common;

import java.util.Map;

import com.ahana.api.domain.common.AccountHead;
import com.ahana.api.domain.common.Floor;
import com.ahana.api.domain.common.Procedures;
import com.ahana.api.domain.common.Room;
import com.ahana.api.domain.common.RoomChargeItem;
import com.ahana.api.domain.common.RoomCharges;
import com.ahana.api.domain.common.RoomMaintenanceDetails;
import com.ahana.api.domain.common.RoomType;
import com.ahana.api.domain.common.SpecialityDetails;
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

	Map<String, Object> deleteFloor(String floorOid) throws AhanaBusinessException;

	Map<String, Object> deleteWard(String wardOid) throws AhanaBusinessException;

	Map<String, Object> getAllWards() throws AhanaBusinessException;

	Map<String, Object> getAllRooms() throws AhanaBusinessException;

	Map<String, Object> deleteRoom(String roomOid) throws AhanaBusinessException;

	Map<String, Object> createAccountHead(AccountHead accountHead) throws AhanaBusinessException;

	Map<String, Object> deleteAccountHead(String accountHeadOid) throws AhanaBusinessException;

	Map<String, Object> getAccountHeadByOid(String accountHeadOid) throws AhanaBusinessException;

	Map<String, Object> getAllAccountHead() throws AhanaBusinessException;

	Map<String, Object> createProcedures(Procedures procedures) throws AhanaBusinessException;

	Map<String, Object> deleteProcedures(String proceduresOid) throws AhanaBusinessException;

	Map<String, Object> getProceduresByOid(String proceduresOid) throws AhanaBusinessException;

	Map<String, Object> getAllProcedures() throws AhanaBusinessException;

	Map<String, Object> deleteRoomChargeItem(String roomChargeItemOid) throws AhanaBusinessException;

	Map<String, Object> getRoomChargeItemByOid(String roomChargeItemOid) throws AhanaBusinessException;

	Map<String, Object> getAllRoomChargeItem() throws AhanaBusinessException;

	Map<String, Object> createRoomChargeItem(RoomChargeItem roomChargeItem) throws AhanaBusinessException;

	Map<String, Object> createSpeciality(SpecialityDetails specialityDetails) throws AhanaBusinessException;

	Map<String, Object> deleteSpeciality(String specialityDetailsOid) throws AhanaBusinessException;

	Map<String, Object> getSpecialityByOid(String specialityOid) throws AhanaBusinessException;

	Map<String, Object> getAllSpeciality() throws AhanaBusinessException;

	Map<String, Object> createRoomType(RoomType roomType) throws AhanaBusinessException;

	Map<String, Object> deleteRoomType(String roomTypeOid) throws AhanaBusinessException;

	Map<String, Object> getRoomTypeByOid(String roomTypeOid) throws AhanaBusinessException;

	Map<String, Object> getAllRoomType() throws AhanaBusinessException;

	Map<String, Object> createRoomMaintenance(RoomMaintenanceDetails roomMaintenanceDetails)throws AhanaBusinessException;

	Map<String, Object> deleteRoomMaintenance(String roomMaintenanceDetailOid) throws AhanaBusinessException;

	Map<String, Object> getRoomMaintenanceByOid(String roomMaintenanceDetails) throws AhanaBusinessException;

	Map<String, Object> getAllRoomMaintenance() throws AhanaBusinessException;

	Map<String, Object> createRoomCharge(RoomCharges roomCharges) throws AhanaBusinessException;

	Map<String, Object> getRoomChargesByOid(String roomChargeOid) throws AhanaBusinessException;

	Map<String, Object> deleteRoomCharges(String roomChargeOid) throws AhanaBusinessException;

	Map<String, Object> getAllRoomCharges() throws AhanaBusinessException;

	Map<String, Object> getFloorValues() throws AhanaBusinessException;

	Map<String, Object> getAllOrganizationModule() throws AhanaBusinessException;

	Map<String, Object> getSpecialityValues() throws AhanaBusinessException;

}
