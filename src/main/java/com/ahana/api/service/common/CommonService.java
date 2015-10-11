package com.ahana.api.service.common;

import org.springframework.validation.BindingResult;

import com.ahana.api.domain.common.Floor;
import com.ahana.api.domain.common.Room;
import com.ahana.api.domain.common.Ward;
import com.ahana.api.system.security.exception.AhanaBusinessException;

public interface CommonService {

	String createFloor(Floor floor, BindingResult errorResult)throws AhanaBusinessException;

	String createWard(Ward ward, BindingResult errorResult)	throws AhanaBusinessException;

	String getFloorByOid(String floorOid) throws AhanaBusinessException;

	String getWardByOid(String wardOid) throws AhanaBusinessException;

	String getAllActiveFloord() throws AhanaBusinessException;

	String createRoom(Room room, BindingResult errorResult)	throws AhanaBusinessException;

	String getRoomByOid(String roomOid) throws AhanaBusinessException;

}
