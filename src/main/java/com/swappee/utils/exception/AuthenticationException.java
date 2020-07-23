package com.swappee.utils.exception;

/**
 * thrown whenever there is an exception for authentication
 */
public class AuthenticationException extends RuntimeException {
    public AuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }
}
