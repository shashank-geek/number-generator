package com.shashank.vmware.numbergenerator.exception;

/**
 * NumberGeneratorException class extending RuntimeException to be thrown
 * instead of any Runtime exception as Internal Server Exception
 */
public class NumberGeneratorException extends RuntimeException {
    private Error error;

    public NumberGeneratorException(String message, ErrorCodes errorCodes) {
        super(message);
        this.error = new Error(message, errorCodes.getCode(), errorCodes.getMessage());
    }

    public Error getError() {
        return error;
    }
}
