package com.ahana.api.system.security.exception;

import java.util.Map;

public interface AhanaException {

    String getShortMessage();

    void addRuntimeDataToExceptionMap(String key, String value);

    Map<String,String> getExceptionMap();

}
