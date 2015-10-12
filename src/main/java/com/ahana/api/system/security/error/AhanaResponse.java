package com.ahana.api.system.security.error;

import java.util.ArrayList;
import java.util.List;

public class AhanaResponse {

	public static final String Ok = "Ok";

	public static final String Error = "Error";

	private String status;

	private List<ResponseError> errors;

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

}