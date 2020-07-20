package com.shashank.vmware.numbergenerator.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * Custom response to be returned as response of the REST API call
 */
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Response {
    private String task;
    private String result;

    public Response(String task, String result) {
        this.task = task;
        this.result = result;
    }
}
