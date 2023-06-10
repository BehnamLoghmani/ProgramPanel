package com.programpanel.plugin.database.sqlite;

import com.programpanel.core.DatabaseConnectionException;
import com.programpanel.core.DatabasePlugin;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class SqlitePlugin implements DatabasePlugin {
    private Connection connection = null;

    @Override
    public void connect() throws DatabaseConnectionException {
        try {
            Class.forName("org.sqlite.JDBC");
            final var dbPath = "test.db";
            final var jarUrl = SqlitePlugin.class.getProtectionDomain().getCodeSource().getLocation();
            final var jarPath = new File(jarUrl.toURI()).getParent();
            final var absoluteDbPath = jarPath + File.separator + ".." + File.separator + dbPath;
            connection = DriverManager.getConnection("jdbc:sqlite:" + absoluteDbPath);
        } catch (Exception e) {
            throw new DatabaseConnectionException(e);
        }
    }

    @Override
    public void disconnect() throws DatabaseConnectionException {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new DatabaseConnectionException(e);
        }
    }

    @Override
    public void onLoad() {
        System.out.println("onLoad");
    }

    @Override
    public void onUnload() {
        System.out.println("onUnload");
    }

    @Override
    public void onEnable() {
        System.out.println("onEnable");
    }

    @Override
    public void onDisable() {
        System.out.println("onDisable");
    }
}
