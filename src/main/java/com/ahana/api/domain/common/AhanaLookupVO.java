package com.ahana.api.domain.common;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@SuppressWarnings("serial")
@Entity
@Table(name = "lookup")
@NamedQueries({@NamedQuery(name = "getAllLookups", query = "select lookupinfo from AhanaLookupVO lookupinfo  order by lookupinfo.category"),
	@NamedQuery(name = "getAllLookupsByCategory", query = "select lookupinfo from AhanaLookupVO lookupinfo where lookupinfo.category=:category order by lookupinfo.category"),
	@NamedQuery(name = "getLookupByCode", query = "select lookupinfo from AhanaLookupVO lookupinfo where lookupinfo.code=:code")})
@IdClass(LookupPK.class)
public class AhanaLookupVO implements Serializable {

    public static final String GET_ALL_LOOKUPS = "getAllLookups";

    @Id
    @Column(name = "code",nullable=false,length=50)
    private String code;

    @Id
    @Column(name = "category",nullable=false,length=100)
    private String category;

    @Column(name = "description",nullable=false,length=100)
    private String description;

    @Column(name = "seq")
    private Integer seq;

    @Column(name = "deprecated")
    private Boolean deprecated;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Boolean getDeprecated() {
        return deprecated;
    }

    public void setDeprecated(Boolean deprecated) {
        this.deprecated = deprecated;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public final String getCategory() {
        return category;
    }

    public final void setCategory(final String catgry) {
        this.category = catgry;
    }

    public final String getDescription() {
        return description;
    }

    public final void setDescription(final String description) {
        this.description = description;
    }

    public Integer getSeq() {
        return seq;
    }

    public boolean equals(Object o) {
        if (!(o instanceof AhanaLookupVO)) {
            return false;
        }
        AhanaLookupVO rhs = (AhanaLookupVO) o;
        return new EqualsBuilder().append(code, rhs.code).append(category,rhs.category).isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder(05, 19).append(code).append(category).toHashCode();
    }
}