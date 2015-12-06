package com.ahana.api.domain.config;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.validator.constraints.NotBlank;

import com.ahana.commons.system.domain.common.AhanaVO;
import com.ahana.commons.system.security.error.CommonErrorConstants;
import com.ahana.commons.system.security.util.RegConstants;

@Entity
@Table(name = "config_room_charges")
@NamedQueries({
		@NamedQuery(name = "getConfigRoomChargesByOid", query = "from ConfigRoomCharges crc where crc.oid= :configRoomChargesOid") })
public class ConfigRoomCharges implements AhanaVO {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "IdGenerator")
	@GenericGenerator(name = "IdGenerator", strategy = "com.ahana.commons.system.security.util.IdGenerator", parameters = {
			@Parameter(name = "table", value = "seed_container"), @Parameter(name = "column", value = "high_oid"),
			@Parameter(name = "install_id", value = "seed_id"), @Parameter(name = "max_lo", value = "100000") })
	@Column(name = "oid")
	private String oid;

	@DecimalMin(value="0.00",message = CommonErrorConstants.DISCOUNT_IS_REQUIRED)
	@DecimalMax(value="100000.00",message = CommonErrorConstants.DISCOUNT_LENGTH_IS_INVALID)
	@Digits(fraction=2,integer=10,message = CommonErrorConstants.DISCOUNT_IS_INVALID_FORMAT)
	@Column(name = "discount")
	private double discount;

	@NotBlank(message = CommonErrorConstants.START_TIME_IS_REQUIRED)
	@Size(message = CommonErrorConstants.START_TIME_LENGTH_IS_INVALID, min = 8, max = 8)
	@Pattern(regexp = RegConstants.TIME, message = CommonErrorConstants.START_TIME_INVALID_FORMAT)
	@Column(name = "start_time")
	private String startTime;

	@NotBlank(message = CommonErrorConstants.END_TIME_IS_REQUIRED)
	@Size(message = CommonErrorConstants.END_TIME_LENGTH_IS_INVALID, min = 8, max = 8)
	@Pattern(regexp = RegConstants.TIME, message = CommonErrorConstants.END_TIME_INVALID_FORMAT)
	@Column(name = "end_time")
	private String endTime;

	@NotBlank(message = CommonErrorConstants.HOUR_FORMAT_IS_REQUIRED)
	@Size(message = CommonErrorConstants.END_TIME_LENGTH_IS_INVALID, min = 8, max = 8)
	@Pattern(regexp = RegConstants.ALPHA_NUMERIC, message = CommonErrorConstants.END_TIME_INVALID_FORMAT)
	@Column(name = "format")
	private String format;

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}
}