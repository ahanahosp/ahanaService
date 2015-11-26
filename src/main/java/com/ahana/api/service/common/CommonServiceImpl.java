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

import com.ahana.api.domain.common.AccountHead;
import com.ahana.api.domain.common.Floor;
import com.ahana.api.domain.common.Organization;
import com.ahana.api.domain.common.Procedures;
import com.ahana.api.domain.common.Room;
import com.ahana.api.domain.common.RoomChargeItem;
import com.ahana.api.domain.common.RoomCharges;
import com.ahana.api.domain.common.RoomMaintenanceDetails;
import com.ahana.api.domain.common.RoomType;
import com.ahana.api.domain.common.SpecialityDetails;
import com.ahana.api.domain.common.Ward;
import com.ahana.api.manager.common.CommonManager;
import com.ahana.commons.system.domain.common.AhanaVO;
import com.ahana.commons.system.security.exception.AhanaBusinessException;
import com.ahana.commons.system.service.BaseService;

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
	@RequestMapping(value = "/getFloorValues",method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getFloorValues() throws AhanaBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getFloorValues----start--->"	+ System.currentTimeMillis());
		}
		List<Map<String, String>> floorDetails=commonManager.getFloorValues();
		if (logger.isDebugEnabled()) {
			logger.debug("getFloorValues: Success");
		}
		return handleSuccess("floorDetails",floorDetails);
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
	
	@Override
	@RequestMapping(value = "/createSpeciality",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> createSpeciality(@Valid @RequestBody SpecialityDetails specialityDetails) throws AhanaBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("createSpeciality----start--->"+ System.currentTimeMillis());
		}
		AhanaVO specialityDetails2 =commonManager.createOrUpdateConfigs(specialityDetails);
		if (logger.isDebugEnabled()) {
			logger.debug("createSpeciality: Success");
		}
		return handleSuccess("specialityDetails",(SpecialityDetails)specialityDetails2);
	}
	
	@Override
	@RequestMapping(value = "/deleteSpeciality",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> deleteSpeciality(@RequestParam("oid") String specialityDetailsOid) throws AhanaBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("deleteSpeciality----start--->"	+ System.currentTimeMillis());
		}
			commonManager.deleteConfigs("SpecialityDetails",specialityDetailsOid);
		if (logger.isDebugEnabled()) {
			logger.debug("deleteSpeciality: Success");
		}
		return handleStatus();
	}
	
	@Override
	@RequestMapping(value = "/getSpecialityByOid",method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getSpecialityByOid(@RequestParam("oid") String specialityOid) throws AhanaBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getSpecialityByOid----start--->"	+ System.currentTimeMillis());
		}
		AhanaVO ahanaVO=commonManager.getConfigDetailsItemByOid("getSpecialityDetailsByOid","specialityDetailOid",specialityOid);
		if (logger.isDebugEnabled()) {
			logger.debug("getSpecialityByOid: Success");
		}
		return handleSuccess("speciality",(SpecialityDetails)ahanaVO);
	}
	
	@Override
	@RequestMapping(value = "/getAllSpeciality",method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getAllSpeciality() throws AhanaBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getAllSpeciality----start--->"	+ System.currentTimeMillis());
		}
		List<Map<String, String>> specialityDetails=commonManager.getAllSpeciality();
		if (logger.isDebugEnabled()) {
			logger.debug("getAllSpeciality: Success");
		}
		return handleSuccess("specialityDetails",specialityDetails);
	}
	
	
	
	@Override
	@RequestMapping(value = "/createRoomType",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> createRoomType(@Valid @RequestBody RoomType roomType) throws AhanaBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("createRoomType----start--->"+ System.currentTimeMillis());
		}
		AhanaVO ahanaVO =commonManager.createOrUpdateConfigs(roomType);
		if (logger.isDebugEnabled()) {
			logger.debug("createRoomType: Success");
		}
		return handleSuccess("roomType",(RoomType)ahanaVO);
	}
	
	@Override
	@RequestMapping(value = "/deleteRoomType",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> deleteRoomType(@RequestParam("oid") String roomTypeOid) throws AhanaBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("deleteRoomType----start--->"	+ System.currentTimeMillis());
		}
			commonManager.deleteConfigs("RoomType",roomTypeOid);
		if (logger.isDebugEnabled()) {
			logger.debug("deleteRoomType: Success");
		}
		return handleStatus();
	}
	
	@Override
	@RequestMapping(value = "/getRoomTypeByOid",method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getRoomTypeByOid(@RequestParam("oid") String roomTypeOid) throws AhanaBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getRoomTypeByOid----start--->"	+ System.currentTimeMillis());
		}
		AhanaVO ahanaVO=commonManager.getConfigDetailsItemByOid("getRoomTypeByOid","roomTypeOid",roomTypeOid);
		if (logger.isDebugEnabled()) {
			logger.debug("getRoomTypeByOid: Success");
		}
		return handleSuccess("roomType",(RoomType)ahanaVO);
	}
	
	@Override
	@RequestMapping(value = "/getAllRoomType",method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getAllRoomType() throws AhanaBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getAllRoomType----start--->"	+ System.currentTimeMillis());
		}
		List<Map<String, String>> roomTypeDetails=commonManager.getAllRoomType();
		if (logger.isDebugEnabled()) {
			logger.debug("getAllRoomType: Success");
		}
		return handleSuccess("roomTypeDetails",roomTypeDetails);
	}
	
	@Override
	@RequestMapping(value = "/createRoomMaintenance",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> createRoomMaintenance(@Valid @RequestBody RoomMaintenanceDetails roomMaintenanceDetails) throws AhanaBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("createRoomMaintenance----start--->"+ System.currentTimeMillis());
		}
		AhanaVO ahanaVO =commonManager.createOrUpdateConfigs(roomMaintenanceDetails);
		if (logger.isDebugEnabled()) {
			logger.debug("createRoomMaintenance: Success");
		}
		return handleSuccess("roomMaintenance",(RoomMaintenanceDetails)ahanaVO);
	}
	
	@Override
	@RequestMapping(value = "/deleteRoomMaintenance",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> deleteRoomMaintenance(@RequestParam("oid") String roomMaintenanceDetailOid) throws AhanaBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("deleteRoomMaintenance----start--->"	+ System.currentTimeMillis());
		}
			commonManager.deleteConfigs("RoomMaintenanceDetails",roomMaintenanceDetailOid);
		if (logger.isDebugEnabled()) {
			logger.debug("deleteRoomMaintenance: Success");
		}
		return handleStatus();
	}
	
	@Override
	@RequestMapping(value = "/getRoomMaintenanceByOid",method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getRoomMaintenanceByOid(@RequestParam("oid") String roomMaintenanceDetails) throws AhanaBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getRoomMaintenanceByOid----start--->"	+ System.currentTimeMillis());
		}
		AhanaVO ahanaVO=commonManager.getConfigDetailsItemByOid("getRoomMaintenanceDetailsByOid","roomMaintenanceDetailsOid",roomMaintenanceDetails);
		if (logger.isDebugEnabled()) {
			logger.debug("getRoomMaintenanceByOid: Success");
		}
		return handleSuccess("roomMaintenance",(RoomMaintenanceDetails)ahanaVO);
	}
	
	@Override
	@RequestMapping(value = "/getAllRoomMaintenance",method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getAllRoomMaintenance() throws AhanaBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getAllRoomMaintenance----start--->"	+ System.currentTimeMillis());
		}
		List<Map<String, String>> roomMaintenanceDetails=commonManager.getAllRoomMaintenance();
		if (logger.isDebugEnabled()) {
			logger.debug("getAllRoomMaintenance: Success");
		}
		return handleSuccess("roomMaintenanceDetails",roomMaintenanceDetails);
	}
	
	@Override
	@RequestMapping(value = "/createRoomCharge",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> createRoomCharge(@Valid @RequestBody RoomCharges roomCharges) throws AhanaBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("createRoomCharge----start--->"+ System.currentTimeMillis());
		}
		AhanaVO ahanaVO =commonManager.createOrUpdateConfigs(roomCharges);
		if (logger.isDebugEnabled()) {
			logger.debug("createRoomCharge: Success");
		}
		return handleSuccess("roomCharge",(RoomCharges)ahanaVO);
	}
	
	@Override
	@RequestMapping(value = "/deleteRoomCharges",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> deleteRoomCharges(@RequestParam("oid") String roomChargeOid) throws AhanaBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("deleteRoomCharges----start--->"	+ System.currentTimeMillis());
		}
			commonManager.deleteConfigs("RoomCharges",roomChargeOid);
		if (logger.isDebugEnabled()) {
			logger.debug("deleteRoomCharges: Success");
		}
		return handleStatus();
	}
	
	@Override
	@RequestMapping(value = "/getRoomChargesByOid",method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getRoomChargesByOid(@RequestParam("oid") String roomChargeOid) throws AhanaBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getRoomChargesByOid----start--->"	+ System.currentTimeMillis());
		}
		AhanaVO ahanaVO=commonManager.getConfigDetailsItemByOid("getRoomChargesByOid","roomChargesOid",roomChargeOid);
		if (logger.isDebugEnabled()) {
			logger.debug("getRoomChargesByOid: Success");
		}
		return handleSuccess("roomCharges",(RoomCharges)ahanaVO);
	}
	
	@Override
	@RequestMapping(value = "/getAllRoomCharges",method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getAllRoomCharges() throws AhanaBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getAllRoomCharges----start--->"	+ System.currentTimeMillis());
		}
		List<Map<String, Object>> roomChargeDetails=commonManager.getAllRoomCharges();
		if (logger.isDebugEnabled()) {
			logger.debug("getAllRoomCharges: Success");
		}
		return handleSuccess("roomChargesDetails",roomChargeDetails);
	}
	
	@Override
	@RequestMapping(value = "/getAllOrganizationModule",method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getAllOrganizationModule() throws AhanaBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getAllOrganizationModule----start--->"	+ System.currentTimeMillis());
		}
		List<Map<String, String>> organizationModuleDetails=commonManager.getAllOrganizationModule();
		if (logger.isDebugEnabled()) {
			logger.debug("getAllOrganizationModule: Success");
		}
		return handleSuccess("organizationModuleDetails",organizationModuleDetails);
	}
	
	@Override
	@RequestMapping(value = "/getSpecialityValues",method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getSpecialityValues() throws AhanaBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getSpecialityValues----start--->"	+ System.currentTimeMillis());
		}
		List<Map<String, String>> specialityDetails=commonManager.getSpecialityValues();
		if (logger.isDebugEnabled()) {
			logger.debug("getSpecialityValues: Success");
		}
		return handleSuccess("specialityDetails",specialityDetails);
	}
	
	@Override
	@RequestMapping(value = "/getDefaultOrganization",method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getDefaultOrganization() throws AhanaBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getDefaultOrganization----start--->"	+ System.currentTimeMillis());
		}
		AhanaVO ahanaVO=commonManager.getConfigDetailsItemByOid("getDefaultOrganizationByOid", "organizationOid", "ahanahospital");
		if (logger.isDebugEnabled()) {
			logger.debug("getDefaultOrganization: Success");
		}
		return handleSuccess("organizationDetails",(Organization)ahanaVO);
	}
	
	@Override
	@RequestMapping(value = "/getWardValues",method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getWardValues() throws AhanaBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getWardValues----start--->"	+ System.currentTimeMillis());
		}
		List<Map<String, String>> specialityDetails=commonManager.getWardValues();
		if (logger.isDebugEnabled()) {
			logger.debug("getWardValues: Success");
		}
		return handleSuccess("wardDetails",specialityDetails);
	}
	
	@Override
	@RequestMapping(value = "/getCategoryValues",method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getCategoryValues() throws AhanaBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getCategoryValues----start--->"	+ System.currentTimeMillis());
		}
		List<Map<String, String>> categoryDetails=commonManager.getCategoryValues();
		if (logger.isDebugEnabled()) {
			logger.debug("getCategoryValues: Success");
		}
		return handleSuccess("categoryDetails",categoryDetails);
	}
	
	@Override
	@RequestMapping(value = "/getRoomTypesValues",method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getRoomTypesValues() throws AhanaBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getRoomTypesValues----start--->"	+ System.currentTimeMillis());
		}
		List<Map<String, String>> roomTypesDetails=commonManager.getRoomTypesValues();
		if (logger.isDebugEnabled()) {
			logger.debug("getRoomTypesValues: Success");
		}
		return handleSuccess("roomTypesDetails",roomTypesDetails);
	}
	
	@Override
	@RequestMapping(value = "/getRoomChargeItemValues",method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getRoomChargeItemValues() throws AhanaBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getRoomChargeItemValues----start--->"	+ System.currentTimeMillis());
		}
		List<Map<String, String>> roomChargeItemDetails=commonManager.getRoomChargeItemValues();
		if (logger.isDebugEnabled()) {
			logger.debug("getRoomChargeItemValues: Success");
		}
		return handleSuccess("roomChargeItemDetails",roomChargeItemDetails);
	}
	
	@Override
	@RequestMapping(value = "/deactivateOrganizationModule",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> deactivateOrganizationModule(@RequestParam("oids") String organizationModuleOids) throws AhanaBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("deactivateOrganizationModule----start--->"	+ System.currentTimeMillis());
		}
			commonManager.deactivateOrganizationModule(organizationModuleOids);
		if (logger.isDebugEnabled()) {
			logger.debug("deactivateOrganizationModule: Success");
		}
		return handleStatus();
	}

}