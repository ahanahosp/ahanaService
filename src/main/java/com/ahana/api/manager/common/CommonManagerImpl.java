package com.ahana.api.manager.common;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ahana.api.dao.common.CommonDao;
import com.ahana.api.domain.common.AccountHead;
import com.ahana.api.domain.common.Floor;
import com.ahana.api.domain.common.Procedures;
import com.ahana.api.domain.common.Room;
import com.ahana.api.domain.common.RoomChargeItem;
import com.ahana.api.domain.common.Ward;
import com.ahana.commons.system.domain.common.AhanaVO;
import com.ahana.commons.system.security.error.CommonErrorConstants;
import com.ahana.commons.system.security.exception.AhanaBusinessException;

@Transactional(propagation = Propagation.REQUIRED)
public class CommonManagerImpl implements CommonManager {
	
	@Autowired
	private CommonDao commonDao;

	@Override
	public Floor createFloor(Floor floor) throws AhanaBusinessException {
		if(floor==null){
			throw new AhanaBusinessException(CommonErrorConstants.NO_RECORDS_FOUND);
		}
		commonDao.createFloor(floor);
		return floor;
	}
	
	@Override
	public Ward createWard(Ward ward) throws AhanaBusinessException {
		if(ward==null){
			throw new AhanaBusinessException(CommonErrorConstants.NO_RECORDS_FOUND);
		}
		commonDao.createWard(ward);
		return ward;
	}

	@Override
	public Floor getFloorByOid(String floorOid) throws AhanaBusinessException {
		Floor floor=commonDao.getFloorByOid(floorOid);
		if(floor==null){
			throw new AhanaBusinessException(CommonErrorConstants.NO_RECORDS_FOUND);
		}
		return floor;
	}

	@Override
	public Ward getWardByOid(String wardOid) throws AhanaBusinessException {
		Ward ward=commonDao.getWardByOid(wardOid);
		if(ward==null){
			throw new AhanaBusinessException(CommonErrorConstants.NO_RECORDS_FOUND);
		}
		return ward;
	}

	@Override
	public List<Map<String,String>> getAllActiveFloord() throws AhanaBusinessException {
		List<Map<String,String>> floors=commonDao.getAllActiveFloord();
		if(CollectionUtils.isEmpty(floors)){
			throw new AhanaBusinessException(CommonErrorConstants.NO_RECORDS_FOUND);
		}
		return floors;
	}

	@Override
	public Room createRoom(Room room) throws AhanaBusinessException {
		if(room==null){
			throw new AhanaBusinessException(CommonErrorConstants.NO_RECORDS_FOUND);
		}
		commonDao.createRoom(room);
		return room;
	}

	@Override
	public Room getRoomByOid(String roomOid) throws AhanaBusinessException {
		Room room=commonDao.getRoomByOid(roomOid);
		if(room==null){
			throw new AhanaBusinessException(CommonErrorConstants.NO_RECORDS_FOUND);
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
			throw new AhanaBusinessException(CommonErrorConstants.NO_RECORDS_FOUND);
		}
		return wards;
	}

	@Override
	public List<Map<String, String>> getAllRooms() throws AhanaBusinessException {
		List<Map<String,String>> rooms=commonDao.getAllRooms();
		if(CollectionUtils.isEmpty(rooms)){
			throw new AhanaBusinessException(CommonErrorConstants.NO_RECORDS_FOUND);
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
			throw new AhanaBusinessException(CommonErrorConstants.NO_RECORDS_FOUND);
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
			throw new AhanaBusinessException(CommonErrorConstants.NO_RECORDS_FOUND);
		}
		return accountHead;
	}

	@Override
	public List<Map<String, String>> getAllAccountHead() throws AhanaBusinessException {
		List<Map<String,String>> accountHead=commonDao.getAllAccountHead();
		if(CollectionUtils.isEmpty(accountHead)){
			throw new AhanaBusinessException(CommonErrorConstants.NO_RECORDS_FOUND);
		}
		return accountHead;
	}

	@Override
	public Procedures createProcedures(Procedures procedures) throws AhanaBusinessException {
		if(procedures==null){
			throw new AhanaBusinessException(CommonErrorConstants.NO_RECORDS_FOUND);
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
			throw new AhanaBusinessException(CommonErrorConstants.NO_RECORDS_FOUND);
		}
		return procedures;
	}

	@Override
	public List<Map<String, String>> getAllProcedures() throws AhanaBusinessException {
		List<Map<String,String>> procedures=commonDao.getAllProcedures();
		if(CollectionUtils.isEmpty(procedures)){
			throw new AhanaBusinessException(CommonErrorConstants.NO_RECORDS_FOUND);
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
			throw new AhanaBusinessException(CommonErrorConstants.NO_RECORDS_FOUND);
		}
		return roomChargeItem;
	}

	@Override
	public List<Map<String, String>> getAllRoomChargeItem() throws AhanaBusinessException {
		List<Map<String,String>> roomChargeItem=commonDao.getAllRoomChargeItem();
		if(CollectionUtils.isEmpty(roomChargeItem)){
			throw new AhanaBusinessException(CommonErrorConstants.NO_RECORDS_FOUND);
		}
		return roomChargeItem;
	}

	@Override
	public RoomChargeItem createRoomChargeItem(RoomChargeItem roomChargeItem) throws AhanaBusinessException {
		if(roomChargeItem==null){
			throw new AhanaBusinessException(CommonErrorConstants.NO_RECORDS_FOUND);
		}
		commonDao.createRoomChargeItem(roomChargeItem);
		return roomChargeItem;
	}

	@Override
	public AhanaVO createOrUpdateConfigs(AhanaVO ahanaVO) throws AhanaBusinessException {
		if(ahanaVO==null){
			throw new AhanaBusinessException(CommonErrorConstants.NO_RECORDS_FOUND);
		}
		commonDao.createOrUpdateConfigs(ahanaVO);
		return ahanaVO;
	}

	@Override
	public void deleteConfigs(String entiryName, String oid) {
		commonDao.deleteConfigs(entiryName,oid);
	}

	@Override
	public AhanaVO getConfigDetailsItemByOid(String queryName, String columnName, String oid) throws AhanaBusinessException {
		AhanaVO ahanaVO=commonDao.getConfigDetailsItemByOid(queryName,columnName,oid);
		if(ahanaVO==null){
			throw new AhanaBusinessException(CommonErrorConstants.NO_RECORDS_FOUND);
		}
		return ahanaVO;
	}

	@Override
	public List<Map<String, String>> getAllSpeciality() throws AhanaBusinessException {
		List<Map<String,String>> specialityDetails=commonDao.getAllSpeciality();
		if(CollectionUtils.isEmpty(specialityDetails)){
			throw new AhanaBusinessException(CommonErrorConstants.NO_RECORDS_FOUND);
		}
		return specialityDetails;
	}

	@Override
	public List<Map<String, String>> getAllRoomType() throws AhanaBusinessException {
		List<Map<String,String>> roomTypeDetails=commonDao.getAllRoomType();
		if(CollectionUtils.isEmpty(roomTypeDetails)){
			throw new AhanaBusinessException(CommonErrorConstants.NO_RECORDS_FOUND);
		}
		return roomTypeDetails;
	}

	@Override
	public List<Map<String, String>> getAllRoomMaintenance() throws AhanaBusinessException {
		List<Map<String,String>> roomMaintenanceDetails=commonDao.getAllRoomMaintenance();
		if(CollectionUtils.isEmpty(roomMaintenanceDetails)){
			throw new AhanaBusinessException(CommonErrorConstants.NO_RECORDS_FOUND);
		}
		return roomMaintenanceDetails;
	}

	@Override
	public List<Map<String, Object>> getAllRoomCharges() throws AhanaBusinessException {
		List<Map<String,Object>> roomChargesDetails=commonDao.getAllRoomCharges();
		if(CollectionUtils.isEmpty(roomChargesDetails)){
			throw new AhanaBusinessException(CommonErrorConstants.NO_RECORDS_FOUND);
		}
		return roomChargesDetails;
	}

	@Override
	public List<Map<String, String>> getFloorValues() throws AhanaBusinessException {
		List<Map<String,String>> floorDetails=commonDao.getFloorValues();
		if(CollectionUtils.isEmpty(floorDetails)){
			throw new AhanaBusinessException(CommonErrorConstants.NO_RECORDS_FOUND);
		}
		return floorDetails;
	}

	@Override
	public List<Map<String, String>> getAllOrganizationModule() throws AhanaBusinessException {
		List<Map<String,String>> organizationModules=commonDao.getAllOrganizationModule();
		if(CollectionUtils.isEmpty(organizationModules)){
			throw new AhanaBusinessException(CommonErrorConstants.NO_RECORDS_FOUND);
		}
		return organizationModules;
	}

	@Override
	public List<Map<String, String>> getSpecialityValues() throws AhanaBusinessException {
		List<Map<String,String>> specialityDetails=commonDao.getSpecialityValues();
		if(CollectionUtils.isEmpty(specialityDetails)){
			throw new AhanaBusinessException(CommonErrorConstants.NO_RECORDS_FOUND);
		}
		return specialityDetails;
	}

	@Override
	public List<Map<String, String>> getWardValues() throws AhanaBusinessException {
		List<Map<String,String>> roomDetails=commonDao.getWardValues();
		if(CollectionUtils.isEmpty(roomDetails)){
			throw new AhanaBusinessException(CommonErrorConstants.NO_RECORDS_FOUND);
		}
		return roomDetails;
	}

	@Override
	public List<Map<String, String>> getCategoryValues() throws AhanaBusinessException {
		List<Map<String,String>> categoryDetails=commonDao.getCategoryValues();
		if(CollectionUtils.isEmpty(categoryDetails)){
			throw new AhanaBusinessException(CommonErrorConstants.NO_RECORDS_FOUND);
		}
		return categoryDetails;
	}

	@Override
	public List<Map<String, String>> getRoomTypesValues() throws AhanaBusinessException {
		List<Map<String,String>> roomTypeDetails=commonDao.getRoomTypesValues();
		if(CollectionUtils.isEmpty(roomTypeDetails)){
			throw new AhanaBusinessException(CommonErrorConstants.NO_RECORDS_FOUND);
		}
		return roomTypeDetails;
	}

	@Override
	public List<Map<String, String>> getRoomChargeItemValues() throws AhanaBusinessException {
		List<Map<String,String>> roomChargeItemDetails=commonDao.getRoomChargeItemValues();
		if(CollectionUtils.isEmpty(roomChargeItemDetails)){
			throw new AhanaBusinessException(CommonErrorConstants.NO_RECORDS_FOUND);
		}
		return roomChargeItemDetails;
	}

	@Override
	public void activateOrDeactivateOrganizationModule(String organizationModuleOids,String type) throws AhanaBusinessException {
		if(StringUtils.isBlank(organizationModuleOids)){
			throw new AhanaBusinessException(CommonErrorConstants.NO_RECORDS_FOUND);
		}
		type=type.equalsIgnoreCase("DEACTIVATE")?"INACT":"ACT";
		commonDao.deactivateOrganizationModule(organizationModuleOids,type);
	}

	@Override
	public List<Map<String, String>> getMaintenancesValues() throws AhanaBusinessException {
		List<Map<String,String>> maintenanceDetails=commonDao.getMaintenancesValues();
		if(CollectionUtils.isEmpty(maintenanceDetails)){
			throw new AhanaBusinessException(CommonErrorConstants.NO_RECORDS_FOUND);
		}
		return maintenanceDetails;
	}

}