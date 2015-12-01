package com.ahana.api.domain.config;

import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;

import org.hibernate.validator.constraints.NotBlank;

import com.ahana.api.domain.common.Floor;
import com.ahana.api.domain.common.RoomType;
import com.ahana.api.domain.common.Ward;
import com.ahana.commons.system.domain.user.Roles;
import com.ahana.commons.system.security.validation.custom.DepententField;

@DepententField.List({ @DepententField(fieldName = "source", fieldValue = "roles", dependFieldName = "roles"),
		@DepententField(fieldName = "source", fieldValue = "floors", dependFieldName = "floors"),
		@DepententField(fieldName = "source", fieldValue = "wards", dependFieldName = "wards"),
		@DepententField(fieldName = "source", fieldValue = "roomTypes", dependFieldName = "roomTypes") })
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

	public ConfigWrapper() {
	}

}