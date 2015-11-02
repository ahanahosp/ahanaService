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

@SuppressWarnings("serial")
@Entity
@Table(name = "ward")
@NamedQueries({ @NamedQuery(name = "getWardByOid", query = "from Ward w where w.oid= :wardOid") })
public class Ward implements AhanaVO {

	@Id
	@GeneratedValue(generator = "IdGenerator")
	@GenericGenerator(name = "IdGenerator", strategy = "com.ahana.commons.system.security.util.IdGenerator", parameters = {
			@Parameter(name = "table", value = "seed_container"),
			@Parameter(name = "column", value = "high_oid"),
			@Parameter(name = "install_id", value = "seed_id"),
			@Parameter(name = "max_lo", value = "100000") })
	@Column(name = "oid")
	private String oid;

	@NotBlank(message=CommonErrorConstants.WARD_NAME_IS_REQUIRED)
	@Length(min=3,max=100,message=CommonErrorConstants.WARD_NAME_LENGTH_IS_INVALID)
	@Pattern(regexp=RegConstants.ALPHA_NUMERIC_SPACE_HYPEN,message=CommonErrorConstants.WARD_NAME_INVALID_FORMAT)
	@Column(name = "ward_name")
	private String wardName;

	@NotBlank(message=CommonErrorConstants.FLOOR_NAME_IS_REQUIRED)
	@Column(name = "floor_oid")
	private String floorOid;

	@Column(name = "status")
	private String status;

	public Ward(){
		this.status=Constants.ACT;
	}

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public String getWardName() {
		return wardName;
	}

	public void setWardName(String wardName) {
		this.wardName = wardName;
	}

	public String getFloorOid() {
		return floorOid;
	}

	public void setFloorOid(String floorOid) {
		this.floorOid = floorOid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}