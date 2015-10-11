package com.ahana.api.service.common;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
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
@RequestMapping("/services/rest/common")
public class CommonServiceImpl extends BaseService implements CommonService {
	
	private static Logger logger = Logger.getLogger(CommonServiceImpl.class);
	
	@Autowired
	private CommonManager commonManager;

	@Override
	@RequestMapping(value = "/createFloor",method=RequestMethod.POST)
	public String createFloor(@Valid @RequestBody Floor floor, BindingResult errorResult) throws AhanaBusinessException {
		String jsonResponse=null;
		if(errorResult.hasErrors()){
			return handleError(errorResult.getAllErrors());
		}
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("createFloor----start--->"	+ System.currentTimeMillis());
			}
			Floor floor2 = commonManager.createFloor(floor);
			jsonResponse=handleSuccess("floor",floor2);
			if (logger.isDebugEnabled()) {
				logger.debug("createFloor: Success");
			}
		} catch (Throwable exc) {
			jsonResponse = handleError(exc);
			logger.error("createFloor::: Error:::", exc);
		}
		return jsonResponse;
	}
	
	@Override
	@RequestMapping(value = "/getFloorByOid",method=RequestMethod.GET)
	public @ResponseBody String getFloorByOid(@RequestParam("oid") String floorOid) throws AhanaBusinessException {
		String jsonResponse=null;
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("getFloorByOid----start--->"	+ System.currentTimeMillis());
			}
			Floor floor=commonManager.getFloorByOid(floorOid);
			jsonResponse = handleSuccess("floor",floor);
			if (logger.isDebugEnabled()) {
				logger.debug("getFloorByOid: Success");
			}
		} catch (Throwable exc) {
			jsonResponse = handleError(exc);
			logger.error("getFloorByOid::: Error:::", exc);
		}
		return jsonResponse;
	}
	
	@Override
	@RequestMapping(value = "/getAllActiveFloord",method=RequestMethod.GET)
	public @ResponseBody String getAllActiveFloord() throws AhanaBusinessException {
		String jsonResponse=null;
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("getAllActiveFloord----start--->"	+ System.currentTimeMillis());
			}
			List<Map<String,String>> floors=commonManager.getAllActiveFloord();
			jsonResponse = handleSuccess("floorDetails",floors);
			if (logger.isDebugEnabled()) {
				logger.debug("getAllActiveFloord: Success");
			}
		} catch (Throwable exc) {
			jsonResponse = handleError(exc);
			logger.error("getAllActiveFloord::: Error:::", exc);
		}
		return jsonResponse;
	}
	
	@Override
	@RequestMapping(value = "/createWard",method=RequestMethod.POST)
	public String createWard(@Valid @RequestBody Ward ward, BindingResult errorResult) throws AhanaBusinessException {
		String jsonResponse=null;
		if(errorResult.hasErrors()){
			return handleError(errorResult.getAllErrors());
		}
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("createWard----start--->"	+ System.currentTimeMillis());
			}
			Ward ward2 = commonManager.createWard(ward);
			jsonResponse=handleSuccess("ward",ward2);
			if (logger.isDebugEnabled()) {
				logger.debug("createWard: Success");
			}
		} catch (Throwable exc) {
			jsonResponse = handleError(exc);
			logger.error("createWard::: Error:::", exc);
		}
		return jsonResponse;
	}
	
	@Override
	@RequestMapping(value = "/getWardByOid",method=RequestMethod.GET)
	public @ResponseBody String getWardByOid(@RequestParam("oid") String wardOid) throws AhanaBusinessException {
		String jsonResponse=null;
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("getWardByOid----start--->"	+ System.currentTimeMillis());
			}
			Ward ward=commonManager.getWardByOid(wardOid);
			jsonResponse = handleSuccess("ward",ward);
			if (logger.isDebugEnabled()) {
				logger.debug("getWardByOid: Success");
			}
		} catch (Throwable exc) {
			jsonResponse = handleError(exc);
			logger.error("getWardByOid::: Error:::", exc);
		}
		return jsonResponse;
	}
	
	@Override
	@RequestMapping(value = "/createRoom",method=RequestMethod.POST)
	public String createRoom(@Valid @RequestBody Room room, BindingResult errorResult) throws AhanaBusinessException {
		String jsonResponse=null;
		if(errorResult.hasErrors()){
			return handleError(errorResult.getAllErrors());
		}
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("createRoom----start--->"	+ System.currentTimeMillis());
			}
			Room room2 = commonManager.createRoom(room);
			jsonResponse=handleSuccess("room",room2);
			if (logger.isDebugEnabled()) {
				logger.debug("createRoom: Success");
			}
		} catch (Throwable exc) {
			jsonResponse = handleError(exc);
			logger.error("createRoom::: Error:::", exc);
		}
		return jsonResponse;
	}
	
	@Override
	@RequestMapping(value = "/getRoomByOid",method=RequestMethod.GET)
	public @ResponseBody String getRoomByOid(@RequestParam("oid") String roomOid) throws AhanaBusinessException {
		String jsonResponse=null;
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("getRoomByOid----start--->"	+ System.currentTimeMillis());
			}
			Room room=commonManager.getRoomByOid(roomOid);
			jsonResponse = handleSuccess("room",room);
			if (logger.isDebugEnabled()) {
				logger.debug("getRoomByOid: Success");
			}
		} catch (Throwable exc) {
			jsonResponse = handleError(exc);
			logger.error("getRoomByOid::: Error:::", exc);
		}
		return jsonResponse;
	}

	
}
