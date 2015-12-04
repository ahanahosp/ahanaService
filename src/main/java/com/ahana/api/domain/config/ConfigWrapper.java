package com.ahana.api.domain.config;

import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;

import org.hibernate.validator.constraints.NotBlank;

import com.ahana.api.domain.common.Floor;
import com.ahana.api.domain.common.Procedures;
import com.ahana.api.domain.common.Room;
import com.ahana.api.domain.common.RoomMaintenanceDetails;
import com.ahana.api.domain.common.RoomType;
import com.ahana.api.domain.common.SpecialityDetails;
import com.ahana.api.domain.common.Ward;
import com.ahana.commons.system.domain.user.Roles;
import com.ahana.commons.system.security.validation.custom.DepententField;

@DepententField.List({ @DepententField(fieldName = "source", fieldValue = "roles", dependFieldName = "roles"),
		@DepententField(fieldName = "source", fieldValue = "floors", dependFieldName = "floors"),
		@DepententField(fieldName = "source", fieldValue = "wards", dependFieldName = "wards"),
		@DepententField(fieldName = "source", fieldValue = "roomTypes", dependFieldName = "roomTypes"),
		@DepententField(fieldName = "source", fieldValue = "roomMaintenanceDetails", dependFieldName = "roomMaintenanceDetails"),
		@DepententField(fieldName = "source", fieldValue = "rooms", dependFieldName = "rooms"),
		@DepententField(fieldName = "source", fieldValue = "alliedCharges", dependFieldName = "alliedCharges"),
		@DepententField(fieldName = "source", fieldValue = "specialityDetails", dependFieldName = "specialityDetails"),
		@DepententField(fieldName = "source", fieldValue = "procedures", dependFieldName = "procedures"),
		@DepententField(fieldName = "source", fieldValue = "patientCategories", dependFieldName = "patientCategories"),
		@DepententField(fieldName = "source", fieldValue = "alertTypes", dependFieldName = "alertTypes")})
public class ConfigWrapper implements Serializable {

	private static final long serialVersionUID = 1L;

	@Valid
	private List<Roles> roles;

	@Valid
	private List<Floor> floors;

	@Valid
	private List<Ward> wards;

	@Valid
	private List<RoomType> roomTypes;

	@Valid
	private List<RoomMaintenanceDetails> roomMaintenanceDetails;

	@Valid
	private List<Room> rooms;

	@Valid
	private List<Procedures> procedures;

	@Valid
	private List<SpecialityDetails> specialityDetails;

	@Valid
	private List<AlliedCharges> alliedCharges;

	@Valid
	private List<AlertType> alertTypes;

	@Valid
	private List<PatientCategory> patientCategories;

	@NotBlank
	private String source;

	public List<Roles> getRoles() {
		return roles;
	}

	public void setRoles(List<Roles> roles) {
		this.roles = roles;
	}

	public List<Floor> getFloors() {
		return floors;
	}

	public void setFloors(List<Floor> floors) {
		this.floors = floors;
	}

	public List<Ward> getWards() {
		return wards;
	}

	public void setWards(List<Ward> wards) {
		this.wards = wards;
	}

	public List<RoomType> getRoomTypes() {
		return roomTypes;
	}

	public void setRoomTypes(List<RoomType> roomTypes) {
		this.roomTypes = roomTypes;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public List<RoomMaintenanceDetails> getRoomMaintenanceDetails() {
		return roomMaintenanceDetails;
	}

	public void setRoomMaintenanceDetails(List<RoomMaintenanceDetails> roomMaintenanceDetails) {
		this.roomMaintenanceDetails = roomMaintenanceDetails;
	}

	public List<Room> getRooms() {
		return rooms;
	}

	public void setRooms(List<Room> rooms) {
		this.rooms = rooms;
	}

	public List<Procedures> getProcedures() {
		return procedures;
	}

	public void setProcedures(List<Procedures> procedures) {
		this.procedures = procedures;
	}

	public List<SpecialityDetails> getSpecialityDetails() {
		return specialityDetails;
	}

	public void setSpecialityDetails(List<SpecialityDetails> specialityDetails) {
		this.specialityDetails = specialityDetails;
	}

	public List<AlliedCharges> getAlliedCharges() {
		return alliedCharges;
	}

	public void setAlliedCharges(List<AlliedCharges> alliedCharges) {
		this.alliedCharges = alliedCharges;
	}

	public List<AlertType> getAlertTypes() {
		return alertTypes;
	}

	public void setAlertTypes(List<AlertType> alertTypes) {
		this.alertTypes = alertTypes;
	}

	public List<PatientCategory> getPatientCategories() {
		return patientCategories;
	}

	public void setPatientCategories(List<PatientCategory> patientCategories) {
		this.patientCategories = patientCategories;
	}

	public ConfigWrapper() {
	}

}