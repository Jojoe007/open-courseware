package com.takdanai.courseware.exceptions;

public class StorageImageException extends RuntimeException {
    public StorageImageException(String message) {
        super(message);
    }

    public StorageImageException(String message, Throwable cause) {
        super(message, cause);
    }
}
