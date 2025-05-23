package org.house.projetjava8.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
    private static final String DB_URL = "jdbc:sqlite:src/main/resources/db/database.db";
   
    public static Connection connection = null;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(DB_URL);
                System.out.println("Connexion réussie à la base de données");
            } catch (SQLException e) {
                System.out.println("Erreur lors de la connexion : " + e.getMessage());
            }
        }
        return connection;
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
                System.out.println("Connexion fermée.");
            } catch (SQLException e) {
                System.out.println("Erreur lors de la fermeture de la connexion : " + e.getMessage());
            }
        }
    }

    private static final PersonDAO personDAO = new PersonDAO();
    private static final RoomDAO roomDAO = new RoomDAO();
    private static final BedDAO bedDAO = new BedDAO();

    public static PersonDAO getPersonDAO() {
        return personDAO;
    }

    public static RoomDAO getRoomDAO() {
        return roomDAO;
    }

    public static BedDAO getBedDAO() {
        return bedDAO;
    }

    private static DatabaseManager instance;

    public static DatabaseManager getInstance() {
        if (instance == null) {
            instance = new DatabaseManager();
        }
        return instance;
    }


    public OccupationDAO getOccupationDAO() {
        return new OccupationDAO(connection);
    }
}
