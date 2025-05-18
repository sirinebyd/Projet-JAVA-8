package org.house.projetjava8.database;

import org.house.projetjava8.dao.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {

    private static final String DB_URL = //nom du fichier
    private static Connection connection = null;

    private static PersonDAO personDAO;
    private static RoomDAO roomDAO;
    private static BedDAO bedDAO;
    private static OccupationDAO occupationDAO;

    public static void connect() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(DB_URL);
            System.out.println(" Connexion établie avec SQLite.");
            personDAO = new PersonDAO(connection);
            roomDAO = new RoomDAO(connection);
            bedDAO = new BedDAO(connection);
            occupationDAO = new OccupationDAO(connection);
        }
    }

    public static void disconnect() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Connexion fermée.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static PersonDAO getPersonDAO() {
        return personDAO;
    }

    public static RoomDAO getRoomDAO() {
        return roomDAO;
    }

    public static BedDAO getBedDAO() {
        return bedDAO;
    }

    public static OccupationDAO getOccupationDAO() {
        return occupationDAO;
    }

    public static Connection getConnection() {
        return connection;
    }
}
