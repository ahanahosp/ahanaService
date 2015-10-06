package com.ahana.api.domain.common;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
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
public class PatientRegistration implements AhanaVO {

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
	@Length(max = 10, min = 2, message =ErrorConstants.SALUTATION_IS_INVALID_LENGTH)
	@Pattern(regexp = "[a-z-A-Z]*", message = ErrorConstants.SALUTATION_MUST_BE_ALPHABETICAL)
	@Column(name = "salutation", nullable = false)
	private String salutation;

	@NotBlank(message = ErrorConstants.FIRST_NAME_IS_REQUIRED)
	@Length(max = 50, min = 3, message = ErrorConstants.FIRST_NAME_IS_INVALID_LENGTH)
	@Pattern(regexp = "[a-z-A-Z]*", message = ErrorConstants.FIRST_NAME_MUST_BE_ALPHABETICAL)
	@Column(name = "first_name", nullable = false)
	private String firstName;

	@NotBlank(message = ErrorConstants.LAST_NAME_IS_REQUIRED)
	@Length(max = 50, min = 3, message = ErrorConstants.LAST_NAME_IS_INVALID_LENGTH)
	@Pattern(regexp = "[a-z-A-Z]*", message = ErrorConstants.LAST_NAME_MUST_BE_ALPHABETICAL)
	@Column(name = "last_name", nullable = false)
	private String lastName;

	@Column(name = "care_taker_name")
	private String careTakerName;

	@NotNull(message = ErrorConstants.DATE_OF_BIRTH_IS_REQUIRED)
	@Past(message = ErrorConstants.DATE_OF_BIRTH_MUST_BE_PAST_DATE)
	@Column(name = "dob", nullable = false)
	private Timestamp dateOfBirth;

	@NotBlank(message = ErrorConstants.GENDER_IS_REQUIRED)
	@Length(max = 6, min = 4, message = ErrorConstants.GENDER_IS_INVALID_LENGTH)
	@Pattern(regexp = "[a-z-A-Z]*", message = ErrorConstants.GENDER_MUST_BE_ALPHABETICAL)
	@Column(name = "gender", nullable = false)
	private String gender;

	@NotBlank(message = ErrorConstants.MARITAL_STATUS_IS_REQUIRED)
	@Length(max = 15, min = 5, message = ErrorConstants.MARITAL_STATUS_LENGTH_IS_INVALID)
	@Pattern(regexp = "[a-z-A-Z]*", message = ErrorConstants.MARITAL_STATUS_MUST_BE_ALPHABETICAL)
	@Column(name = "marital_status", nullable = false)
	private String maritalStatus;

	@Column(name = "occupation")
	private String occupation;

	@Column(name = "blood_group")
	private String bloodGroup;

	@Column(name = "category")
	private String category;

	@Column(name = "email_id")
	private String emailId;

	@Column(name = "referal_doctor")
	private String referalDoctor;

	@Column(name = "registration_mode")
	private String registrationMode;

	@NotBlank(message = ErrorConstants.PATIENT_TYPE_IS_REQUIRED)
	@Length(max = 50, min = 3, message = ErrorConstants.PATIENT_TYPE_LENGTH_IS_INVALID)
	@Pattern(regexp = "[a-z-A-Z]*", message = ErrorConstants.PATIENT_TYPE_MUST_BE_ALPHABETICAL)
	@Column(name = "patient_type", nullable = false)
	private String patinetType;

	@NotBlank(message = ErrorConstants.ADDRESS_IS_REQUIRED)
	@Length(max = 100, min = 5, message = ErrorConstants.ADDRESS_IS_INVALID_LENGTH)
	@Pattern(regexp = "[a-z-A-Z]*", message = ErrorConstants.ADDRESS_MUST_BE_ALPHABETICAL)
	@Column(name = "address", nullable = false, length = 100)
	private String address;

	@NotBlank(message = ErrorConstants.COUNTRY_REQUIRED)
	@Length(max = 50, min = 3, message = ErrorConstants.COUNTRY_REQUIRED)
	@Pattern(regexp = "[a-z-A-Z]*", message = ErrorConstants.COUNTRY_MUST_BE_ALPHABETICAL)
	@Column(name = "country", nullable = false, length = 50)
	private String country;

	@NotBlank(message = ErrorConstants.STATE_IS_REQUIRED)
	@Length(max = 50, min = 3, message = ErrorConstants.STATE_IS_INVALID_LENGTH)
	@Pattern(regexp = "[a-z-A-Z]*", message = ErrorConstants.STATE_MUST_BE_ALPHABETICAL)
	@Column(name = "state", nullable = false, length = 50)
	private String state;

	@NotBlank(message = ErrorConstants.CITY_IS_REQUIRED)
	@Length(max = 50, min = 3, message = ErrorConstants.CITY_IS_INVALID_LENGTH)
	@Pattern(regexp = "[a-z-A-Z]*", message = ErrorConstants.CITY_MUST_BE_ALPHABETICAL)
	@Column(name = "city", nullable = false, length = 50)
	private String city;

	@NotBlank(message = ErrorConstants.ZIP_CODE_IS_REQUIRED)
	@Length(max = 10, min = 5, message = ErrorConstants.ZIP_CODE_IS_INVALID_LENGTH)
	@Pattern(regexp = "[0-9]*", message = ErrorConstants.ZIP_CODE_IS_INVALID_FORMAT)
	@Column(name = "zip", nullable = false, length = 10)
	private String zip;

	@Transient
	private boolean sameAsCurrentAddress=false;

	@Column(name = "permanent_address")
	private String permanentAddress;

	@Column(name = "permanent_country")
	private String permanentCountry;

	@Column(name = "permanent_state")
	private String permanentState;

	@Column(name = "permanent_city")
	private String permanentCity;

	@Column(name = "permanent_zip")
	private String permanentZip;

	@Column(name = "primary_contact")
	private String primaryContact;

	@Column(name = "secondary_contact")
	private String secondaryContact;

	@NotBlank(message = ErrorConstants.MOBILE_NUMBER_IS_REQUIRED)
	@Length(max = 12, min = 10, message = ErrorConstants.MOBILE_NUMBER_IS_INVALID_LENGTH)
	@Pattern(regexp = "[0-9]*", message = ErrorConstants.MOBILE_NUMBER_MUST_BE_NUMBER)
	@Column(name = "mobile")
	private String mobile;

	@Column(name = "referal_hospital")
	private String referal_hospital;

	@Column(name = "patient_reference")
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

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCareTakerName() {
		return careTakerName;
	}

	public void setCareTakerName(String careTakerName) {
		this.careTakerName = careTakerName;
	}

	public Timestamp getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Timestamp dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getReferalDoctor() {
		return referalDoctor;
	}

	public void setReferalDoctor(String referalDoctor) {
		this.referalDoctor = referalDoctor;
	}

	public String getRegistrationMode() {
		return registrationMode;
	}

	public void setRegistrationMode(String registrationMode) {
		this.registrationMode = registrationMode;
	}

	public String getPatinetType() {
		return patinetType;
	}

	public void setPatinetType(String patinetType) {
		this.patinetType = patinetType;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public boolean isSameAsCurrentAddress() {
		return sameAsCurrentAddress;
	}

	public void setSameAsCurrentAddress(boolean sameAsCurrentAddress) {
		this.sameAsCurrentAddress = sameAsCurrentAddress;
	}

	public String getPermanentAddress() {
		return permanentAddress;
	}

	public void setPermanentAddress(String permanentAddress) {
		this.permanentAddress = permanentAddress;
	}

	public String getPermanentCountry() {
		return permanentCountry;
	}

	public void setPermanentCountry(String permanentCountry) {
		this.permanentCountry = permanentCountry;
	}

	public String getPermanentState() {
		return permanentState;
	}

	public void setPermanentState(String permanentState) {
		this.permanentState = permanentState;
	}

	public String getPermanentCity() {
		return permanentCity;
	}

	public void setPermanentCity(String permanentCity) {
		this.permanentCity = permanentCity;
	}

	public String getPermanentZip() {
		return permanentZip;
	}

	public void setPermanentZip(String permanentZip) {
		this.permanentZip = permanentZip;
	}

	public String getPrimaryContact() {
		return primaryContact;
	}

	public void setPrimaryContact(String primaryContact) {
		this.primaryContact = primaryContact;
	}

	public String getSecondaryContact() {
		return secondaryContact;
	}

	public void setSecondaryContact(String secondaryContact) {
		this.secondaryContact = secondaryContact;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getReferal_hospital() {
		return referal_hospital;
	}

	public void setReferal_hospital(String referal_hospital) {
		this.referal_hospital = referal_hospital;
	}

	public String getPatientReference() {
		return patientReference;
	}

	public void setPatientReference(String patientReference) {
		this.patientReference = patientReference;
	}

}