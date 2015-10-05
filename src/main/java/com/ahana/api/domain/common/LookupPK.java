package com.ahana.api.domain.common;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.apache.commons.lang3.builder.HashCodeBuilder;

@Embeddable
@AttributeOverrides({
		@AttributeOverride(name = "code", column = @Column(name = "code")),
		@AttributeOverride(name = "category", column = @Column(name = "category")) })
class LookupPK implements Serializable {

	private static final long serialVersionUID = -7460093900781470614L;

	private String code;

	private String category;

	public LookupPK() {
	}

	public LookupPK(String code, String category) {
		this.code = code;
		this.category = category;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public final boolean equals(final Object o) {
		LookupPK anotherPK = (LookupPK) o;
        if (anotherPK.getCode().equalsIgnoreCase(this.code)
           && anotherPK.getCategory().equalsIgnoreCase(this.category)) {
			return true;
		}
		return false;
	}

	public final int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}
}