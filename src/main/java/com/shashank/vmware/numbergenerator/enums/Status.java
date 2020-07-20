package com.shashank.vmware.numbergenerator.enums;

import java.io.Serializable;

/**
 * ENUM to hold value of the status - INPROGRESS, SUCCESS, ERROR
 */
public enum Status implements Serializable {
    INPROGRESS,
    SUCCESS,
    ERROR;

    public String getStatus() {
        return this.name();
    }
}
