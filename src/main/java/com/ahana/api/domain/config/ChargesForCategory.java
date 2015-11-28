package com.ahana.api.domain.config;

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
import com.ahana.commons.system.security.util.RegConstants;

@Entity
@Table(name = "charges_for_category")
@NamedQueries({
		@NamedQuery(name = "getChargesForCategoryByOid", query = "from ChargesForCategory cc where cc.oid= :chargesForCategoryOid") })
public class ChargesForCategory implements AhanaVO {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "IdGenerator")
	@GenericGenerator(name = "IdGenerator", strategy = "com.ahana.commons.system.security.util.IdGenerator", parameters = {
			@Parameter(name = "table", value = "seed_container"), @Parameter(name = "column", value = "high_oid"),
			@Parameter(name = "install_id", value = "seed_id"), @Parameter(name = "max_lo", value = "100000") })
	@Column(name = "oid")
	private String oid;

	@NotBlank(message = CommonErrorConstants.CATEGORY_IS_REQUIRED)
	@Length(min = 3, max = 100, message = CommonErrorConstants.CATEGORY_LENGTH_IS_INVALID)
	@Pattern(regexp = RegConstants.ALPHA_NUMERIC_SPACE_HYPEN, message = CommonErrorConstants.CATEGORY_FORMAT_IS_INVALID)
	@Column(name = "category")
	private String category;
	
	@NotBlank(message = CommonErrorConstants.SUBCATEGORY_IS_REQUIRED)
	@Column(name = "sub_category_oid")
	private String subCategoryOid;

	@Column(name = "default_value")
	private String defaultValue;

	@Column(name = "free")
	private String free;

	@Column(name = "standard_default")
	private String standardDefault;

	@Column(name = "priority")
	private String priority;

	@Column(name = "priority_free")
	private String priorityFree;

	@Column(name = "vip")
	private String vip;

	@Column(name = "vip_free")
	private String vipFree;

	@Column(name = "ac")
	private String ac;

	@Column(name = "ac_small")
	private String acSmall;

	@Column(name = "deluxe")
	private String deluxe;

	@Column(name = "economy_non_ac")
	private String ecnomyNonAc;

	@Column(name = "non_ac")
	private String nonAc;

	@Column(name = "non_ac_small")
	private String nonAcSmall;

	@Column(name = "semi_economy_non_ac")
	private String semiEconomyNonAc;

	@Column(name = "special_care_room")
	private String specialCareRoom;

	@Column(name = "suite")
	private String suite;

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

	public String getSubCategoryOid() {
		return subCategoryOid;
	}

	public void setSubCategoryOid(String subCategoryOid) {
		this.subCategoryOid = subCategoryOid;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public String getFree() {
		return free;
	}

	public void setFree(String free) {
		this.free = free;
	}

	public String getStandardDefault() {
		return standardDefault;
	}

	public void setStandardDefault(String standardDefault) {
		this.standardDefault = standardDefault;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getPriorityFree() {
		return priorityFree;
	}

	public void setPriorityFree(String priorityFree) {
		this.priorityFree = priorityFree;
	}

	public String getVip() {
		return vip;
	}

	public void setVip(String vip) {
		this.vip = vip;
	}

	public String getVipFree() {
		return vipFree;
	}

	public void setVipFree(String vipFree) {
		this.vipFree = vipFree;
	}

	public String getAc() {
		return ac;
	}

	public void setAc(String ac) {
		this.ac = ac;
	}

	public String getAcSmall() {
		return acSmall;
	}

	public void setAcSmall(String acSmall) {
		this.acSmall = acSmall;
	}

	public String getDeluxe() {
		return deluxe;
	}

	public void setDeluxe(String deluxe) {
		this.deluxe = deluxe;
	}

	public String getEcnomyNonAc() {
		return ecnomyNonAc;
	}

	public void setEcnomyNonAc(String ecnomyNonAc) {
		this.ecnomyNonAc = ecnomyNonAc;
	}

	public String getNonAc() {
		return nonAc;
	}

	public void setNonAc(String nonAc) {
		this.nonAc = nonAc;
	}

	public String getNonAcSmall() {
		return nonAcSmall;
	}

	public void setNonAcSmall(String nonAcSmall) {
		this.nonAcSmall = nonAcSmall;
	}

	public String getSemiEconomyNonAc() {
		return semiEconomyNonAc;
	}

	public void setSemiEconomyNonAc(String semiEconomyNonAc) {
		this.semiEconomyNonAc = semiEconomyNonAc;
	}

	public String getSpecialCareRoom() {
		return specialCareRoom;
	}

	public void setSpecialCareRoom(String specialCareRoom) {
		this.specialCareRoom = specialCareRoom;
	}

	public String getSuite() {
		return suite;
	}

	public void setSuite(String suite) {
		this.suite = suite;
	}
}