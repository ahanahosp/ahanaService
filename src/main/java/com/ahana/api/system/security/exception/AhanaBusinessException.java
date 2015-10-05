package com.ahana.api.system.security.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.core.NestedCheckedException;

public class AhanaBusinessException extends NestedCheckedException implements	AhanaException {

	private static final long serialVersionUID = 1L;

	private String errorConstant;

	private Map<String,String> exceptionMap = new HashMap<String,String>();

	public AhanaBusinessException(final String msg) {
		super(msg);
		this.errorConstant = msg;
	}

	public AhanaBusinessException(final String msg, final Throwable cause) {
		super(msg, cause);
		this.errorConstant = msg;
	}

	public final String getErrorConstant() {
		return errorConstant;
	}

	@Override
	public String getShortMessage() {
		return errorConstant;
	}

	@Override
	public Map<String, String> getExceptionMap() {
		return this.exceptionMap;
	}

	@Override
	public void addRuntimeDataToExceptionMap(String key, String value) {
		exceptionMap.put(key,value);
	}

}