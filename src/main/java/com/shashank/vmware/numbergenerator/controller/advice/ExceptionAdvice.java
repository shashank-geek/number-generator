package com.shashank.vmware.numbergenerator.controller.advice;

import com.shashank.vmware.numbergenerator.exception.NumberGeneratorException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Global Exception Handling class to handle HTTP status code and the response body
 */
@ControllerAdvice
public class ExceptionAdvice {
    /**
     * Exception to be customised with HTTP status code and the response body
     * for the NumberGeneratorException
     *
     * @param e NumberGeneratorException
     * @return ResponseEntity
     */
    @ExceptionHandler(NumberGeneratorException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity handleGenericNumberGeneratorException(NumberGeneratorException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getError());
    }
}
