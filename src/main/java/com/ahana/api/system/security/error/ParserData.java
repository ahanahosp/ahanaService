package com.ahana.api.system.security.error;

import java.util.ArrayList;
import java.util.List;

public class ParserData {

    private List<ResponseError> systemErrorConstants = new ArrayList<ResponseError>();

    private List<ResponseError> applicationErrorConstants = new ArrayList<ResponseError>();

    private List<ResponseError> businessErrorConstants = new ArrayList<ResponseError>();

    private List<ResponseError> validationErrorConstants = new ArrayList<ResponseError>();

    private List<ResponseError> securityErrorConstants = new ArrayList<ResponseError>();

    public List<ResponseError> getSystemErrorConstants() {
        return systemErrorConstants;
    }

    public void setSystemErrorConstants(List<ResponseError> systemErrorConstants) {
        this.systemErrorConstants = systemErrorConstants;
    }

    public List<ResponseError> getApplicationErrorConstants() {
        return applicationErrorConstants;
    }

    public void setApplicationErrorConstants(List<ResponseError> applicationErrorConstants) {
        this.applicationErrorConstants = applicationErrorConstants;
    }

    public List<ResponseError> getBusinessErrorConstants() {
        businessErrorConstants.addAll(validationErrorConstants);
        businessErrorConstants.addAll(securityErrorConstants);
        return businessErrorConstants;
    }

    public void setBusinessErrorConstants(List<ResponseError> businessErrorConstants) {
        this.businessErrorConstants = businessErrorConstants;
    }

    public List<ResponseError> getValidationErrorConstants() {
        return validationErrorConstants;
    }

    public void setValidationErrorConstants(List<ResponseError> validationErrorConstants) {
        this.validationErrorConstants = validationErrorConstants;
    }

    public List<ResponseError> getSecurityErrorConstants() {
        return securityErrorConstants;
    }

    public void setSecurityErrorConstants(List<ResponseError> securityErrorConstants) {
        this.securityErrorConstants = securityErrorConstants;
    }

}