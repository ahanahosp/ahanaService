package com.ahana.api.domain.user;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.ahana.api.common.AhanaVO;

@SuppressWarnings("serial")
@Entity
@Table(name = "role_rights")
public class RoleRights implements AhanaVO {

	@Id
	@GeneratedValue(generator = "IdGenerator")
	@GenericGenerator(name = "IdGenerator", strategy = "com.ahana.api.common.IdGenerator", parameters = {
			@Parameter(name = "table", value = "seed_container"),
			@Parameter(name = "column", value = "high_oid"),
			@Parameter(name = "install_id", value = "seed_id"),
			@Parameter(name = "max_lo", value = "100000") })
	@Column(name = "oid")
	private String oid;

	@Column(name = "role_oid")
	private String roleOid;

	@Column(name = "module_oid")
	private String moduleOid;

	@Column(name = "organization_oid")
	private String organizationOid;

	private List<String> moduleOids;

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public String getRoleOid() {
		return roleOid;
	}

	public void setRoleOid(String roleOid) {
		this.roleOid = roleOid;
	}

	public String getModuleOid() {
		return moduleOid;
	}

	public void setModuleOid(String moduleOid) {
		this.moduleOid = moduleOid;
	}

	public String getOrganizationOid() {
		return organizationOid;
	}

	public void setOrganizationOid(String organizationOid) {
		this.organizationOid = organizationOid;
	}

	public List<String> getModuleOids() {
		return moduleOids;
	}

	public void setModuleOids(List<String> moduleOids) {
		this.moduleOids = moduleOids;
	}

}