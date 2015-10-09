package com.ahana.api.domain.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.validator.constraints.NotBlank;

import com.ahana.api.common.AhanaVO;
import com.ahana.api.common.Constants;
import com.ahana.api.common.ErrorConstants;

@SuppressWarnings("serial")
@Entity
@Table(name = "roles")
@NamedQueries({ @NamedQuery(name = "getRoleByOid", query = "from Roles ro where ro.oid= :roleOid") })
public class Roles implements AhanaVO {

	@Id
	@GeneratedValue(generator = "IdGenerator")
	@GenericGenerator(name = "IdGenerator", strategy = "com.ahana.api.common.IdGenerator", parameters = {
			@Parameter(name = "table", value = "seed_container"),
			@Parameter(name = "column", value = "high_oid"),
			@Parameter(name = "install_id", value = "seed_id"),
			@Parameter(name = "max_lo", value = "100000") })
	@Column(name = "oid")
	private String oid;

	@NotBlank(message=ErrorConstants.ROLE_NAME_REQUIRED)
	@Size(message=ErrorConstants.ROLE_NAME_INVALID_LENGTH,min=2,max=50)
	@Pattern(regexp="[a-z-A-Z]*",message=ErrorConstants.ROLE_MUST_BE_ALPHABETICAL)
	@Column(name = "role_name",unique=true,nullable=false,length=50)
	private String roleName;

	@Column(name="status",length=5)
	private String roleStatus;
	
	public Roles(){
		this.roleStatus=Constants.ACT;
	}

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleStatus() {
		return roleStatus;
	}

	public void setRoleStatus(String roleStatus) {
		this.roleStatus = roleStatus;
	}
}
