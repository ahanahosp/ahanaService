package com.ahana.api.domain.common;

import java.util.List;
import java.util.Map;

import com.ahana.commons.system.domain.common.AhanaVO;

@SuppressWarnings("serial")
public class SearchRequest implements AhanaVO {

	private String oid;

	private String status = "ACT";

	private int noOfRecords = 5;

	private int pageIndex = 0;

	private boolean pagination = true;

	private int totalRecords;

	private String url;

	private String type;

	private List<Map<String, Object>> response;

	private List<AhanaVO> objectResponse;

	public int getNoOfRecords() {
		return noOfRecords;
	}

	public void setNoOfRecords(int noOfRecords) {
		this.noOfRecords = noOfRecords;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public boolean isPagination() {
		return pagination;
	}

	public void setPagination(boolean pagination) {
		this.pagination = pagination;
	}

	public int getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
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

	public List<Map<String, Object>> getResponse() {
		return response;
	}

	public void setResponse(List<Map<String, Object>> response) {
		this.response = response;
	}

	public List<AhanaVO> getObjectResponse() {
		return objectResponse;
	}

	public void setObjectResponse(List<AhanaVO> objectResponse) {
		this.objectResponse = objectResponse;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}