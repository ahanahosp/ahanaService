package com.ahana.api.domain.user;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.apache.commons.lang3.builder.HashCodeBuilder;


@Embeddable
@AttributeOverrides({
		@AttributeOverride(name = "userOid", column = @Column(name = "user_oid")),
		@AttributeOverride(name = "roleOid", column = @Column(name = "role_oid")) })
public class UserRolePK implements Serializable {

	private static final long serialVersionUID = -7460093900781470614L;

	private String userOid;

	private String roleOid;


	public UserRolePK() {
	}

	public UserRolePK(String userOid, String roleOid) {
		this.userOid = userOid;
		this.roleOid = roleOid;
	}


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

	/**
	 * {@inheritDoc}
	 */
	public final boolean equals(final Object o) {

		UserRolePK userRolePK = (UserRolePK) o;

        if (userRolePK.getUserOid() == this.userOid
           && userRolePK.getRoleOid() == this.roleOid) {
			return true;
		}
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	public final int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}
}