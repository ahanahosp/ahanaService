package com.ahana.api.domain.common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.ahana.api.common.AhanaVO;
import com.ahana.api.common.ErrorConstants;
import com.ahana.api.common.RegConstants;

@Entity
@Table(name = "room_charges_item")
@NamedQueries({ @NamedQuery(name = "getRoomChargeItemByOid", query = "from RoomChargeItem r where r.oid= :roomChargeItemOid") })
public class RoomChargeItem implements AhanaVO {

	private static final long serialVersionUID = 4626929763755972434L;

	@Id
	@GeneratedValue(generator = "IdGenerator")
	@GenericGenerator(name = "IdGenerator", strategy = "com.ahana.api.common.IdGenerator", parameters = {
			@Parameter(name = "table", value = "seed_container"), @Parameter(name = "column", value = "high_oid"),
			@Parameter(name = "install_id", value = "seed_id"), @Parameter(name = "max_lo", value = "100000") })
	@Column(name = "oid")
	private String oid;

	@NotBlank(message = ErrorConstants.WARD_NAME_IS_REQUIRED)
	@Length(min = 3, max = 100, message = ErrorConstants.WARD_NAME_LENGTH_IS_INVALID)
	@Pattern(regexp = RegConstants.ALPHA_NUMERIC, message = ErrorConstants.WARD_NAME_INVALID_FORMAT)
	@Column(name = "item")
	private String item;

	@NotBlank(message = ErrorConstants.FLOOR_NAME_IS_REQUIRED)
	@Column(name = "code")
	private String code;

	@NotBlank(message = ErrorConstants.FLOOR_NAME_IS_REQUIRED)
	@Column(name = "description")
	private String description;

	@NotBlank(message = ErrorConstants.FLOOR_NAME_IS_REQUIRED)
	@Column(name = "category_oid")
	private String categoryOid;

	@Column(name = "status")
	private String status;

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategoryOid() {
		return categoryOid;
	}

	public void setCategoryOid(String categoryOid) {
		this.categoryOid = categoryOid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
