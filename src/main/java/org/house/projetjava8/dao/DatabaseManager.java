package org.house.projetjava8.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
    private static Connection connection;

    public static void connect(String dbPath) throws SQLException {
        connection = DriverManager.getConnection("nom"+ chemin);
    }

    public static void disconnect() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    public static Connection getConnection() {
        return connection;
    }
}
