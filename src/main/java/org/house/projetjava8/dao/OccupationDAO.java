package org.house.projetjava8.dao;

import org.house.projetjava8.model.Occupation;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OccupationDAO {
    private static final Connection connection;

    static {
        try {
            // Charger le driver SQLite
            Class.forName("org.sqlite.JDBC");

            // Récupérer la ressource de la base
            URL dbUrl = OccupationDAO.class.getResource("/db/database.db");

            if (dbUrl == null) {
                throw new IllegalStateException("Ressource db/database.db introuvable dans le classpath");
            }

            // Convertir en chemin absolu
            String dbPath = Paths.get(dbUrl.toURI()).toAbsolutePath().toString();

            // Ouvrir la connexion JDBC
            connection = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
            System.out.println("Connexion réussie à la base de données Occupation");

        } catch (ClassNotFoundException e) {
            throw new ExceptionInInitializerError("Driver SQLite non trouvé : " + e.getMessage());
        } catch (URISyntaxException e) {
            throw new ExceptionInInitializerError("URI invalide pour la ressource : " + e.getMessage());
        } catch (SQLException e) {
            throw new ExceptionInInitializerError("Erreur de connexion SQLite : " + e.getMessage());
        }
    }

    /**
     * Récupère toutes les occupations pour un lit donné.
     */
    public List<Occupation> getOccupationsForBed(int bedId) throws SQLException {
        List<Occupation> occupations = new ArrayList<>();
        String sql = "SELECT * FROM occupation WHERE bed_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, bedId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    occupations.add(new Occupation(
                        rs.getInt("id"),
                        rs.getInt("person_id"),
                        rs.getInt("bed_id"),
                        rs.getDate("start_date") != null ? rs.getDate("start_date").toLocalDate() : null,
                        rs.getDate("end_date")   != null ? rs.getDate("end_date").toLocalDate()   : null,
                        rs.getBoolean("has_left")
                    ));
                }
            }
        }
        return occupations;
    }

    /**
     * Ajoute une occupation. Permet startDate non null, endDate optionnelle.
     */
    public boolean add(Occupation occupation) throws SQLException {
        LocalDate start = occupation.getStartDate();
        LocalDate end = occupation.getEndDate();
        if (start == null) {
            throw new IllegalArgumentException("La date de début doit être renseignée");
        }

        String sql = "INSERT INTO occupation (person_id, bed_id, start_date, end_date, has_left) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, occupation.getPersonId());
            stmt.setInt(2, occupation.getBedId());
            stmt.setDate(3, Date.valueOf(start));
            // endDate peut être null -> SQL NULL
            if (end != null) {
                stmt.setDate(4, Date.valueOf(end));
            } else {
                stmt.setNull(4, Types.DATE);
            }
            stmt.setBoolean(5, occupation.isHasLeft());

            int affected = stmt.executeUpdate();
            if (affected == 1) {
                try (ResultSet keys = stmt.getGeneratedKeys()) {
                    if (keys.next()) {
                        occupation.setId(keys.getInt(1));
                    }
                }
                return true;
            }
            return false;
        }
    }

    /**
     * Récupère toutes les occupations.
     */
    public List<Occupation> getAll() throws SQLException {
        List<Occupation> occupations = new ArrayList<>();
        String sql = "SELECT * FROM occupation";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                occupations.add(new Occupation(
                    rs.getInt("id"),
                    rs.getInt("person_id"),
                    rs.getInt("bed_id"),
                    rs.getDate("start_date") != null ? rs.getDate("start_date").toLocalDate() : null,
                    rs.getDate("end_date")   != null ? rs.getDate("end_date").toLocalDate()   : null,
                    rs.getBoolean("has_left")
                ));
            }
        }
        return occupations;
    }

    /**
     * Récupère une occupation par ID.
     */
    public Occupation getById(int id) throws SQLException {
        String sql = "SELECT * FROM occupation WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Occupation(
                        rs.getInt("id"),
                        rs.getInt("person_id"),
                        rs.getInt("bed_id"),
                        rs.getDate("start_date") != null ? rs.getDate("start_date").toLocalDate() : null,
                        rs.getDate("end_date")   != null ? rs.getDate("end_date").toLocalDate()   : null,
                        rs.getBoolean("has_left")
                    );
                }
            }
        }
        return null;
    }

    /**
     * Supprime une occupation par ID.
     */
    public boolean delete(int id) throws SQLException {
        String sql = "DELETE FROM occupation WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        }
    }

    /**
     * Met à jour le flag has_left pour une occupation.
     */
    public boolean updateHasLeft(int id, boolean hasLeft) throws SQLException {
        String sql = "UPDATE occupation SET has_left = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setBoolean(1, hasLeft);
            stmt.setInt(2, id);
            return stmt.executeUpdate() == 1;
        }
    }

    /**
     * Met à jour le flag has_left via l'objet Occupation.
     */
    public boolean updateHasLeft(Occupation occupation) throws SQLException {
        return updateHasLeft(occupation.getId(), occupation.isHasLeft());
    }
}