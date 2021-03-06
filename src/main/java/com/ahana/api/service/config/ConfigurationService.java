package com.ahana.api.service.config;

import java.util.Map;

import com.ahana.api.domain.common.RoomAndBedType;
import com.ahana.api.domain.config.AlertType;
import com.ahana.api.domain.config.AlliedCharges;
import com.ahana.api.domain.config.ChargesForCategory;
import com.ahana.api.domain.config.ConfigRoomCharges;
import com.ahana.api.domain.config.ConfigWrapper;
import com.ahana.api.domain.config.DoctorSchedule;
import com.ahana.api.domain.config.PatientCategory;
import com.ahana.commons.system.security.exception.AhanaBusinessException;

public interface ConfigurationService {

	Map<String, Object> createAlertTypes(AlertType alertType) throws AhanaBusinessException;

	Map<String, Object> getAlertTypeByOid(String alertTypeOid) throws AhanaBusinessException;

	Map<String, Object> deleteAlertType(String alertTypeOid) throws AhanaBusinessException;

	Map<String, Object> getAllAlertType() throws AhanaBusinessException;

	Map<String, Object> createRoomAndBedType(RoomAndBedType roomAndBedType) throws AhanaBusinessException;

	Map<String, Object> getRoomAndBedTypeByOid(String floorOid) throws AhanaBusinessException;

	Map<String, Object> getAllActiveRoomAndBedType() throws AhanaBusinessException;

	Map<String, Object> deleteRoomAndBedType(String roomBedTypeOid) throws AhanaBusinessException;

	Map<String, Object> createOrUpdateMultipleConfig(ConfigWrapper configWrapper) throws AhanaBusinessException;

	Map<String, Object> createAlliedCharges(AlliedCharges alliedCharges) throws AhanaBusinessException;

	Map<String, Object> getAlliedChargesByOid(String alliedChargesOid) throws AhanaBusinessException;

	Map<String, Object> deleteAlliedCharges(String alliedChargesOid) throws AhanaBusinessException;

	Map<String, Object> getAllAlliedCharges() throws AhanaBusinessException;

	Map<String, Object> createChargesForCategory(ChargesForCategory chargesForCategory) throws AhanaBusinessException;

	Map<String, Object> getChargesForCategoryByOid(String chargesForCategoryOid) throws AhanaBusinessException;

	Map<String, Object> deleteChargesForCategory(String chargesForCategoryOid) throws AhanaBusinessException;

	Map<String, Object> getAllChargesForCategory() throws AhanaBusinessException;

	Map<String, Object> deletePatientCategory(String patientCategoryOid) throws AhanaBusinessException;

	Map<String, Object> getAllPatientCategory() throws AhanaBusinessException;

	Map<String, Object> getPatientCategoryByOid(String patientCategoryOid) throws AhanaBusinessException;

	Map<String, Object> createPatientCategory(PatientCategory patientCategory) throws AhanaBusinessException;

	Map<String, Object> getSubCategoryByCategory(String category) throws AhanaBusinessException;

	Map<String, Object> getDoctorDetails() throws AhanaBusinessException;

	Map<String, Object> getAllDoctorDetails() throws AhanaBusinessException;

	Map<String, Object> createDoctorSchedule(DoctorSchedule doctorSchedule) throws AhanaBusinessException;

	Map<String, Object> createConfigRoomCharges(ConfigRoomCharges configRoomCharges) throws AhanaBusinessException;

	Map<String, Object> getConfigRoomChargesByOid(String configRoomChargesOid) throws AhanaBusinessException;

	Map<String, Object> getAllConfigRoomCharges() throws AhanaBusinessException;

	Map<String, Object> deleteConfigRoomCharges(String configRoomChargesOid) throws AhanaBusinessException;

	Map<String, Object> deleteMultipleObject(String oids, String source) throws AhanaBusinessException;

	Map<String, Object> getDoctorScheduleByOid(String dsOid) throws AhanaBusinessException;

	Map<String, Object> getScheduledDoctorDetailsByOid(String doctorOid) throws AhanaBusinessException;

	Map<String, Object> deleteDoctorSchedule(String dsOid) throws AhanaBusinessException;

	Map<String, Object> updateChangesForCategory(String oid, String filedName, String fieldValue)
			throws AhanaBusinessException;

	Map<String, Object> getChargesForCategoryByOidAndType(String oid, String type) throws AhanaBusinessException;

}