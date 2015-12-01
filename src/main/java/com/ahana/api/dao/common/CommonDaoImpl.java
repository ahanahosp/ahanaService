package com.ahana.api.dao.common;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ahana.api.domain.common.AccountHead;
import com.ahana.api.domain.common.Floor;
import com.ahana.api.domain.common.Procedures;
import com.ahana.api.domain.common.Room;
import com.ahana.api.domain.common.RoomChargeItem;
import com.ahana.api.domain.common.Ward;
import com.ahana.commons.system.dao.common.AhanaDaoSupport;
import com.ahana.commons.system.domain.common.AhanaVO;
import com.ahana.commons.system.security.util.CommonUtils;
import com.ahana.commons.system.security.util.Constants;

@Transactional(readOnly = false)
public class CommonDaoImpl extends AhanaDaoSupport implements CommonDao {

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public Floor createFloor(Floor floor) {
		saveOrUpdate(floor);
		return floor;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public Ward createWard(Ward ward) {
		saveOrUpdate(ward);
		return ward;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Floor getFloorByOid(String floorOid) {
		List<Floor> floors = findByNamedQuery("getFloorByOid", "floorOid", floorOid);
		if(CollectionUtils.isNotEmpty(floors)){
			return floors.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Ward getWardByOid(String wardOid) {
		List<Ward> wards = findByNamedQuery("getWardByOid", "wardOid", wardOid);
		if(CollectionUtils.isNotEmpty(wards)){
			return wards.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String,String>> getAllActiveFloord() {
		Query sqlQuery=null;
		List<Map<String, String>> list=null;
		String query=null;
		try{
			query="select oid as oid,floor_name as floorName,floor_code as floorCode,status as status from floor";
			sqlQuery=getSessionFactory().getCurrentSession().createSQLQuery(query)
					.addScalar("oid")
					.addScalar("floorName")
					.addScalar("floorCode")
					.addScalar("status")
					.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			list = sqlQuery.list();
		}finally{
			sqlQuery=null;
			query=null;
		}
		return list;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public Room createRoom(Room room) {
		saveOrUpdate(room);
		return room;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Room getRoomByOid(String roomOid) {
		List<Room> rooms = findByNamedQuery("getRoomByOid", "roomOid", roomOid);
		if(CollectionUtils.isNotEmpty(rooms)){
			return rooms.get(0);
		}
		return null;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void deleteFloor(String floorOid) {
		Query q = getSessionFactory().getCurrentSession().createQuery("update Floor set status='INACT' where oid ='"+floorOid+"'");
		q.executeUpdate();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void deleteWard(String wardOid) {
		Query q = getSessionFactory().getCurrentSession().createQuery("update Ward set status='INACT' where oid ='"+wardOid+"'");
		q.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, String>> getAllWards() {
		Query sqlQuery=null;
		List<Map<String, String>> list=null;
		String query=null;
		try{
			query="select w.oid as oid,w.ward_name as wardName,w.status as status,f.floor_name as floorName,f.oid as floorOid from ward w"
					+ " join floor f on w.floor_oid=f.oid ";
			sqlQuery=getSessionFactory().getCurrentSession().createSQLQuery(query)
					.addScalar("oid")
					.addScalar("wardName")
					.addScalar("status")
					.addScalar("floorName")
					.addScalar("floorOid")
					.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			list = sqlQuery.list();
		}finally{
			sqlQuery=null;
			query=null;
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, String>> getAllRooms() {
		Query sqlQuery=null;
		List<Map<String, String>> list=null;
		String query=null;
		try{
			query="select r.oid as oid,r.bed_name as bedName,r.occupancy_status as occupancy,rm.maintenance_name as maintenanceName,"
					+ "rm.oid as maintenanceOid,r.status as status,w.ward_name as wardName,w.oid as wardOid from room r join ward w "
					+ "on r.ward_oid=w.oid join room_maintance_details rm on r.maintenance_oid=rm.oid";
			sqlQuery=getSessionFactory().getCurrentSession().createSQLQuery(query)
					.addScalar("oid")
					.addScalar("bedName")
					.addScalar("occupancy")
					.addScalar("maintenanceName")
					.addScalar("maintenanceOid")
					.addScalar("status")
					.addScalar("wardName")
					.addScalar("wardOid")
					.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			list = sqlQuery.list();
		}finally{
			sqlQuery=null;
			query=null;
		}
		return list;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void deleteRoom(String roomOid) {
		Query q = getSessionFactory().getCurrentSession().createQuery("update Room set status='INACT' where oid ='"+roomOid+"'");
		q.executeUpdate();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void deleteAccountHead(String accountHeadOid) {
		Query q = getSessionFactory().getCurrentSession().createQuery("update AccountHead set status='INACT' where oid ='"+accountHeadOid+"'");
		q.executeUpdate();		
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public AccountHead createAccountHead(AccountHead accountHead) {
		saveOrUpdate(accountHead);
		return accountHead;
	}

	@SuppressWarnings("unchecked")
	@Override
	public AccountHead getAccountHeadByOid(String accountHeadOid) {
		List<AccountHead> accountHeads = findByNamedQuery("getAccountHeadByOid", "accountHeadOid", accountHeadOid);
		if(CollectionUtils.isNotEmpty(accountHeads)){
			return accountHeads.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, String>> getAllAccountHead() {
		Query sqlQuery=null;
		List<Map<String, String>> list=null;
		String query=null;
		try{
			query="select ci.oid as oid,ci.category as category,ci.description as description,ci.status as status "
					+ "from category_item ci";
			sqlQuery=getSessionFactory().getCurrentSession().createSQLQuery(query)
					.addScalar("oid")
					.addScalar("category")
					.addScalar("description")
					.addScalar("status")
					.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			list = sqlQuery.list();
		}finally{
			sqlQuery=null;
			query=null;
		}
		return list;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public Procedures createProcedures(Procedures procedures) {
		saveOrUpdate(procedures);
		return procedures;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void deleteProcedures(String proceduresOid) {
		Query q = getSessionFactory().getCurrentSession().createQuery("update Procedures set status='INACT' where oid ='"+proceduresOid+"'");
		q.executeUpdate();		
	}

	@SuppressWarnings("unchecked")
	@Override
	public Procedures getProceduresByOid(String proceduresOid) {
		List<Procedures> procedures = findByNamedQuery("getProceduresByOid", "proceduresOid", proceduresOid);
		if(CollectionUtils.isNotEmpty(procedures)){
			return procedures.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, String>> getAllProcedures() {
		Query sqlQuery=null;
		List<Map<String, String>> list=null;
		String query=null;
		try{
			query="select p.oid as oid,p.procedures_name as proceduresName,p.status as status "
					+ "from procedures p";
			sqlQuery=getSessionFactory().getCurrentSession().createSQLQuery(query)
					.addScalar("oid")
					.addScalar("proceduresName")
					.addScalar("status")
					.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			list = sqlQuery.list();
		}finally{
			sqlQuery=null;
			query=null;
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public RoomChargeItem getRoomChargeItemByOid(String roomChargeItemOid) {
		List<RoomChargeItem> roomChargeItems = findByNamedQuery("getRoomChargeItemByOid", "roomChargeItemOid", roomChargeItemOid);
		if(CollectionUtils.isNotEmpty(roomChargeItems)){
			return roomChargeItems.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, String>> getAllRoomChargeItem() {
		Query sqlQuery=null;
		List<Map<String, String>> list=null;
		String query=null;
		try{
			query="select r.oid as oid,r.item as item,r.code as code,r.description as description,r.status as status, "
					+ "c.category as category from room_charges_item r join category_item c on r.category_oid=c.oid";
			sqlQuery=getSessionFactory().getCurrentSession().createSQLQuery(query)
					.addScalar("oid")
					.addScalar("item")
					.addScalar("code")
					.addScalar("description")
					.addScalar("status")
					.addScalar("category")
					.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			list = sqlQuery.list();
		}finally{
			sqlQuery=null;
			query=null;
		}
		return list;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void deleteRoomChargeItem(String roomChargeItemOid) {
		Query q = getSessionFactory().getCurrentSession().createQuery("update RoomChargeItem set status='INACT' where oid ='"+roomChargeItemOid+"'");
		q.executeUpdate();	
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public RoomChargeItem createRoomChargeItem(RoomChargeItem roomChargeItem) {
		saveOrUpdate(roomChargeItem);
		return roomChargeItem;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public AhanaVO createOrUpdateConfigs(AhanaVO ahanaVO) {
		saveOrUpdate(ahanaVO);
		return ahanaVO;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void deleteConfigs(String entiryName, String oid) {
		Query q = getSessionFactory().getCurrentSession().createQuery("update '"+entiryName+"' set status='INACT' where oid ='"+oid+"'");
		q.executeUpdate();			
	}

	@SuppressWarnings("unchecked")
	@Override
	public AhanaVO getConfigDetailsItemByOid(String queryName, String columnName, String oid) {
		List<AhanaVO> configDetails = findByNamedQuery(queryName, columnName, oid);
		if(CollectionUtils.isNotEmpty(configDetails)){
			return configDetails.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, String>> getAllSpeciality() {
		Query sqlQuery=null;
		List<Map<String, String>> list=null;
		String query=null;
		try{
			query="select s.oid as oid,s.speciality_name as specialityName,s.status as status from speciality_details s";
			sqlQuery=getSessionFactory().getCurrentSession().createSQLQuery(query)
					.addScalar("oid")
					.addScalar("specialityName")
					.addScalar("status")
					.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			list = sqlQuery.list();
		}finally{
			sqlQuery=null;
			query=null;
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, String>> getAllRoomType() {
		Query sqlQuery=null;
		List<Map<String, String>> list=null;
		String query=null;
		try{
			query="select r.oid as oid,r.room_name as roomName,r.status as status from room_type r";
			sqlQuery=getSessionFactory().getCurrentSession().createSQLQuery(query)
					.addScalar("oid")
					.addScalar("roomName")
					.addScalar("status")
					.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			list = sqlQuery.list();
		}finally{
			sqlQuery=null;
			query=null;
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, String>> getAllRoomMaintenance() {
		Query sqlQuery=null;
		List<Map<String, String>> list=null;
		String query=null;
		try{
			query="select m.oid as oid,m.maintenance_name as maintenanceName,m.status as status from room_maintance_details m";
			sqlQuery=getSessionFactory().getCurrentSession().createSQLQuery(query)
					.addScalar("oid")
					.addScalar("maintenanceName")
					.addScalar("status")
					.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			list = sqlQuery.list();
		}finally{
			sqlQuery=null;
			query=null;
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> getAllRoomCharges() {
		Query sqlQuery=null;
		List<Map<String, Object>> list=null;
		String query=null;
		try{
			query="select r.oid as oid,r.charge as charge,r.status as status,rc.item as item,rt.room_name as roomName "
					+ "from room_charges r join room_charges_item rc on r.room_charge_items_oid=rc.oid join room_type rt on "
					+ "r.room_type_oid=rt.oid";
			sqlQuery=getSessionFactory().getCurrentSession().createSQLQuery(query)
					.addScalar("oid")
					.addScalar("charge")
					.addScalar("status")
					.addScalar("item")
					.addScalar("roomName")
					.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			list = sqlQuery.list();
		}finally{
			sqlQuery=null;
			query=null;
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, String>> getFloorValues() {
		Query sqlQuery=null;
		List<Map<String, String>> list=null;
		String query=null;
		try{
			query="select f.oid as value,f.floor_name as label from floor f where f.status='"+Constants.ACT+"'";
			sqlQuery=getSessionFactory().getCurrentSession().createSQLQuery(query)
					.addScalar("value")
					.addScalar("label")
					.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			list = sqlQuery.list();
		}finally{
			sqlQuery=null;
			query=null;
		}
		return list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, String>> getSpecialityValues() {
		Query sqlQuery=null;
		List<Map<String, String>> list=null;
		String query=null;
		try{
			query="select s.oid as value,s.speciality_name as label from speciality_details s where s.status='"+Constants.ACT+"'";
			sqlQuery=getSessionFactory().getCurrentSession().createSQLQuery(query)
					.addScalar("value")
					.addScalar("label")
					.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			list = sqlQuery.list();
		}finally{
			sqlQuery=null;
			query=null;
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, String>> getAllOrganizationModule() {
		Query sqlQuery=null;
		List<Map<String, String>> list=null;
		String query=null;
		try{
			query="select o.oid as oid,o.module_name as moduleName,o.status as status from organization_module o";
			sqlQuery=getSessionFactory().getCurrentSession().createSQLQuery(query)
					.addScalar("oid")
					.addScalar("moduleName")
					.addScalar("status")
					.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			list = sqlQuery.list();
		}finally{
			sqlQuery=null;
			query=null;
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, String>> getWardValues() {
		Query sqlQuery=null;
		List<Map<String, String>> list=null;
		String query=null;
		try{
			query="select w.oid as value,w.ward_name as label from ward w where w.status='"+Constants.ACT+"'";
			sqlQuery=getSessionFactory().getCurrentSession().createSQLQuery(query)
					.addScalar("value")
					.addScalar("label")
					.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			list = sqlQuery.list();
		}finally{
			sqlQuery=null;
			query=null;
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, String>> getCategoryValues() {
		Query sqlQuery=null;
		List<Map<String, String>> list=null;
		String query=null;
		try{
			query="select c.oid as value,c.category as label from category_item c where c.status='"+Constants.ACT+"'";
			sqlQuery=getSessionFactory().getCurrentSession().createSQLQuery(query)
					.addScalar("value")
					.addScalar("label")
					.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			list = sqlQuery.list();
		}finally{
			sqlQuery=null;
			query=null;
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, String>> getRoomTypesValues() {
		Query sqlQuery=null;
		List<Map<String, String>> list=null;
		String query=null;
		try{
			query="select r.oid as value,r.room_name as label from room_type r where r.status='"+Constants.ACT+"'";
			sqlQuery=getSessionFactory().getCurrentSession().createSQLQuery(query)
					.addScalar("value")
					.addScalar("label")
					.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			list = sqlQuery.list();
		}finally{
			sqlQuery=null;
			query=null;
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, String>> getRoomChargeItemValues() {
		Query sqlQuery=null;
		List<Map<String, String>> list=null;
		String query=null;
		try{
			query="select r.oid as value,r.item as label from room_charges_item r where r.status='"+Constants.ACT+"'";
			sqlQuery=getSessionFactory().getCurrentSession().createSQLQuery(query)
					.addScalar("value")
					.addScalar("label")
					.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			list = sqlQuery.list();
		}finally{
			sqlQuery=null;
			query=null;
		}
		return list;
	}

	@Override
	public void deactivateOrganizationModule(String organizationModuleOids) {
		String commaSeprateOids=CommonUtils.convertCommoaSeprated(organizationModuleOids);
		SQLQuery sqlQuery = getSessionFactory().getCurrentSession().createSQLQuery("update organization_module set status='INACT' where oid in("+commaSeprateOids+")");
		sqlQuery.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, String>> getMaintenancesValues() {
		Query sqlQuery=null;
		List<Map<String, String>> list=null;
		String query=null;
		try{
			query="select r.oid as value,r.maintenance_name as label from room_maintance_details r where r.status='"+Constants.ACT+"'";
			sqlQuery=getSessionFactory().getCurrentSession().createSQLQuery(query)
					.addScalar("value")
					.addScalar("label")
					.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			list = sqlQuery.list();
		}finally{
			sqlQuery=null;
			query=null;
		}
		return list;
	}
}