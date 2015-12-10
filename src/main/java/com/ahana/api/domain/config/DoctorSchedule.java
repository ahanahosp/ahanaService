package com.ahana.api.domain.config;

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

import com.ahana.commons.system.domain.common.AhanaVO;
import com.ahana.commons.system.security.error.CommonErrorConstants;
import com.ahana.commons.system.security.util.Constants;
import com.ahana.commons.system.security.util.RegConstants;

@Entity
@Table(name = "doctor_schedule")
@NamedQueries({ @NamedQuery(name = "getDoctorScheduleByOid", query = "from DoctorSchedule ds where ds.oid= :dsOid"),
		@NamedQuery(name = "getScheduledDoctorDetailsByOid", query = "from DoctorSchedule ds where ds.doctorOid= :doctorOid") })
public class DoctorSchedule implements AhanaVO {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "IdGenerator")
	@GenericGenerator(name = "IdGenerator", strategy = "com.ahana.commons.system.security.util.IdGenerator", parameters = {
			@Parameter(name = "table", value = "seed_container"), @Parameter(name = "column", value = "high_oid"),
			@Parameter(name = "install_id", value = "seed_id"), @Parameter(name = "max_lo", value = "100000") })
	@Column(name = "oid")
	private String oid;

	@NotBlank(message = CommonErrorConstants.DOCTOR_NAME_IS_REQUIRED)
	@Column(name = "doctor_oid")
	private String doctorOid;

	@NotBlank(message = CommonErrorConstants.VISITING_DAY_IS_REQUIRED)
	@Size(message = CommonErrorConstants.VISITING_DAY_LENGTH_IS_INVALID, min = 3, max = 10)
	@Pattern(regexp = RegConstants.ALPHAPET_SPACE, message = CommonErrorConstants.VISITING_DAY_INVALID_FORMAT)
	@Column(name = "visiting_day")
	private String visitingDay;

	@NotBlank(message = CommonErrorConstants.START_TIME_IS_REQUIRED)
	@Size(message = CommonErrorConstants.START_TIME_LENGTH_IS_INVALID, min = 5, max = 8)
	@Pattern(regexp = RegConstants.TIME, message = CommonErrorConstants.START_TIME_INVALID_FORMAT)
	@Column(name = "start_time")
	private String startTime;

	@NotBlank(message = CommonErrorConstants.END_TIME_IS_REQUIRED)
	@Size(message = CommonErrorConstants.END_TIME_LENGTH_IS_INVALID, min = 5, max = 8)
	@Pattern(regexp = RegConstants.TIME, message = CommonErrorConstants.END_TIME_INVALID_FORMAT)
	@Column(name = "end_time")
	private String endTime;

	@Column(name = "status")
	private String status;

	public DoctorSchedule() {
		this.status = Constants.ACT;
	}

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public String getDoctorOid() {
		return doctorOid;
	}

	public void setDoctorOid(String doctorOid) {
		this.doctorOid = doctorOid;
	}

	public String getVisitingDay() {
		return visitingDay;
	}

	public void setVisitingDay(String visitingDay) {
		this.visitingDay = visitingDay;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}