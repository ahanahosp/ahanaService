package com.ahana.api.domain.config;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.apache.commons.lang3.builder.HashCodeBuilder;


@Embeddable
@AttributeOverrides({
		@AttributeOverride(name = "userOid", column = @Column(name = "user_oid")),
		@AttributeOverride(name = "roleOid", column = @Column(name = "role_oid")) })
public class BedVsRoomTypePK implements Serializable {

	private static final long serialVersionUID = -7460093900781470614L;

	private String roomAndBedTypeOid;

	private String roomTypeOid;


	public BedVsRoomTypePK() {
	}

	public BedVsRoomTypePK(String roomAndBedTypeOid, String roomTypeOid) {
		this.roomAndBedTypeOid = roomAndBedTypeOid;
		this.roomTypeOid = roomTypeOid;
	}
	
	public String getRoomAndBedTypeOid() {
		return roomAndBedTypeOid;
	}

	public void setRoomAndBedTypeOid(String roomAndBedTypeOid) {
		this.roomAndBedTypeOid = roomAndBedTypeOid;
	}

	public String getRoomTypeOid() {
		return roomTypeOid;
	}

	public void setRoomTypeOid(String roomTypeOid) {
		this.roomTypeOid = roomTypeOid;
	}

	public final boolean equals(final Object o) {
		BedVsRoomTypePK userRolePK = (BedVsRoomTypePK) o;
        if (userRolePK.getRoomTypeOid() == this.roomTypeOid
           && userRolePK.getRoomAndBedTypeOid() == this.roomAndBedTypeOid) {
			return true;
		}
		return false;
	}

	public final int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}
}