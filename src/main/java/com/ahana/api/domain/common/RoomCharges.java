package com.ahana.api.domain.common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import com.ahana.commons.system.domain.common.AhanaVO;
import com.ahana.commons.system.security.error.CommonErrorConstants;
import com.ahana.commons.system.security.util.Constants;
import com.ahana.commons.system.security.util.RegConstants;

@Entity
@Table(name = "room_charges")
@NamedQueries({
		@NamedQuery(name = "getRoomChargesByOid", query = "from RoomCharges rm where rm.oid= :roomChargesOid") })
public class RoomCharges implements AhanaVO {

	private static final long serialVersionUID = -7210783645037933808L;

	@Id
	@GeneratedValue(generator = "IdGenerator")
	@GenericGenerator(name = "IdGenerator", strategy = "com.ahana.commons.system.security.util.IdGenerator", parameters = {
			@Parameter(name = "table", value = "seed_container"), @Parameter(name = "column", value = "high_oid"),
			@Parameter(name = "install_id", value = "seed_id"), @Parameter(name = "max_lo", value = "100000") })
	@Column(name = "oid")
	private String oid;

	@NotBlank(message = CommonErrorConstants.ROOM_CHARGE_ITEM_IS_REQUIRED)
	@Column(name = "room_type_oid")
	private String roomTypeOid;

	@Range(max=1000000,min=1,message = CommonErrorConstants.CHARGE_IS_REQUIRED)
	@Column(name = "charge")
	private int charge;

	@NotBlank(message = CommonErrorConstants.BED_TYPE_IS_REQUIRED)
	@Size(message = CommonErrorConstants.BED_TYPE_LENGTH_IS_REQUIRED, min = 2, max = 100)
	@Pattern(regexp = RegConstants.ALPHA_NUMERIC_SPACE_HYPEN, message = CommonErrorConstants.BED_TYPE_IS_INVALID_FORMAT)
	@Column(name = "room_charge_items_oid")
	private String roomChargeItemsOid;

	@Column(name = "status", length = 5)
	private String status;

	public RoomCharges() {
		this.status = Constants.ACT;
	}

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public String getRoomTypeOid() {
		return roomTypeOid;
	}

	public void setRoomTypeOid(String roomTypeOid) {
		this.roomTypeOid = roomTypeOid;
	}

	public int getCharge() {
		return charge;
	}

	public void setCharge(int charge) {
		this.charge = charge;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRoomChargeItemsOid() {
		return roomChargeItemsOid;
	}

	public void setRoomChargeItemsOid(String roomChargeItemsOid) {
		this.roomChargeItemsOid = roomChargeItemsOid;
	}

}