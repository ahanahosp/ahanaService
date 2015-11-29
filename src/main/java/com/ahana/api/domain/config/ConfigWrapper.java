package com.ahana.api.domain.config;

import java.io.Serializable;
import java.util.List;

import com.ahana.commons.system.domain.user.Roles;

public class ConfigWrapper implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private List<Roles> roles;

	public List<Roles> getRoles() {
		return roles;
	}

	public void setRoles(List<Roles> roles) {
		this.roles = roles;
	}
	
}