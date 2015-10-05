package com.ahana.api.domain.common;

import com.ahana.api.common.AhanaVO;

@SuppressWarnings("serial")
public class IDValuePair implements AhanaVO{

	private String oid;
	
	private String name;
	
	private String status;

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}