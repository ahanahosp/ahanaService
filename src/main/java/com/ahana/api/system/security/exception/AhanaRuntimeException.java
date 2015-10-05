package com.ahana.api.system.security.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.core.NestedRuntimeException;

public class AhanaRuntimeException extends NestedRuntimeException implements AhanaException {

    private static final long serialVersionUID = 1L;

    private String errorConstant;

    private Map<String,String> exceptionMap = new HashMap<String,String>();

    public AhanaRuntimeException(final String msg) {
        super(msg);
        this.errorConstant = msg;
    }

    public AhanaRuntimeException(final String msg, final Throwable cause) {
        super(msg, cause);
        this.errorConstant = msg;
    }

    public String getErrorConstant() {
        return errorConstant;
    }

    public void setErrorConstant(String errorConstant) {
        this.errorConstant = errorConstant;
    }

    @Override
    public String getShortMessage() {
        return errorConstant;
    }

	@Override
	public Map<String, String> getExceptionMap() {
		return exceptionMap;
	}

	@Override
	public void addRuntimeDataToExceptionMap(String key, String value) {
		exceptionMap.put(key,value);
	}
}