package com.ahana.api.system.security.exception;

import org.springframework.security.authentication.AccountStatusException;

@SuppressWarnings("serial")
public class AhanaBadCredentialsException extends AccountStatusException {

	public AhanaBadCredentialsException(String msg) {
        super(msg);
    }

	public AhanaBadCredentialsException(String msg, Object extraInformation) {
        super(msg);
    }

    public AhanaBadCredentialsException(String msg, Throwable t) {
        super(msg, t);
    }
}
