package com.shashank.vmware.numbergenerator.exception;

/**
 * Error class to customised the response of the exception
 */
public class Error {
    private String code;
    private String detailMessage;
    private String message;

    public Error() {

    }

    public Error(String message, String code, String detailMessage) {
        this.message = message;
        this.code = code;
        this.detailMessage = detailMessage;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDetailMessage() {
        return detailMessage;
    }

    public void setDetailMessage(String detailMessage) {
        this.detailMessage = detailMessage;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
