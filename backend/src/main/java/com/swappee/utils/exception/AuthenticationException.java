package com.swappee.utils.exception;

/**
 * thrown whenever there is an exception for authentication
 */
public class AuthenticationException extends RuntimeException {
    private static final long serialVersionUID = -5616976732261857005L;

    public AuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }
}
