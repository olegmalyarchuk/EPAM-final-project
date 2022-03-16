package com.example.conference.exceptions;

/**
 * *
 * An exception that provides information on an application error.
 * *
 */
public class AppException extends Exception {

    private static final long serialVersionUID = 828877906288218916L;

    public AppException() {
        super();
    }

    public AppException(String message, Throwable cause) {
        super(message, cause);
    }

    public AppException(String message) {
        super(message);
    }

}