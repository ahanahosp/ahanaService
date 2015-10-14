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
@Table(name = "speciality_details")
@NamedQueries({
		@NamedQuery(name = "getSpecialityDetailsByOid", query = "from SpecialityDetails sd where sd.oid= :specialityDetailOid") })
public class SpecialityDetails implements AhanaVO {

	private static final long serialVersionUID = 5031522286203851395L;

	@Id
	@GeneratedValue(generator = "IdGenerator")
	@GenericGenerator(name = "IdGenerator", strategy = "com.ahana.api.common.IdGenerator", parameters = {
			@Parameter(name = "table", value = "seed_container"), @Parameter(name = "column", value = "high_oid"),
			@Parameter(name = "install_id", value = "seed_id"), @Parameter(name = "max_lo", value = "100000") })
	@Column(name = "oid")
	private String oid;

	@NotBlank(message = ErrorConstants.ROLE_NAME_REQUIRED)
	@Size(message = ErrorConstants.ROLE_NAME_INVALID_LENGTH, min = 2, max = 100)
	@Pattern(regexp = RegConstants.ALPHAPET_SPACE_HYPEN, message = ErrorConstants.ROLE_MUST_BE_ALPHABETICAL)
	@Column(name = "speciality_name", unique = true, nullable = false, length = 100)
	private String specialityName;

	@Column(name = "status", length = 5)
	private String status;

	public SpecialityDetails() {
		this.status = Constants.ACT;
	}

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public String getSpecialityName() {
		return specialityName;
	}

	public void setSpecialityName(String specialityName) {
		this.specialityName = specialityName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
