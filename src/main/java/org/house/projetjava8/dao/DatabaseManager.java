package org.house.projetjava8.dao; 

import java.sql.Connection;        
import java.sql.DriverManager;     
import java.sql.SQLException;       

public class DatabaseManager {     

    private static final String DB_URL = "jdbc:sqlite:src/main/resources/db/database.db"; 

    private static Connection connection = null; 

    public static Connection getConnection() {  
        if (connection == null) {           
            try {
                connection = DriverManager.getConnection(DB_URL);
                System.out.println("Connexion à la base réussie.");
            } catch (SQLException e) {         
                System.err.println("Erreur de connexion : " + e.getMessage());
            }
        }
        return connection;                       
    }

    public static void closeConnection() {      
        if (connection != null) {
            try {
                connection.close();              
                System.out.println("Connexion fermée.");
            } catch (SQLException e) {
                System.err.println(" Erreur lors de la fermeture : " + e.getMessage());
            }
        }
    }
}

