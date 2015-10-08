package com.ahana.api.domain.user;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
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
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ahana.api.common.Constants;
import com.ahana.api.common.AhanaVO;
import com.ahana.api.common.ErrorConstants;


@SuppressWarnings("serial")
@Entity
@Table(name = "user_profile")
@NamedQueries({
    @NamedQuery(name = "getUserProfileByUserId", query = "from UserProfile userv where userv.userId= :userId"),
    @NamedQuery(name = "getUserProfileByUserOid", query = "from UserProfile user where user.oid= :userOid"),
    @NamedQuery(name = "getOldPassword", query = "select user.password from UserProfile user where user.oid= :userOid")
})
public class UserProfile implements UserDetails,AhanaVO {
	
	public static final String STATUS_USER_ACTIVE = "ACT";
	public static final String GET_USER_PROFILE_BY_USER_ID = "getUserProfileByUserId";
	public static final String GET_USER_PROFILE_BY_USER_OID = "getUserProfileByUserOid";
	public static final String GET_OLD_PASSWORD = "getOldPassword";

    @Id
    @GeneratedValue(generator = "IdGenerator")
	@GenericGenerator(name = "IdGenerator", strategy = "com.ahana.api.common.IdGenerator", parameters = {
			@Parameter(name = "table", value = "seed_container"),
			@Parameter(name = "column", value = "high_oid"),
			@Parameter(name = "install_id", value = "seed_id"),
			@Parameter(name = "max_lo", value = "100000") })
    @Column(name = "oid")
    private String oid;
    
    /*@NotBlank(message = ErrorConstants.SALUTATION_IS_REQUIRED)
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
	private String lastName;*/

    @Column(name = "password",nullable=false,length=100)
    private String password="cc03e747a6afbbcbf8be7668acfebee5";//By default test123

    @NotBlank(message = ErrorConstants.LAST_NAME_IS_REQUIRED)
	@Length(max = 50, min = 3, message = ErrorConstants.LAST_NAME_IS_INVALID_LENGTH)
	@Pattern(regexp = "[a-z-A-Z]*", message = ErrorConstants.LAST_NAME_MUST_BE_ALPHABETICAL)
    @Column(name = "user_id",nullable=false,unique=true,length=100)
    private String userId;

    @Column(name = "user_status",nullable=false,length=5)
    private String userStatus;

    @Column(name = "password_exp_date",nullable=false)
	private Timestamp passwordExpDate;

	@Column(name = "last_logged_on")
	private Timestamp lastLogon;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles", joinColumns = { @JoinColumn(name = "user_oid") }, inverseJoinColumns = { @JoinColumn(name = "role_oid") })
    private Set<Roles> roles = new HashSet<Roles>();
    
    @Column(name="language")
    private String language;
    
    @Transient
    private String confirmPassword;
    
    @Column(name="user_type")
    private String userType;
    
    @Column(name="account_locked")
    private boolean accountLocked;
    
    @Column(name="account_expired")
    private boolean accountExpired;
    
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
    private String currentServiceURI;
    
    @Transient
    private String roleOid;
    
    @Transient
    private Map<String, String> details;
    
    public UserProfile() {
        password = "";
        userId = "";
		this.passwordExpDate = new Timestamp(System.currentTimeMillis() + (86666666*90));//Adding 90 days from current
		this.language = Constants.DEFAULT_LOCALE;
		this.userStatus=STATUS_USER_ACTIVE;
		this.lastLogon = new Timestamp(System.currentTimeMillis());
    }

    public UserProfile(String id, String username, String password,boolean enabled,boolean accountNonExpired,
    		boolean accountNonLocked,boolean credentialsNonExpired,Collection<GrantedAuthority> authorities,
    		String language,Timestamp lastLogon,String roleOid,String userType)throws IllegalArgumentException {
        this.oid = id;
        this.userId = username;
        this.password = password;
        this.enabled = enabled;
        this.accountNonExpired = accountNonExpired;
        this.credentialsNonExpired = credentialsNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.authorities = authorities;
        this.language=language;
        this.lastLogon=lastLogon;
        this.roleOid=roleOid;
        this.userType=userType;
    }
    
	public Timestamp getLastLogon() {
		return lastLogon;
	}

	public void setLastLogon(Timestamp lastLogon) {
		this.lastLogon = lastLogon;
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

	public Timestamp getPasswordExpDate() {
		return passwordExpDate;
	}

	public void setPasswordExpDate(Timestamp passwordExpDate) {
		this.passwordExpDate = passwordExpDate;
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

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public boolean equals(Object o) {
		if ( !(o instanceof UserProfile) ) {
			return false;
		}
		UserProfile rhs = (UserProfile) o;
		if(oid != null && rhs.oid !=null){
			return new EqualsBuilder().append(oid, rhs.oid).isEquals();
		}else{
			return new EqualsBuilder().append(userId, rhs.userId).isEquals();
		}
	}

	public int hashCode() {
		if(oid != null){
			return new HashCodeBuilder(05,19).append(oid).toHashCode();
		}else{
			return new HashCodeBuilder(05,19).append(userId).toHashCode();
		}
	}

	public String getCurrentServiceURI() {
		return currentServiceURI;
	}

	public void setCurrentServiceURI(String currentServiceURI) {
		this.currentServiceURI = currentServiceURI;
	}

	public String getRoleOid() {
		return roleOid;
	}

	public void setRoleOid(String roleOid) {
		this.roleOid = roleOid;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public Map<String, String> getDetails() {
		return details;
	}

	public void setDetails(Map<String, String> details) {
		this.details = details;
	}

	public boolean isAccountLocked() {
		return accountLocked;
	}

	public void setAccountLocked(boolean accountLocked) {
		this.accountLocked = accountLocked;
	}

	public boolean isAccountExpired() {
		return accountExpired;
	}

	public void setAccountExpired(boolean accountExpired) {
		this.accountExpired = accountExpired;
	}
}