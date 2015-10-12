package com.ahana.api.manager.common;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ahana.api.common.ErrorConstants;
import com.ahana.api.dao.common.CommonDao;
import com.ahana.api.domain.common.Floor;
import com.ahana.api.domain.common.Room;
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

}