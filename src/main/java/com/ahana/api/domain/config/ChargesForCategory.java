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

	@Column(name = "sub_category_oid")
	private String subCategoryOid;

	@Column(name = "default_value")
	private double defaultValue;

	@Column(name = "free")
	private double free;

	@Column(name = "standard_default")
	private double standardDefault;

	@Column(name = "priority")
	private double priority;

	@Column(name = "priority_free")
	private double priorityFree;

	@Column(name = "vip")
	private double vip;

	@Column(name = "vip_free")
	private double vipFree;

	@Column(name = "ac")
	private double ac;

	@Column(name = "ac_small")
	private double acSmall;

	@Column(name = "deluxe")
	private double deluxe;

	@Column(name = "economy_non_ac")
	private double ecnomyNonAc;

	@Column(name = "non_ac")
	private double nonAc;

	@Column(name = "non_ac_small")
	private double nonAcSmall;

	@Column(name = "semi_economy_non_ac")
	private double semiEconomyNonAc;

	@Column(name = "special_care_room")
	private double specialCareRoom;

	@Column(name = "suite")
	private double suite;

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

	public double getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(double defaultValue) {
		this.defaultValue = defaultValue;
	}

	public double getFree() {
		return free;
	}

	public void setFree(double free) {
		this.free = free;
	}

	public double getStandardDefault() {
		return standardDefault;
	}

	public void setStandardDefault(double standardDefault) {
		this.standardDefault = standardDefault;
	}

	public double getPriority() {
		return priority;
	}

	public void setPriority(double priority) {
		this.priority = priority;
	}

	public double getPriorityFree() {
		return priorityFree;
	}

	public void setPriorityFree(double priorityFree) {
		this.priorityFree = priorityFree;
	}

	public double getVip() {
		return vip;
	}

	public void setVip(double vip) {
		this.vip = vip;
	}

	public double getVipFree() {
		return vipFree;
	}

	public void setVipFree(double vipFree) {
		this.vipFree = vipFree;
	}

	public double getAc() {
		return ac;
	}

	public void setAc(double ac) {
		this.ac = ac;
	}

	public double getAcSmall() {
		return acSmall;
	}

	public void setAcSmall(double acSmall) {
		this.acSmall = acSmall;
	}

	public double getDeluxe() {
		return deluxe;
	}

	public void setDeluxe(double deluxe) {
		this.deluxe = deluxe;
	}

	public double getEcnomyNonAc() {
		return ecnomyNonAc;
	}

	public void setEcnomyNonAc(double ecnomyNonAc) {
		this.ecnomyNonAc = ecnomyNonAc;
	}

	public double getNonAc() {
		return nonAc;
	}

	public void setNonAc(double nonAc) {
		this.nonAc = nonAc;
	}

	public double getNonAcSmall() {
		return nonAcSmall;
	}

	public void setNonAcSmall(double nonAcSmall) {
		this.nonAcSmall = nonAcSmall;
	}

	public double getSemiEconomyNonAc() {
		return semiEconomyNonAc;
	}

	public void setSemiEconomyNonAc(double semiEconomyNonAc) {
		this.semiEconomyNonAc = semiEconomyNonAc;
	}

	public double getSpecialCareRoom() {
		return specialCareRoom;
	}

	public void setSpecialCareRoom(double specialCareRoom) {
		this.specialCareRoom = specialCareRoom;
	}

	public double getSuite() {
		return suite;
	}

	public void setSuite(double suite) {
		this.suite = suite;
	}
}