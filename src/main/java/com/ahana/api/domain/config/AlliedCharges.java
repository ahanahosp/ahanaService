package com.ahana.api.domain.config;

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
@Table(name = "allied_charges")
@NamedQueries({
		@NamedQuery(name = "getAlliedChargesByOid", query = "from AlliedCharges at where at.oid= :alliedChargesOid") })
public class AlliedCharges implements AhanaVO {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "IdGenerator")
	@GenericGenerator(name = "IdGenerator", strategy = "com.ahana.commons.system.security.util.IdGenerator", parameters = {
			@Parameter(name = "table", value = "seed_container"), @Parameter(name = "column", value = "high_oid"),
			@Parameter(name = "install_id", value = "seed_id"), @Parameter(name = "max_lo", value = "100000") })
	@Column(name = "oid")
	private String oid;

	@NotBlank(message = CommonErrorConstants.ALLIED_CHARGES_IS_REQUIRED)
	@Length(min = 3, max = 100, message = CommonErrorConstants.ALLIED_CHARGES_LENGTH_IS_INVALID)
	@Pattern(regexp = RegConstants.ALPHA_NUMERIC_SPACE_HYPEN, message = CommonErrorConstants.ALLIED_CHARGES_IS_INVALID_FORMAT)
	@Column(name = "allied_charges")
	private String alliedCharges;

	@Column(name = "status")
	private String status;

	public AlliedCharges() {
		this.status = Constants.ACT;
	}

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public String getAlliedCharges() {
		return alliedCharges;
	}

	public void setAlliedCharges(String alliedCharges) {
		this.alliedCharges = alliedCharges;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
