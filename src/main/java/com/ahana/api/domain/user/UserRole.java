package com.ahana.api.domain.user;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.ahana.api.common.AhanaVO;

@Entity
@Table(name = "user_roles")
@IdClass(UserRolePK.class)
@NamedQueries({
    @NamedQuery(name = "getUserRolesOidByUserOid", query = "select userRole.roleOid from UserRole userRole where userRole.userOid= :userOid")
})
public class UserRole implements Serializable,AhanaVO{

	private static final long serialVersionUID = 78018374285740564L;

	public static final String GET_USER_ROLES_OID_BY_USER_OID = "getUserRolesOidByUserOid";

	public UserRole() {
	}

	public UserRole(UserRolePK userRolePK) {
		userOid = userRolePK.getUserOid();
		roleOid = userRolePK.getRoleOid();
	}

	@Id
	@Column(name = "user_oid",insertable=true,updatable=true)
	private String userOid;

	@Id
	@Column(name = "role_oid",insertable=true,updatable=true)
	private String roleOid;

	/**
	 * @return the userOid
	 */
	public String getUserOid() {
		return userOid;
	}

	/**
	 * @param userOid
	 */
	public void setUserOid(String userOid) {
		this.userOid = userOid;
	}

	/**
	 * @return the roleOid
	 */
	public String getRoleOid() {
		return roleOid;
	}

	/**
	 * @param roleOid the roleOid to set
	 */
	public void setRoleOid(String roleOid) {
		this.roleOid = roleOid;
	}



}
