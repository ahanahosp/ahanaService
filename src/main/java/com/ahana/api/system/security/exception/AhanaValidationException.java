package com.ahana.api.system.security.exception;

import java.util.List;

import org.springframework.validation.FieldError;

public class AhanaValidationException extends AhanaBusinessException {

    private static final long serialVersionUID = 1L;

    private List<FieldError> errors;

    private int resourceObjectIndex = 1;

    public List<FieldError> getErrors() {
        return errors;
    }

    public void setErrors(List<FieldError> errors) {
        this.errors = errors;
    }

    public int getResourceObjectIndex() {
        return resourceObjectIndex;
    }

    public void setResourceObjectIndex(int resourceObjectIndex) {
        this.resourceObjectIndex = resourceObjectIndex;
    }

    public AhanaValidationException(final String msg) {
        super(msg);
    }

    public AhanaValidationException(final String msg, final Throwable cause) {
        super(msg, cause);
    }

    public AhanaValidationException(final List<FieldError> errors, final String msg) {
        super(msg);
        this.errors = errors;
    }

    public AhanaValidationException(final List<FieldError> errors, final String msg, final int resourceObjectIndex) {
        super(msg);
        this.errors = errors;
        this.resourceObjectIndex = resourceObjectIndex;
    }
}