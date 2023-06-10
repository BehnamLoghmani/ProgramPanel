package com.programpanel.core;

public class DatabaseConnectionException extends RuntimeException {
    public DatabaseConnectionException(Throwable throwable) {
        super(throwable);
    }
}
