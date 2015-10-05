package com.ahana.api.system.security.exception;


public class AhanaSystemException extends AhanaRuntimeException {

    private static final long serialVersionUID = 1L;

    public AhanaSystemException(final String msg) {
        super(msg);
    }

    public AhanaSystemException(final String msg, final Throwable cause) {
        super(msg, cause);

    }
}