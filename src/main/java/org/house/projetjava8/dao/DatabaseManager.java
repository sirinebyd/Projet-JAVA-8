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
                initializeDatabase(); // Création automatique des tables
            } catch (SQLException e) {
                System.out.println("Erreur lors de la connexion : " + e.getMessage());
            }
        }
        return connection;
    }

    /**
     * Crée toutes les tables nécessaires si elles n'existent pas.
     */
    private static void initializeDatabase() {
        String[] tableStatements = {
                "CREATE TABLE IF NOT EXISTS person (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "last_name TEXT NOT NULL," +
                        "first_name TEXT NOT NULL," +
                        "gender TEXT," +
                        "birth_date TEXT," +
                        "birth_city TEXT," +
                        "social_security_number TEXT" +
                        ");",

                "CREATE TABLE IF NOT EXISTS room (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "name TEXT NOT NULL," +
                        "gender_restriction TEXT," +
                        "min_age INTEGER," +
                        "max_age INTEGER" +
                        ");",

                "CREATE TABLE IF NOT EXISTS bed (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "label TEXT NOT NULL," +
                        "capacity INTEGER," +
                        "room_id INTEGER," +
                        "FOREIGN KEY(room_id) REFERENCES room(id)" +
                        ");",

                "CREATE TABLE IF NOT EXISTS occupation (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "person_id INTEGER," +
                        "bed_id INTEGER," +
                        "start_date DATE," +
                        "end_date DATE," +
                        "has_left BOOLEAN," +
                        "FOREIGN KEY(person_id) REFERENCES person(id)," +
                        "FOREIGN KEY(bed_id) REFERENCES bed(id)" +
                        ");"
        };

        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            for (String sql : tableStatements) {
                conn.createStatement().execute(sql);
            }
            System.out.println("Tables vérifiées/créées avec succès.");
        } catch (SQLException e) {
            System.err.println("Erreur lors de la création des tables : " + e.getMessage());
        }
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
        return new OccupationDAO();
    }
}
