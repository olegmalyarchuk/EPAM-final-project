package com.example.conference.exceptions;

public class DataBaseConnectionException extends Exception {
    public DataBaseConnectionException() {
        super("Unable to connect to database");
    }
    public DataBaseConnectionException(String message) {
        super(message);
    }
}
