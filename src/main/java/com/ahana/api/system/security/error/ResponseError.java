package com.ahana.api.system.security.error;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("ResponseError")
public class ResponseError implements Serializable{

    private static final long serialVersionUID = 1L;

    private String errorType;

    private String errorCode;

    private String shortMessage;

    private String longMessage;

    public String getErrorType() {
        return errorType;
    }

    public void setErrorType(String errorType) {
        this.errorType = errorType;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getShortMessage() {
        return shortMessage;
    }

    public void setShortMessage(String shortMessage) {
        this.shortMessage = shortMessage;
    }

    public String getLongMessage() {
        return longMessage;
    }

    public void setLongMessage(String longMessage) {
        this.longMessage = longMessage;
    }

    public ResponseError clone(){
        ResponseError error = new ResponseError();
        error.setErrorCode(this.errorCode);
        error.setErrorType(this.errorType);
        error.setLongMessage(this.longMessage);
        error.setShortMessage(this.shortMessage);
        return error;
    }
}