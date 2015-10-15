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

import com.ahana.api.common.AhanaVO;
import com.ahana.api.common.Constants;
import com.ahana.api.common.ErrorConstants;
import com.ahana.api.common.RegConstants;

@Entity
@Table(name = "room_charges")
@NamedQueries({
		@NamedQuery(name = "getRoomChargesByOid", query = "from RoomCharges rm where rm.oid= :roomChargesOid") })
public class RoomCharges implements AhanaVO {

	private static final long serialVersionUID = -7210783645037933808L;

	@Id
	@GeneratedValue(generator = "IdGenerator")
	@GenericGenerator(name = "IdGenerator", strategy = "com.ahana.api.common.IdGenerator", parameters = {
			@Parameter(name = "table", value = "seed_container"), @Parameter(name = "column", value = "high_oid"),
			@Parameter(name = "install_id", value = "seed_id"), @Parameter(name = "max_lo", value = "100000") })
	@Column(name = "oid")
	private String oid;

	@NotBlank(message = ErrorConstants.ROOM_CHARGE_ITEM_IS_REQUIRED)
	@Column(name = "room_charge_item_oid")
	private String roomChargeItemOid;

	@NotBlank(message = ErrorConstants.CHARGE_IS_REQUIRED)
	@Size(message = ErrorConstants.CHARGE_LENGTH_IS_INVALID, min = 2, max = 10)
	@Pattern(regexp = RegConstants.NUMERIC, message = ErrorConstants.CHARGE_MUST_BE_NUMERIC)
	@Column(name = "charge")
	private int charge;

	@NotBlank(message = ErrorConstants.BED_TYPE_IS_REQUIRED)
	@Size(message = ErrorConstants.BED_TYPE_LENGTH_IS_REQUIRED, min = 2, max = 100)
	@Pattern(regexp = RegConstants.ALPHA_NUMERIC_SPACE_HYPEN, message = ErrorConstants.BED_TYPE_IS_INVALID_FORMAT)
	@Column(name = "bed_type")
	private String bedType;

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

	public String getRoomChargeItemOid() {
		return roomChargeItemOid;
	}

	public void setRoomChargeItemOid(String roomChargeItemOid) {
		this.roomChargeItemOid = roomChargeItemOid;
	}

	public int getCharge() {
		return charge;
	}

	public void setCharge(int charge) {
		this.charge = charge;
	}

	public String getBedType() {
		return bedType;
	}

	public void setBedType(String bedType) {
		this.bedType = bedType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}