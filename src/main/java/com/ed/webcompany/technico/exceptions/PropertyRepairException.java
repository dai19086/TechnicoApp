package com.ed.webcompany.technico.exceptions;

public class PropertyRepairException extends RuntimeException {

    public PropertyRepairException(String message) {
        super(message);
    }

    public PropertyRepairException(String message, Throwable cause) {
        super(message, cause);
    }
}
