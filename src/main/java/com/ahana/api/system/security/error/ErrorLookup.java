package com.ahana.api.system.security.error;

public interface ErrorLookup {

    ResponseError findErrorByToken(String token);
}
