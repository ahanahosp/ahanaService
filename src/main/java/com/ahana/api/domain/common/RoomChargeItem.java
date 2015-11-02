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

import com.ahana.commons.system.domain.common.AhanaVO;
import com.ahana.commons.system.security.error.CommonErrorConstants;
import com.ahana.commons.system.security.util.Constants;
import com.ahana.commons.system.security.util.RegConstants;

@Entity
@Table(name = "room_charges_item")
@NamedQueries({ @NamedQuery(name = "getRoomChargeItemByOid", query = "from RoomChargeItem r where r.oid= :roomChargeItemOid") })
public class RoomChargeItem implements AhanaVO {

	private static final long serialVersionUID = 4626929763755972434L;

	@Id
	@GeneratedValue(generator = "IdGenerator")
	@GenericGenerator(name = "IdGenerator", strategy = "com.ahana.commons.system.security.util.IdGenerator", parameters = {
			@Parameter(name = "table", value = "seed_container"), @Parameter(name = "column", value = "high_oid"),
			@Parameter(name = "install_id", value = "seed_id"), @Parameter(name = "max_lo", value = "100000") })
	@Column(name = "oid")
	private String oid;

	@NotBlank(message = CommonErrorConstants.ITEM_IS_REQUIRED)
	@Length(min = 3, max = 100, message = CommonErrorConstants.ITEM_LENGTH_IS_INVALID)
	@Pattern(regexp = RegConstants.ALPHA_NUMERIC_SPACE_HYPEN, message = CommonErrorConstants.ITEM_IS_INVALID_FORMAT)
	@Column(name = "item")
	private String item;

	@NotBlank(message = CommonErrorConstants.CODE_IS_REQUIRED)
	@Length(min = 3, max = 10, message = CommonErrorConstants.CODE_LENGTH_IS_INVALID)
	@Pattern(regexp = RegConstants.ALPHA_NUMERIC_SPACE_HYPEN, message = CommonErrorConstants.CODE_IS_INVALID_FORMAT)
	@Column(name = "code")
	private String code;

	@Column(name = "description")
	private String description;

	@NotBlank(message = CommonErrorConstants.CATEGORY_IS_REQUIRED)
	@Column(name = "category_oid")
	private String categoryOid;

	@Column(name = "status")
	private String status;
	
	public RoomChargeItem(){
		this.status=Constants.ACT;
	}

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
