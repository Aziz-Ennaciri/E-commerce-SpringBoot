package com.ecommerce.aze_ecom.exceptions;

public class ResourceNotFoundException extends RuntimeException{
    String resourceName;
    String field;
    String fieldName;
    Long fieldId;

    public ResourceNotFoundException(String message, Throwable cause, String resourceName, String field, String fieldName) {
        super(message, cause);
        this.resourceName = resourceName;
        this.field = field;
        this.fieldName = fieldName;
    }

    public ResourceNotFoundException(String message, Throwable cause, String resourceName, String field, Long fieldId) {
        super(message, cause);
        this.resourceName = resourceName;
        this.field = field;
        this.fieldId = fieldId;
    }
    public ResourceNotFoundException(){}
}
