package com.ahana.api.system.security.error;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AhanaResponse {

	public static final String Ok = "Ok";

	public static final String Error = "Error";

	private String status;

	private List<ResponseError> errors;

	private Map<String, Object> paramObjectsMap = new HashMap<String, Object>();

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public final void setErrors(final List<ResponseError> errors) {
		this.errors = errors;
	}

	public final List<ResponseError> getErrors() {
		if (errors == null) {
			errors = new ArrayList<ResponseError>();
		}
		return errors;
	}

	public final Map<String, Object> getParamObjectsMap() {
		return paramObjectsMap;
	}

	public final void setParamObjectsMap(Map<String, Object> paramObjectsMap) {
		this.paramObjectsMap = paramObjectsMap;
	}

	public final void clearParamMap(final String key) {
		paramObjectsMap.remove(key);
	}
}