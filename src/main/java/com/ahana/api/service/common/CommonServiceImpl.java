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
import com.ahana.api.domain.common.AccountHead;
import com.ahana.api.domain.common.Floor;
import com.ahana.api.domain.common.Procedures;
import com.ahana.api.domain.common.Room;
import com.ahana.api.domain.common.RoomChargeItem;
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
	@RequestMapping(value = "/getAllActiveFloor",method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getAllActiveFloor() throws AhanaBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getAllActiveFloor----start--->"	+ System.currentTimeMillis());
		}
		List<Map<String,String>> floors=commonManager.getAllActiveFloord();
		if (logger.isDebugEnabled()) {
			logger.debug("getAllActiveFloor: Success");
		}
		return handleSuccess("floorDetails",floors);
	}
	
	@Override
	@RequestMapping(value = "/deleteFloor",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> deleteFloor(@RequestParam("oid") String floorOid) throws AhanaBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("deleteFloor----start--->"	+ System.currentTimeMillis());
		}
		commonManager.deleteFloor(floorOid);
		if (logger.isDebugEnabled()) {
			logger.debug("deleteFloor: Success");
		}
		return handleStatus();
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
	@RequestMapping(value = "/deleteWard",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> deleteWard(@RequestParam("oid") String wardOid) throws AhanaBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("deleteWard----start--->"	+ System.currentTimeMillis());
		}
		commonManager.deleteWard(wardOid);
		if (logger.isDebugEnabled()) {
			logger.debug("deleteWard: Success");
		}
		return handleStatus();
	}
	
	@Override
	@RequestMapping(value = "/getAllWards",method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getAllWards() throws AhanaBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getAllWards----start--->"	+ System.currentTimeMillis());
		}
		List<Map<String, String>> wardDetails=commonManager.getAllWards();
		if (logger.isDebugEnabled()) {
			logger.debug("getAllWards: Success");
		}
		return handleSuccess("wardDetails",wardDetails);
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
	
	@Override
	@RequestMapping(value = "/getAllRooms",method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getAllRooms() throws AhanaBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getAllRooms----start--->"	+ System.currentTimeMillis());
		}
		List<Map<String, String>> wardDetails=commonManager.getAllRooms();
		if (logger.isDebugEnabled()) {
			logger.debug("getAllRooms: Success");
		}
		return handleSuccess("roomDetails",wardDetails);
	}
	
	@Override
	@RequestMapping(value = "/deleteRoom",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> deleteRoom(@RequestParam("oid") String roomOid) throws AhanaBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("deleteRoom----start--->"	+ System.currentTimeMillis());
		}
		commonManager.deleteRoom(roomOid);
		if (logger.isDebugEnabled()) {
			logger.debug("deleteRoom: Success");
		}
		return handleStatus();
	}
	
	@Override
	@RequestMapping(value = "/createAccountHead",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> createAccountHead(@Valid @RequestBody AccountHead accountHead) throws AhanaBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("createAccountHead----start--->"	+ System.currentTimeMillis());
		}
		AccountHead accountHead2 = commonManager.createAccountHead(accountHead);
		if (logger.isDebugEnabled()) {
			logger.debug("createAccountHead: Success");
		}
		return handleSuccess("accountHead",accountHead2);
	}
	
	@Override
	@RequestMapping(value = "/deleteAccountHead",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> deleteAccountHead(@RequestParam("oid") String accountHeadOid) throws AhanaBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("deleteAccountHead----start--->"	+ System.currentTimeMillis());
		}
		commonManager.deleteAccountHead(accountHeadOid);
		if (logger.isDebugEnabled()) {
			logger.debug("deleteAccountHead: Success");
		}
		return handleStatus();
	}
	
	@Override
	@RequestMapping(value = "/getAccountHeadByOid",method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getAccountHeadByOid(@RequestParam("oid") String accountHeadOid) throws AhanaBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getAccountHeadByOid----start--->"	+ System.currentTimeMillis());
		}
		AccountHead accountHead=commonManager.getAccountHeadByOid(accountHeadOid);
		if (logger.isDebugEnabled()) {
			logger.debug("getAccountHeadByOid: Success");
		}
		return handleSuccess("accountHead",accountHead);
	}
	
	@Override
	@RequestMapping(value = "/getAllAccountHead",method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getAllAccountHead() throws AhanaBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getAllAccountHead----start--->"	+ System.currentTimeMillis());
		}
		List<Map<String, String>> accountHeadDetails=commonManager.getAllAccountHead();
		if (logger.isDebugEnabled()) {
			logger.debug("getAllAccountHead: Success");
		}
		return handleSuccess("accountHeadDetails",accountHeadDetails);
	}
	
	@Override
	@RequestMapping(value = "/createProcedures",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> createProcedures(@Valid @RequestBody Procedures procedures) throws AhanaBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("createProcedures----start--->"	+ System.currentTimeMillis());
		}
		Procedures procedures2 = commonManager.createProcedures(procedures);
		if (logger.isDebugEnabled()) {
			logger.debug("createProcedures: Success");
		}
		return handleSuccess("procedures",procedures2);
	}
	
	@Override
	@RequestMapping(value = "/deleteProcedures",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> deleteProcedures(@RequestParam("oid") String proceduresOid) throws AhanaBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("deleteProcedures----start--->"	+ System.currentTimeMillis());
		}
		commonManager.deleteProcedures(proceduresOid);
		if (logger.isDebugEnabled()) {
			logger.debug("deleteProcedures: Success");
		}
		return handleStatus();
	}
	
	@Override
	@RequestMapping(value = "/getProceduresByOid",method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getProceduresByOid(@RequestParam("oid") String proceduresOid) throws AhanaBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getProceduresByOid----start--->"	+ System.currentTimeMillis());
		}
		Procedures procedures=commonManager.getProceduresByOid(proceduresOid);
		if (logger.isDebugEnabled()) {
			logger.debug("getProceduresByOid: Success");
		}
		return handleSuccess("procedures",procedures);
	}
	
	@Override
	@RequestMapping(value = "/getAllProcedures",method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getAllProcedures() throws AhanaBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getAllProcedures----start--->"	+ System.currentTimeMillis());
		}
		List<Map<String, String>> proceduresDetails=commonManager.getAllProcedures();
		if (logger.isDebugEnabled()) {
			logger.debug("getAllProcedures: Success");
		}
		return handleSuccess("proceduresDetails",proceduresDetails);
	}
	
	@Override
	@RequestMapping(value = "/createRoomChargeItem",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> createRoomChargeItem(@Valid @RequestBody RoomChargeItem roomChargeItem) throws AhanaBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("createRoomChargeItem----start--->"	+ System.currentTimeMillis());
		}
		RoomChargeItem roomChargeItem2 = commonManager.createRoomChargeItem(roomChargeItem);
		if (logger.isDebugEnabled()) {
			logger.debug("createRoomChargeItem: Success");
		}
		return handleSuccess("procedures",roomChargeItem2);
	}
	
	@Override
	@RequestMapping(value = "/deleteRoomChargeItem",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> deleteRoomChargeItem(@RequestParam("oid") String roomChargeItemOid) throws AhanaBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("deleteRoomChargeItem----start--->"	+ System.currentTimeMillis());
		}
		commonManager.deleteRoomChargeItem(roomChargeItemOid);
		if (logger.isDebugEnabled()) {
			logger.debug("deleteRoomChargeItem: Success");
		}
		return handleStatus();
	}
	
	@Override
	@RequestMapping(value = "/getRoomChargeItemByOid",method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getRoomChargeItemByOid(@RequestParam("oid") String roomChargeItemOid) throws AhanaBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getRoomChargeItemByOid----start--->"	+ System.currentTimeMillis());
		}
		RoomChargeItem roomChargeItem=commonManager.getRoomChargeItemByOid(roomChargeItemOid);
		if (logger.isDebugEnabled()) {
			logger.debug("getRoomChargeItemByOid: Success");
		}
		return handleSuccess("roomChargeItem",roomChargeItem);
	}
	
	@Override
	@RequestMapping(value = "/getAllRoomChargeItem",method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getAllRoomChargeItem() throws AhanaBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getAllRoomChargeItem----start--->"	+ System.currentTimeMillis());
		}
		List<Map<String, String>> roomChargeItemDetails=commonManager.getAllRoomChargeItem();
		if (logger.isDebugEnabled()) {
			logger.debug("getAllRoomChargeItem: Success");
		}
		return handleSuccess("roomChargeItemDetails",roomChargeItemDetails);
	}
}