package com.shashank.vmware.numbergenerator.exception;

/**
 * ENUm to hold the error codes
 */
public enum ErrorCodes {
    INVALID_ACTION("101", "Action is invalid"),
    UUID_NOT_FOUND("102", "UUID not found"),
    TRANSACTION_NOT_FOUND("103", "Transaction not found");

    private final String code;
    private final String message;

    ErrorCodes(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
