package com.ahana.api.domain.common;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.ahana.api.common.AhanaVO;
import com.ahana.api.common.Constants;
import com.ahana.api.common.ErrorConstants;

@SuppressWarnings("serial")
@Entity
@Table(name = "patient")
public class QuickRegistration implements AhanaVO {

	@Id
	@GeneratedValue(generator = "IdGenerator")
	@GenericGenerator(name = "IdGenerator", strategy = "com.ahana.api.common.IdGenerator", parameters = {
			@Parameter(name = "table", value = "seed_container"),
			@Parameter(name = "column", value = "high_oid"),
			@Parameter(name = "install_id", value = "seed_id"),
			@Parameter(name = "max_lo", value = "100000"),
			@Parameter(name = "type", value = Constants.ID_PATIENT) })
	@Column(name = "oid")
	private String oid;

	@NotNull(message = ErrorConstants.REGISTRATION_DATE_IS_REQUIRED)
	@Column(name = "registration_date", nullable = false)
	private Timestamp registrationDate;

	@NotBlank(message = ErrorConstants.SALUTATION_IS_REQUIRED)
	@Length(max = 10, min = 2, message = ErrorConstants.SALUTATION_IS_INVALID_LENGTH)
	@Pattern(regexp = "[a-z-A-Z\\.]*", message = ErrorConstants.SALUTATION_MUST_BE_ALPHABETICAL)
	@Column(name = "salutation", nullable = false, length = 10)
	private String salutation;

	@NotBlank(message = ErrorConstants.FIRST_NAME_IS_REQUIRED)
	@Length(max = 50, min = 3, message = ErrorConstants.FIRST_NAME_IS_INVALID_LENGTH)
	@Pattern(regexp = "[a-z-A-Z]*", message = ErrorConstants.FIRST_NAME_MUST_BE_ALPHABETICAL)
	@Column(name = "first_name", nullable = false, length = 50)
	private String firstName;

	@Column(name = "last_name", length = 50)
	private String lastName;

	@NotBlank(message = ErrorConstants.GENDER_IS_REQUIRED)
	@Length(max = 6, min = 1, message = ErrorConstants.GENDER_IS_INVALID_LENGTH)
	@Pattern(regexp = "[a-z-A-Z]*", message = ErrorConstants.GENDER_MUST_BE_ALPHABETICAL)
	@Column(name = "gender", nullable = false, length = 6)
	private String gender;

	@NotBlank(message = ErrorConstants.CITY_IS_REQUIRED)
	@Length(max = 50, min = 1, message = ErrorConstants.CITY_IS_INVALID_LENGTH)
	@Pattern(regexp = "[a-z-A-Z]*", message = ErrorConstants.CITY_MUST_BE_ALPHABETICAL)
	@Column(name = "city", nullable = false, length = 50)
	private String city;

	@NotBlank(message = ErrorConstants.MOBILE_NUMBER_IS_REQUIRED)
	@Length(max = 12, min = 5, message = ErrorConstants.MOBILE_NUMBER_IS_INVALID_LENGTH)
	@Pattern(regexp = "[0-9]*", message = ErrorConstants.MOBILE_NUMBER_MUST_BE_NUMBER)
	@Column(name = "mobile")
	private String mobile;

	@NotBlank(message = ErrorConstants.PATIENT_TYPE_IS_REQUIRED)
	@Length(max = 50, min = 3, message = ErrorConstants.PATIENT_TYPE_LENGTH_IS_INVALID)
	@Pattern(regexp = "[a-z-A-Z]*", message = ErrorConstants.PATIENT_TYPE_MUST_BE_ALPHABETICAL)
	@Column(name = "patient_type", nullable = false, length = 50)
	private String patientType;

	@Column(name = "category")
	private String category;

	@NotBlank(message = ErrorConstants.PATIENT_REFERENCE_NO_REQUIRED)
	@Length(max = 50, min = 3, message = ErrorConstants.PATIENT_REFERENCE_NO_LENGTH_IS_INVALID)
	@Column(name = "patient_reference", nullable = false, length = 50)
	private String patientReference;

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public Timestamp getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Timestamp registrationDate) {
		this.registrationDate = registrationDate;
	}

	public String getSalutation() {
		return salutation;
	}

	public void setSalutation(String salutation) {
		this.salutation = salutation;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPatientType() {
		return patientType;
	}

	public void setPatientType(String patientType) {
		this.patientType = patientType;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getPatientReference() {
		return patientReference;
	}

	public void setPatientReference(String patientReference) {
		this.patientReference = patientReference;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}