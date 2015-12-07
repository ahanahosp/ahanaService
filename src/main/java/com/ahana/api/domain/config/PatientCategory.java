package com.ahana.api.domain.config;

import java.sql.Timestamp;

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
@Table(name = "patient_category")
@NamedQueries({
		@NamedQuery(name = "getPatientCategoryByOid", query = "from PatientCategory at where at.oid= :patientCategoryOid") })
public class PatientCategory implements AhanaVO {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "IdGenerator")
	@GenericGenerator(name = "IdGenerator", strategy = "com.ahana.commons.system.security.util.IdGenerator", parameters = {
			@Parameter(name = "table", value = "seed_container"), @Parameter(name = "column", value = "high_oid"),
			@Parameter(name = "install_id", value = "seed_id"), @Parameter(name = "max_lo", value = "100000") })
	@Column(name = "oid")
	private String oid;

	@NotBlank(message = CommonErrorConstants.CATEGORY_IS_REQUIRED)
	@Length(min = 3, max = 100, message = CommonErrorConstants.CATEGORY_LENGTH_IS_INVALID)
	@Pattern(regexp = RegConstants.ALPHA_NUMERIC_SPACE_HYPEN, message = CommonErrorConstants.CATEGORY_FORMAT_IS_INVALID)
	@Column(name = "category_name")
	private String categoryName;

	@Column(name = "activation_date")
	private Timestamp activationDate;

	@Column(name = "status")
	private String status;

	public PatientCategory() {
		this.status = Constants.ACT;
	}

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Timestamp getActivationDate() {
		return activationDate;
	}

	public void setActivationDate(Timestamp activationDate) {
		this.activationDate = activationDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}