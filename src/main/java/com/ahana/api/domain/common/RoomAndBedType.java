package com.ahana.api.domain.common;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.ahana.commons.system.domain.common.AhanaVO;
import com.ahana.commons.system.security.error.CommonErrorConstants;
import com.ahana.commons.system.security.util.RegConstants;

@Entity
@Table(name = "room_bed_type")
@NamedQueries({
		@NamedQuery(name = "getvRoomAndBedTypeByOid", query = "from RoomAndBedType r where r.oid= :roomBedTypeOid") })
public class RoomAndBedType implements AhanaVO {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "IdGenerator")
	@GenericGenerator(name = "IdGenerator", strategy = "com.ahana.commons.system.security.util.IdGenerator", parameters = {
			@Parameter(name = "table", value = "seed_container"), @Parameter(name = "column", value = "high_oid"),
			@Parameter(name = "install_id", value = "seed_id"), @Parameter(name = "max_lo", value = "100000") })
	@Column(name = "oid")
	private String oid;

	@Min(value=1, message = CommonErrorConstants.OID_IS_REQUIRED)
	@Max(value=1000,message = CommonErrorConstants.OID_IS_INVALID_LENGTH)
	//@Pattern(regexp = RegConstants.NUMERIC, message = CommonErrorConstants.OID_IS_INVALID_FORMAT)
	@Column(name = "order_no")
	private int orderId;

	@NotBlank(message = CommonErrorConstants.BED_NO_IS_REQUIRED)
	@Length(min = 1, max = 10, message = CommonErrorConstants.BED_NO_LENGTH_IS_INVALID)
	@Pattern(regexp = RegConstants.ALPHA_NUMERIC_SPACE_HYPEN, message = CommonErrorConstants.BED_NO_IS_INVALID_FORMAT)
	@Column(name = "bed_no")
	private String bedNo;

	@Column(name = "status")
	private String status;

	@NotBlank(message = CommonErrorConstants.ROOM_NAME_IS_REQUIRED)
	@Transient
	private String roomTypeOids;

	@Transient
	private List<String> savedRoomTyes;

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getBedNo() {
		return bedNo;
	}

	public void setBedNo(String bedNo) {
		this.bedNo = bedNo;
	}

	public String getRoomTypeOids() {
		return roomTypeOids;
	}

	public void setRoomTypeOids(String roomTypeOids) {
		this.roomTypeOids = roomTypeOids;
	}

	public List<String> getSavedRoomTyes() {
		return savedRoomTyes;
	}

	public void setSavedRoomTyes(List<String> savedRoomTyes) {
		this.savedRoomTyes = savedRoomTyes;
	}

}