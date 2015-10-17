package com.ahana.api.domain.user;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Pattern;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ahana.api.common.AhanaVO;
import com.ahana.api.common.ErrorConstants;
import com.ahana.api.common.mail.LookupConstants;

@SuppressWarnings("serial")
@Entity
@Table(name = "user_profile")
@NamedQueries({
		@NamedQuery(name = "getUserProfileByUserId", query = "from UserProfile userv where userv.userId= :userId"),
		@NamedQuery(name = "getUserProfileByUserOid", query = "from UserProfile user where user.oid= :userOid"),
		@NamedQuery(name = "getOldPassword", query = "select user.password from UserProfile user where user.oid= :userOid") })
public class UserProfile implements UserDetails, AhanaVO {

	public static final String STATUS_USER_ACTIVE = "ACT";
	public static final String GET_USER_PROFILE_BY_USER_ID = "getUserProfileByUserId";
	public static final String GET_USER_PROFILE_BY_USER_OID = "getUserProfileByUserOid";
	public static final String GET_OLD_PASSWORD = "getOldPassword";

	@Id
	@GeneratedValue(generator = "IdGenerator")
	@GenericGenerator(name = "IdGenerator", strategy = "com.ahana.api.common.IdGenerator", parameters = {
			@Parameter(name = "table", value = "seed_container"), @Parameter(name = "column", value = "high_oid"),
			@Parameter(name = "install_id", value = "seed_id"), @Parameter(name = "max_lo", value = "100000") })
	@Column(name = "oid")
	private String oid;

	@NotBlank(message = ErrorConstants.SALUTATION_IS_REQUIRED)
	@Length(max = 10, min = 2, message = ErrorConstants.SALUTATION_IS_INVALID_LENGTH)
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

	@NotBlank(message = ErrorConstants.PASSWORD_IS_REQUIRED)
	@Length(max = 50, min = 3, message = ErrorConstants.PASSWORD_LENGTH_IS_INVALID)
	@Column(name = "password", nullable = false, length = 100, updatable = false)
	private String password = "cc03e747a6afbbcbf8be7668acfebee5";// By default
																	// test123

	@NotBlank(message = ErrorConstants.USER_ID_IS_REQUIRED)
	@Length(max = 100, min = 5, message = ErrorConstants.USER_ID_LENGTH_IS_INVALID)
	@Pattern(regexp = "[a-z-A-Z-0-9]*", message = ErrorConstants.USER_ID_IS_INVALID_FORMAT)
	@Column(name = "user_id", nullable = false, unique = true, length = 100)
	private String userId;

	@NotBlank(message = ErrorConstants.EMAIL_ID_IS_REQUIRED)
	@Length(max = 100, min = 10, message = ErrorConstants.EMAIL_ID_LENGTH_IS_INVALID)
	@Email(message = ErrorConstants.EMAIL_ID_IS_INVALID)
	@Column(name = "email_id", nullable = false, unique = true, length = 100)
	private String emailId;

	@NotBlank(message = ErrorConstants.MOBILE_NUMBER_IS_REQUIRED)
	@Length(max = 12, min = 10, message = ErrorConstants.MOBILE_NUMBER_IS_INVALID_LENGTH)
	@Pattern(regexp = "[0-9]*", message = ErrorConstants.MOBILE_NUMBER_MUST_BE_NUMBER)
	@Column(name = "mobile_no", nullable = false, length = 12)
	private String mobileNo;

	@Column(name = "user_status", nullable = false, length = 5, updatable = false)
	private String userStatus;

	@Column(name = "password_exp_date", nullable = false, updatable = false)
	private Timestamp inactivationDate;

	@Column(name = "designation")
	private String designation;

	@Column(name = "speciality")
	private String speciality;

	@Column(name = "care_provider")
	private String careProvider;

	@Column(name = "address")
	private String address;

	@Column(name = "country")
	private String country;

	@Column(name = "state")
	private String state;

	@Column(name = "city")
	private String city;

	@Column(name = "zip")
	private String zip;

	@Column(name = "activation_date", nullable = false, updatable = false)
	private Timestamp activationDate;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_roles", joinColumns = { @JoinColumn(name = "user_oid") }, inverseJoinColumns = {
			@JoinColumn(name = "role_oid") })
	private Set<Roles> roles = new HashSet<Roles>();

	@Transient
	private boolean enabled;

	@Transient
	private boolean accountNonExpired = false;

	@Transient
	private boolean credentialsNonExpired;

	@Transient
	private boolean accountNonLocked = false;

	@Transient
	private Collection<GrantedAuthority> authorities;

	@Transient
	private String roleOid;

	public UserProfile() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, 1); // to get previous year add -1
		this.inactivationDate = new Timestamp(cal.getTimeInMillis());
		this.activationDate = new Timestamp(System.currentTimeMillis());
		this.userStatus = STATUS_USER_ACTIVE;
		this.careProvider = "FALSE";
		this.country=LookupConstants.DEFAULT_COUNTRY;
		this.state=LookupConstants.DEFAULT_STATE;
		this.city=LookupConstants.DEFAULT_CITY;
	}

	public UserProfile(String id, String username, String password, boolean enabled, boolean accountNonExpired,
			boolean accountNonLocked, boolean credentialsNonExpired, Collection<GrantedAuthority> authorities,
			String roleOid) throws IllegalArgumentException {
		this.oid = id;
		this.userId = username;
		this.password = password;
		this.enabled = enabled;
		this.accountNonExpired = accountNonExpired;
		this.credentialsNonExpired = credentialsNonExpired;
		this.accountNonLocked = accountNonLocked;
		this.authorities = authorities;
		this.roleOid = roleOid;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return userId;
	}

	@Override
	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	public Set<Roles> getRoles() {
		return roles;
	}

	public void setRoles(Set<Roles> roles) {
		this.roles = roles;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	public void setAuthorities(Collection<GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	public boolean equals(Object o) {
		if (!(o instanceof UserProfile)) {
			return false;
		}
		UserProfile rhs = (UserProfile) o;
		if (oid != null && rhs.oid != null) {
			return new EqualsBuilder().append(oid, rhs.oid).isEquals();
		} else {
			return new EqualsBuilder().append(userId, rhs.userId).isEquals();
		}
	}

	public int hashCode() {
		if (oid != null) {
			return new HashCodeBuilder(05, 19).append(oid).toHashCode();
		} else {
			return new HashCodeBuilder(05, 19).append(userId).toHashCode();
		}
	}

	public String getRoleOid() {
		return roleOid;
	}

	public void setRoleOid(String roleOid) {
		this.roleOid = roleOid;
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

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getSpeciality() {
		return speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}

	public String getCareProvider() {
		return careProvider;
	}

	public void setCareProvider(String careProvider) {
		this.careProvider = careProvider;
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

	public Timestamp getInactivationDate() {
		return inactivationDate;
	}

	public void setInactivationDate(Timestamp inactivationDate) {
		this.inactivationDate = inactivationDate;
	}

	public Timestamp getActivationDate() {
		return activationDate;
	}

	public void setActivationDate(Timestamp activationDate) {
		this.activationDate = activationDate;
	}

}