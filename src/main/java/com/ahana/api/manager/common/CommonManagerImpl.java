package com.ahana.api.manager.common;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ahana.api.common.ErrorConstants;
import com.ahana.api.dao.common.CommonDao;
import com.ahana.api.domain.common.AccountHead;
import com.ahana.api.domain.common.Floor;
import com.ahana.api.domain.common.Procedures;
import com.ahana.api.domain.common.Room;
import com.ahana.api.domain.common.RoomChargeItem;
import com.ahana.api.domain.common.Ward;
import com.ahana.api.system.security.exception.AhanaBusinessException;

@Transactional(propagation = Propagation.REQUIRED)
public class CommonManagerImpl implements CommonManager {
	
	@Autowired
	private CommonDao commonDao;

	@Override
	public Floor createFloor(Floor floor) throws AhanaBusinessException {
		if(floor==null){
			throw new AhanaBusinessException(ErrorConstants.NO_RECORDS_FOUND);
		}
		commonDao.createFloor(floor);
		return floor;
	}
	
	@Override
	public Ward createWard(Ward ward) throws AhanaBusinessException {
		if(ward==null){
			throw new AhanaBusinessException(ErrorConstants.NO_RECORDS_FOUND);
		}
		commonDao.createFloor(ward);
		return ward;
	}

	@Override
	public Floor getFloorByOid(String floorOid) throws AhanaBusinessException {
		Floor floor=commonDao.getFloorByOid(floorOid);
		if(floor==null){
			throw new AhanaBusinessException(ErrorConstants.NO_RECORDS_FOUND);
		}
		return floor;
	}

	@Override
	public Ward getWardByOid(String wardOid) throws AhanaBusinessException {
		Ward ward=commonDao.getWardByOid(wardOid);
		if(ward==null){
			throw new AhanaBusinessException(ErrorConstants.NO_RECORDS_FOUND);
		}
		return ward;
	}

	@Override
	public List<Map<String,String>> getAllActiveFloord() throws AhanaBusinessException {
		List<Map<String,String>> floors=commonDao.getAllActiveFloord();
		if(CollectionUtils.isEmpty(floors)){
			throw new AhanaBusinessException(ErrorConstants.NO_RECORDS_FOUND);
		}
		return floors;
	}

	@Override
	public Room createRoom(Room room) throws AhanaBusinessException {
		if(room==null){
			throw new AhanaBusinessException(ErrorConstants.NO_RECORDS_FOUND);
		}
		commonDao.createRoom(room);
		return room;
	}

	@Override
	public Room getRoomByOid(String roomOid) throws AhanaBusinessException {
		Room room=commonDao.getRoomByOid(roomOid);
		if(room==null){
			throw new AhanaBusinessException(ErrorConstants.NO_RECORDS_FOUND);
		}
		return room;
	}

	@Override
	public void deleteFloor(String floorOid) {
		commonDao.deleteFloor(floorOid);
	}

	@Override
	public void deleteWard(String wardOid) {
		commonDao.deleteWard(wardOid);
	}

	@Override
	public List<Map<String, String>> getAllWards() throws AhanaBusinessException {
		List<Map<String,String>> wards=commonDao.getAllWards();
		if(CollectionUtils.isEmpty(wards)){
			throw new AhanaBusinessException(ErrorConstants.NO_RECORDS_FOUND);
		}
		return wards;
	}

	@Override
	public List<Map<String, String>> getAllRooms() throws AhanaBusinessException {
		List<Map<String,String>> rooms=commonDao.getAllRooms();
		if(CollectionUtils.isEmpty(rooms)){
			throw new AhanaBusinessException(ErrorConstants.NO_RECORDS_FOUND);
		}
		return rooms;
	}

	@Override
	public void deleteRoom(String roomOid) {
		commonDao.deleteRoom(roomOid);
	}

	@Override
	public AccountHead createAccountHead(AccountHead accountHead) throws AhanaBusinessException {
		if(accountHead==null){
			throw new AhanaBusinessException(ErrorConstants.NO_RECORDS_FOUND);
		}
		commonDao.createAccountHead(accountHead);
		return accountHead;
	}

	@Override
	public void deleteAccountHead(String accountHeadOid) {
		commonDao.deleteAccountHead(accountHeadOid);
	}

	@Override
	public AccountHead getAccountHeadByOid(String accountHeadOid) throws AhanaBusinessException {
		AccountHead accountHead=commonDao.getAccountHeadByOid(accountHeadOid);
		if(accountHead==null){
			throw new AhanaBusinessException(ErrorConstants.NO_RECORDS_FOUND);
		}
		return accountHead;
	}

	@Override
	public List<Map<String, String>> getAllAccountHead() throws AhanaBusinessException {
		List<Map<String,String>> accountHead=commonDao.getAllAccountHead();
		if(CollectionUtils.isEmpty(accountHead)){
			throw new AhanaBusinessException(ErrorConstants.NO_RECORDS_FOUND);
		}
		return accountHead;
	}

	@Override
	public Procedures createProcedures(Procedures procedures) throws AhanaBusinessException {
		if(procedures==null){
			throw new AhanaBusinessException(ErrorConstants.NO_RECORDS_FOUND);
		}
		commonDao.createProcedures(procedures);
		return procedures;
	}

	@Override
	public void deleteProcedures(String proceduresOid) {
		commonDao.deleteProcedures(proceduresOid);
	}

	@Override
	public Procedures getProceduresByOid(String proceduresOid) throws AhanaBusinessException {
		Procedures procedures=commonDao.getProceduresByOid(proceduresOid);
		if(procedures==null){
			throw new AhanaBusinessException(ErrorConstants.NO_RECORDS_FOUND);
		}
		return procedures;
	}

	@Override
	public List<Map<String, String>> getAllProcedures() throws AhanaBusinessException {
		List<Map<String,String>> procedures=commonDao.getAllProcedures();
		if(CollectionUtils.isEmpty(procedures)){
			throw new AhanaBusinessException(ErrorConstants.NO_RECORDS_FOUND);
		}
		return procedures;
	}

	@Override
	public void deleteRoomChargeItem(String roomChargeItemOid) {
		commonDao.deleteRoomChargeItem(roomChargeItemOid);
	}

	@Override
	public RoomChargeItem getRoomChargeItemByOid(String roomChargeItemOid) throws AhanaBusinessException {
		RoomChargeItem roomChargeItem=commonDao.getRoomChargeItemByOid(roomChargeItemOid);
		if(roomChargeItem==null){
			throw new AhanaBusinessException(ErrorConstants.NO_RECORDS_FOUND);
		}
		return roomChargeItem;
	}

	@Override
	public List<Map<String, String>> getAllRoomChargeItem() throws AhanaBusinessException {
		List<Map<String,String>> roomChargeItem=commonDao.getAllRoomChargeItem();
		if(CollectionUtils.isEmpty(roomChargeItem)){
			throw new AhanaBusinessException(ErrorConstants.NO_RECORDS_FOUND);
		}
		return roomChargeItem;
	}

	@Override
	public RoomChargeItem createRoomChargeItem(RoomChargeItem roomChargeItem) throws AhanaBusinessException {
		if(roomChargeItem==null){
			throw new AhanaBusinessException(ErrorConstants.NO_RECORDS_FOUND);
		}
		commonDao.createRoomChargeItem(roomChargeItem);
		return roomChargeItem;
	}

}