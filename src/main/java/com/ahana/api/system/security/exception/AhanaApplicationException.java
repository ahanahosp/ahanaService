package com.ahana.api.system.security.exception;


public class AhanaApplicationException extends AhanaRuntimeException {

    private static final long serialVersionUID = 1L;

    public AhanaApplicationException(final String msg) {
        super(msg);
    }

    public AhanaApplicationException(final String msg, final Throwable cause) {
        super(msg, cause);
    }
}