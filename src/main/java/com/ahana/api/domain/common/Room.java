package com.ahana.api.domain.common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.ahana.commons.system.domain.common.AhanaVO;
import com.ahana.commons.system.security.error.CommonErrorConstants;
import com.ahana.commons.system.security.util.Constants;
import com.ahana.commons.system.security.util.RegConstants;

@SuppressWarnings("serial")
@Entity
@Table(name = "room")
@NamedQueries({ @NamedQuery(name = "getRoomByOid", query = "from Room r where r.oid= :roomOid") })
public class Room implements AhanaVO {

	@Id
	@GeneratedValue(generator = "IdGenerator")
	@GenericGenerator(name = "IdGenerator", strategy = "com.ahana.commons.system.security.util.IdGenerator", parameters = {
			@Parameter(name = "table", value = "seed_container"), @Parameter(name = "column", value = "high_oid"),
			@Parameter(name = "install_id", value = "seed_id"), @Parameter(name = "max_lo", value = "100000") })
	@Column(name = "oid")
	private String oid;

	@NotBlank(message = CommonErrorConstants.BED_NAME_IS_REQUIRED)
	@Length(min = 3, max = 100, message = CommonErrorConstants.BED_NAME_LENGTH_IS_INVALID)
	@Pattern(regexp = RegConstants.ALPHA_NUMERIC_SPACE_HYPEN, message = CommonErrorConstants.BED_NAME_IS_INVALID_FORMAT)
	@Column(name = "bed_name")
	private String bedName;

	@NotBlank(message = CommonErrorConstants.WARD_NAME_IS_REQUIRED)
	@Column(name = "ward_oid", length = 20)
	private String wardOid;

	@Column(name = "occupancy_status")
	private String occupancyStatus;

	@NotBlank(message = CommonErrorConstants.MAINTENANCE_NAME_IS_REQUIRED)
	@Column(name = "maintenance_oid", length = 20)
	private String maintenanceOid;

	@Column(name = "status")
	private String status;

	public Room() {
		this.status = Constants.ACT;
	}

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public String getBedName() {
		return bedName;
	}

	public void setBedName(String bedName) {
		this.bedName = bedName;
	}

	public String getWardOid() {
		return wardOid;
	}

	public void setWardOid(String wardOid) {
		this.wardOid = wardOid;
	}

	public String getOccupancyStatus() {
		return occupancyStatus;
	}

	public void setOccupancyStatus(String occupancyStatus) {
		this.occupancyStatus = occupancyStatus;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMaintenanceOid() {
		return maintenanceOid;
	}

	public void setMaintenanceOid(String maintenanceOid) {
		this.maintenanceOid = maintenanceOid;
	}

}