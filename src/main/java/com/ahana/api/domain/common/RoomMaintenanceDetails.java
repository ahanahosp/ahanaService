package com.ahana.api.domain.common;

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
import com.ahana.api.common.RegConstants;

@Entity
@Table(name = "room_maintance_details")
@NamedQueries({
		@NamedQuery(name = "getRoomMaintenanceDetailsByOid", query = "from RoomMaintenanceDetails rm where rm.oid= :roomMaintenanceDetailsOid") })
public class RoomMaintenanceDetails implements AhanaVO {

	private static final long serialVersionUID = -2270364007661496860L;

	@Id
	@GeneratedValue(generator = "IdGenerator")
	@GenericGenerator(name = "IdGenerator", strategy = "com.ahana.api.common.IdGenerator", parameters = {
			@Parameter(name = "table", value = "seed_container"), @Parameter(name = "column", value = "high_oid"),
			@Parameter(name = "install_id", value = "seed_id"), @Parameter(name = "max_lo", value = "100000") })
	@Column(name = "oid")
	private String oid;

	@NotBlank(message = ErrorConstants.ROLE_NAME_REQUIRED)
	@Size(message = ErrorConstants.ROLE_NAME_INVALID_LENGTH, min = 2, max = 100)
	@Pattern(regexp = RegConstants.ALPHAPET_SPACE_HYPEN, message = ErrorConstants.ROLE_MUST_BE_ALPHABETICAL)
	@Column(name = "maintenance_name", unique = true, nullable = false, length = 100)
	private String maintenanceName;

	@Column(name = "status", length = 5)
	private String status;

	public RoomMaintenanceDetails() {
		this.status = Constants.ACT;
	}

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMaintenanceName() {
		return maintenanceName;
	}

	public void setMaintenanceName(String maintenanceName) {
		this.maintenanceName = maintenanceName;
	}

}
