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

import com.ahana.api.common.AhanaVO;
import com.ahana.api.common.ErrorConstants;
import com.ahana.api.common.RegConstants;

@Entity
@Table(name = "category_item")
@NamedQueries({ @NamedQuery(name = "getAccountHeadByOid", query = "from AccountHead ah where ah.oid= :accountHeadOid") })
public class AccountHead implements AhanaVO {

	private static final long serialVersionUID = 1586160714854378399L;

	@Id
	@GeneratedValue(generator = "IdGenerator")
	@GenericGenerator(name = "IdGenerator", strategy = "com.ahana.api.common.IdGenerator", parameters = {
			@Parameter(name = "table", value = "seed_container"), @Parameter(name = "column", value = "high_oid"),
			@Parameter(name = "install_id", value = "seed_id"), @Parameter(name = "max_lo", value = "100000") })
	@Column(name = "oid")
	private String oid;

	@NotBlank(message = ErrorConstants.CATEGORY_IS_REQUIRED)
	@Length(min = 3, max = 100, message = ErrorConstants.CATEGORY_LENGTH_IS_INVALID)
	@Pattern(regexp = RegConstants.ALPHA_NUMERIC, message = ErrorConstants.CATEGORY_FORMAT_IS_INVALID)
	@Column(name = "category")
	private String category;

	@Column(name = "desciption")
	private String desciption;

	@Column(name = "status")
	private String status;

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDesciption() {
		return desciption;
	}

	public void setDesciption(String desciption) {
		this.desciption = desciption;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
