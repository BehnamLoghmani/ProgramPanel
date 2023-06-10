package com.programpanel.core;

public interface DatabasePlugin extends Plugin {
    void connect() throws DatabaseConnectionException;

    void disconnect() throws DatabaseConnectionException;
}
