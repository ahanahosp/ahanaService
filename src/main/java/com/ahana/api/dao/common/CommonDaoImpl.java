package com.ahana.api.dao.common;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ahana.api.common.Constants;
import com.ahana.api.dao.common.AhanaDaoSupport;
import com.ahana.api.domain.common.Floor;
import com.ahana.api.domain.common.Room;
import com.ahana.api.domain.common.Ward;

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
	public Ward createFloor(Ward ward) {
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
			query="select oid as oid,floor_name as name from floor where status='"+Constants.ACT+"'";
			sqlQuery=getSessionFactory().getCurrentSession().createSQLQuery(query)
					.addScalar("oid")
					.addScalar("name")
					.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			list = sqlQuery.list();
		}finally{
			sqlQuery=null;
			query=null;
		}
		return list;
	}

	@Override
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

}