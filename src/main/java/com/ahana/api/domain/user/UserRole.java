package com.ahana.api.domain.user;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.Valid;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import com.ahana.api.common.AhanaVO;
import com.ahana.api.common.ErrorConstants;

@Entity
@Table(name = "user_roles")
@IdClass(UserRolePK.class)
@NamedQueries({ @NamedQuery(name = "getUserRolesOidByUserOid", query = "select userRole.roleOid from UserRole userRole where userRole.userOid= :userOid") })
public class UserRole implements Serializable, AhanaVO {

	private static final long serialVersionUID = 78018374285740564L;

	public static final String GET_USER_ROLES_OID_BY_USER_OID = "getUserRolesOidByUserOid";

	public UserRole() {
	}

	public UserRole(UserRolePK userRolePK) {
		userOid = userRolePK.getUserOid();
		roleOid = userRolePK.getRoleOid();
	}

	@Id
	@NotBlank(message = ErrorConstants.USER_OID_IS_REQUIRED)
	@Length(min = 20, max = 20, message = ErrorConstants.USER_OID_IS_INVALID_LENGTH)
	@Column(name = "user_oid", insertable = true, updatable = true)
	private String userOid;

	@Id
	@Column(name = "role_oid", insertable = true, updatable = true)
	private String roleOid;
	
	@Transient
	@Valid
	@NotEmpty(message = ErrorConstants.ROLE_NAME_REQUIRED)
	private List<String> roleOids;

	public String getUserOid() {
		return userOid;
	}

	public void setUserOid(String userOid) {
		this.userOid = userOid;
	}

	public String getRoleOid() {
		return roleOid;
	}

	public void setRoleOid(String roleOid) {
		this.roleOid = roleOid;
	}

	public List<String> getRoleOids() {
		return roleOids;
	}

	public void setRoleOids(List<String> roleOids) {
		this.roleOids = roleOids;
	}
}