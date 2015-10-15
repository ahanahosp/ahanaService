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
import com.ahana.api.common.Constants;
import com.ahana.api.common.ErrorConstants;
import com.ahana.api.common.RegConstants;

@Entity
@Table(name = "floor")
@NamedQueries({ @NamedQuery(name = "getFloorByOid", query = "from Floor f where f.oid= :floorOid") })
public class Floor implements AhanaVO {

	private static final long serialVersionUID = 7619309774719642928L;

	@Id
	@GeneratedValue(generator = "IdGenerator")
	@GenericGenerator(name = "IdGenerator", strategy = "com.ahana.api.common.IdGenerator", parameters = {
			@Parameter(name = "table", value = "seed_container"),
			@Parameter(name = "column", value = "high_oid"),
			@Parameter(name = "install_id", value = "seed_id"),
			@Parameter(name = "max_lo", value = "100000") })
	@Column(name = "oid")
	private String oid;

	@NotBlank(message=ErrorConstants.FLOOR_NAME_IS_REQUIRED)
	@Length(min=3,max=100,message=ErrorConstants.FLOOR_NAME_LENGTH_IS_INVALID)
	@Pattern(regexp=RegConstants.ALPHA_NUMERIC_SPACE_HYPEN,message=ErrorConstants.FLOOR_NAME_INVALID_FORMAT)
	@Column(name = "floor_name")
	private String floorName;

	@NotBlank(message=ErrorConstants.FLOOR_CODE_IS_REQUIRED)
	@Length(min=2,max=10,message=ErrorConstants.FLOOR_CODE_LENGTH_IS_INVALID)
	@Pattern(regexp=RegConstants.ALPHA_NUMERIC_SPACE_HYPEN,message=ErrorConstants.FLOOR_CODE_INVALID_FORMAT)
	@Column(name = "floor_code")
	private String floorCode;

	@Column(name = "status")
	private String status;
	
	public Floor(){
		this.status=Constants.ACT;
	}

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public String getFloorName() {
		return floorName;
	}

	public void setFloorName(String floorName) {
		this.floorName = floorName;
	}

	public String getFloorCode() {
		return floorCode;
	}

	public void setFloorCode(String floorCode) {
		this.floorCode = floorCode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
