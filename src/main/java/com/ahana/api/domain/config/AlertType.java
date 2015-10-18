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

import com.ahana.api.common.AhanaVO;
import com.ahana.api.common.Constants;
import com.ahana.api.common.ErrorConstants;
import com.ahana.api.common.RegConstants;

@Entity
@Table(name = "alert_type")
@NamedQueries({ @NamedQuery(name = "getAlertTypeByOid", query = "from AlertType at where at.oid= :alertTypeOid") })
public class AlertType implements AhanaVO {

	private static final long serialVersionUID = 7619309774719642928L;

	@Id
	@GeneratedValue(generator = "IdGenerator")
	@GenericGenerator(name = "IdGenerator", strategy = "com.ahana.api.common.IdGenerator", parameters = {
			@Parameter(name = "table", value = "seed_container"), @Parameter(name = "column", value = "high_oid"),
			@Parameter(name = "install_id", value = "seed_id"), @Parameter(name = "max_lo", value = "100000") })
	@Column(name = "oid")
	private String oid;

	@NotBlank(message = ErrorConstants.ALERT_NAME_IS_REQUIRED)
	@Length(min = 3, max = 100, message = ErrorConstants.ALERT_NAME_LENGTH_IS_INVALID)
	@Pattern(regexp = RegConstants.ALPHA_NUMERIC_SPACE_HYPEN, message = ErrorConstants.ALERT_NAME_IS_INVALID_FORMAT)
	@Column(name = "alert_name")
	private String alertName;

	@Column(name = "status")
	private String status;

	public AlertType() {
		this.status = Constants.ACT;
	}

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

	public String getAlertName() {
		return alertName;
	}

	public void setAlertName(String alertName) {
		this.alertName = alertName;
	}
}