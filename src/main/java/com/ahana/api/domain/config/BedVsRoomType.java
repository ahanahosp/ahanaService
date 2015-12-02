package com.ahana.api.domain.config;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.ahana.commons.system.domain.common.AhanaVO;

@Entity
@Table(name = "bed_vs_rooms")
@IdClass(BedVsRoomTypePK.class)
public class BedVsRoomType implements Serializable, AhanaVO {

	private static final long serialVersionUID = 78018374285740564L;

	public BedVsRoomType() {
	}

	public BedVsRoomType(BedVsRoomTypePK bedVsRoomTypePK) {
		roomTypeOid = bedVsRoomTypePK.getRoomTypeOid();
		roomAndBedTypeOid = bedVsRoomTypePK.getRoomAndBedTypeOid();
	}

	@Id
	@Column(name = "room_type_oid", insertable = true, updatable = true)
	private String roomTypeOid;

	@Id
	@Column(name = "room_and_bed_type_oid", insertable = true, updatable = true)
	private String roomAndBedTypeOid;

	public String getRoomTypeOid() {
		return roomTypeOid;
	}

	public void setRoomTypeOid(String roomTypeOid) {
		this.roomTypeOid = roomTypeOid;
	}

	public String getRoomAndBedTypeOid() {
		return roomAndBedTypeOid;
	}

	public void setRoomAndBedTypeOid(String roomAndBedTypeOid) {
		this.roomAndBedTypeOid = roomAndBedTypeOid;
	}

}