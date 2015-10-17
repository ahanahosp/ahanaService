package com.ahana.api.domain.common;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import com.ahana.api.common.AhanaVO;
import com.ahana.api.common.ErrorConstants;
import com.ahana.api.common.mail.LookupConstants;

@SuppressWarnings("serial")
@Entity
@Table(name = "patient")
public class PatientRegistration implements AhanaVO {

	@Id
	@GeneratedValue(generator = "IdGenerator")
	@GenericGenerator(name = "IdGenerator", strategy = "com.ahana.api.common.IdGenerator", parameters = {
			@Parameter(name = "table", value = "seed_container"), @Parameter(name = "column", value = "high_oid"),
			@Parameter(name = "install_id", value = "seed_id"), @Parameter(name = "max_lo", value = "100000")
			//,@Parameter(name = "type", value = Constants.ID_PATIENT) 
			})
	@Column(name = "oid")
	private String oid;

	@NotNull(message = ErrorConstants.REGISTRATION_DATE_IS_REQUIRED)
	@Column(name = "registration_date", nullable = false)
	private Timestamp registrationDate;

	@NotBlank(message = ErrorConstants.FIRST_NAME_IS_REQUIRED)
	@Size(max = 50, min = 3, message = ErrorConstants.FIRST_NAME_IS_INVALID_LENGTH)
	@Pattern(regexp = "[a-z-A-Z]*", message = ErrorConstants.FIRST_NAME_MUST_BE_ALPHABETICAL)
	@Column(name = "first_name", nullable = false, length = 50)
	private String firstName;

	@NotBlank(message = ErrorConstants.FIRST_NAME_IS_REQUIRED)
	@Length(max = 50, min = 3, message = ErrorConstants.FIRST_NAME_IS_INVALID_LENGTH)
	@Pattern(regexp = "[a-z-A-Z]*", message = ErrorConstants.FIRST_NAME_MUST_BE_ALPHABETICAL)
	@Column(name = "last_name", length = 50)
	private String lastName;

	@NotBlank(message = ErrorConstants.GENDER_IS_REQUIRED)
	@Length(max = 6, min = 1, message = ErrorConstants.GENDER_IS_INVALID_LENGTH)
	@Pattern(regexp = "[a-z-A-Z]*", message = ErrorConstants.GENDER_MUST_BE_ALPHABETICAL)
	@Column(name = "gender", nullable = false, length = 6)
	private String gender;

	@Range(max=100,min=1,message = ErrorConstants.AGE_IS_REQUIRED)
	@Column(name = "age")
	private int age;

	@NotBlank(message = ErrorConstants.MOBILE_NUMBER_IS_REQUIRED)
	@Length(max = 12, min = 5, message = ErrorConstants.MOBILE_NUMBER_IS_INVALID_LENGTH)
	@Pattern(regexp = "[0-9]*", message = ErrorConstants.MOBILE_NUMBER_MUST_BE_NUMBER)
	@Column(name = "mobile")
	private String mobile;

	@Column(name = "address", length = 100)
	private String address;

	@Column(name = "country", length = 50)
	private String country;

	@Column(name = "state", length = 50)
	private String state;

	@Column(name = "city", length = 50)
	private String city;

	@Column(name = "zip", length = 10)
	private String zip;

	@Column(name = "category", length = 50)
	private String category;

	@Column(name = "billing", length = 50)
	private String billing;
	
	public PatientRegistration(){
		this.registrationDate=new Timestamp(System.currentTimeMillis());
		this.country=LookupConstants.DEFAULT_COUNTRY;
		this.state=LookupConstants.DEFAULT_STATE;
		this.city=LookupConstants.DEFAULT_CITY;
	}

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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
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

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getBilling() {
		return billing;
	}

	public void setBilling(String billing) {
		this.billing = billing;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	

}