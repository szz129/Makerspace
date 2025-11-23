package com.makerspace.makerspaceapp.exception;

public class ResourceNotFoundException extends RuntimeException {
    
    // Constructor with single message
    public ResourceNotFoundException(String message) {
        super(message);
    }
    
    // Constructor with resource name, field name, and field value
    public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("%s not found with %s: '%s'", resourceName, fieldName, fieldValue));
    }
    
    // Constructor with message and cause
    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}