package com.ahana.api.service.common;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ahana.api.common.BaseService;
import com.ahana.api.domain.common.Floor;
import com.ahana.api.domain.common.Room;
import com.ahana.api.domain.common.Ward;
import com.ahana.api.manager.common.CommonManager;
import com.ahana.api.system.security.exception.AhanaBusinessException;

@RestController
@RequestMapping("/services/rest/secure/common")
public class CommonServiceImpl extends BaseService implements CommonService {
	
	private static Logger logger = Logger.getLogger(CommonServiceImpl.class);
	
	@Autowired
	private CommonManager commonManager;

	@Override
	@RequestMapping(value = "/createFloor",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> createFloor(@Valid @RequestBody Floor floor) throws AhanaBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("createFloor----start--->"	+ System.currentTimeMillis());
		}
		Floor floor2 = commonManager.createFloor(floor);
		if (logger.isDebugEnabled()) {
			logger.debug("createFloor: Success");
		}
		return handleSuccess("floor",floor2);
	}
	
	@Override
	@RequestMapping(value = "/getFloorByOid",method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getFloorByOid(@RequestParam("oid") String floorOid) throws AhanaBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getFloorByOid----start--->"	+ System.currentTimeMillis());
		}
		Floor floor=commonManager.getFloorByOid(floorOid);
		if (logger.isDebugEnabled()) {
			logger.debug("getFloorByOid: Success");
		}
		return handleSuccess("floor",floor);
	}
	
	@Override
	@RequestMapping(value = "/getAllActiveFloord",method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getAllActiveFloor() throws AhanaBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getAllActiveFloord----start--->"	+ System.currentTimeMillis());
		}
		List<Map<String,String>> floors=commonManager.getAllActiveFloord();
		if (logger.isDebugEnabled()) {
			logger.debug("getAllActiveFloord: Success");
		}
		return handleSuccess("floorDetails",floors);
		
	}
	
	@Override
	@RequestMapping(value = "/createWard",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> createWard(@Valid @RequestBody Ward ward) throws AhanaBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("createWard----start--->"	+ System.currentTimeMillis());
		}
		Ward ward2 = commonManager.createWard(ward);
		if (logger.isDebugEnabled()) {
			logger.debug("createWard: Success");
		}
		return handleSuccess("ward",ward2);
	}
	
	@Override
	@RequestMapping(value = "/getWardByOid",method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getWardByOid(@RequestParam("oid") String wardOid) throws AhanaBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getWardByOid----start--->"	+ System.currentTimeMillis());
		}
		Ward ward=commonManager.getWardByOid(wardOid);
		if (logger.isDebugEnabled()) {
			logger.debug("getWardByOid: Success");
		}
		return handleSuccess("ward",ward);
	}
	
	@Override
	@RequestMapping(value = "/createRoom",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> createRoom(@Valid @RequestBody Room room) throws AhanaBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("createRoom----start--->"	+ System.currentTimeMillis());
		}
		Room room2 = commonManager.createRoom(room);
		if (logger.isDebugEnabled()) {
			logger.debug("createRoom: Success");
		}
		return handleSuccess("room",room2);
	}
	
	@Override
	@RequestMapping(value = "/getRoomByOid",method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getRoomByOid(@RequestParam("oid") String roomOid) throws AhanaBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getRoomByOid----start--->"	+ System.currentTimeMillis());
		}
		Room room=commonManager.getRoomByOid(roomOid);
		if (logger.isDebugEnabled()) {
			logger.debug("getRoomByOid: Success");
		}
		return handleSuccess("room",room);
	}
}