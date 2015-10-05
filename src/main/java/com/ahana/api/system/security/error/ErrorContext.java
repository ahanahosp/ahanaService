package com.ahana.api.system.security.error;

import java.util.HashMap;
import java.util.Map;

public class ErrorContext {

    private Map<String, Object> errorContextValues = new HashMap<String, Object>();

    public Map<String, Object> getErrorContextValues() {
        return errorContextValues;
    }

    public void setErrorContextValues(Map<String, Object> errorContextValues) {
        this.errorContextValues = errorContextValues;
    }

    public void put(String key, Object value) {
        errorContextValues.put(key, value);
    }

    public void putAll(Map<String,String> values) {
        errorContextValues.putAll(values);
    }

    public Object get(String key) {
        return errorContextValues.get(key);
    }
}