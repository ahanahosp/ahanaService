package com.ahana.api.system.security.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.AuthenticationException;

public class AhanaAuthenticationException extends AuthenticationException	implements AhanaException {

	private String errorConstant;

	private Map<String, String> exceptionMap = new HashMap<String, String>();

	private static final long serialVersionUID = 1L;

	public AhanaAuthenticationException(String msg) {
		super(msg);
		this.errorConstant = msg;
	}

	public AhanaAuthenticationException(final String msg, final Throwable cause) {
		super(msg, cause);
		this.errorConstant = msg;
	}

	public final String getErrorConstant() {
		return errorConstant;
	}

	public String getShortMessage() {
		return errorConstant;
	}

	public Map<String, String> getExceptionMap() {
		return this.exceptionMap;
	}

	public void addRuntimeDataToExceptionMap(String key, String value) {
		exceptionMap.put(key, value);
	}

}