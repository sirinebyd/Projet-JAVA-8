package org.house.projetjava8.service;

import org.house.projetjava8.dao.OccupationDAO;
import org.house.projetjava8.model.Occupation;
import org.house.projetjava8.model.Person;
import org.house.projetjava8.model.Room;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.sql.SQLException;

import static org.house.projetjava8.dao.DatabaseManager.connection;

public class OccupationService {
    private final OccupationDAO dao = new OccupationDAO();

    public List<Occupation> getAll() {
        try {
            return dao.getAll();
        } catch (Exception e) {
            throw new RuntimeException("Failed to getAll: " + e.getMessage(), e);
        }
    }

    public Occupation getById(int id) {
        try {
            return dao.getById(id);
        } catch (Exception e) {
            throw new RuntimeException("Failed to getById: " + e.getMessage(), e);
        }
    }

    public void save(Occupation occupation) {
        try {
            dao.add(occupation);
        } catch (Exception e) {
            throw new RuntimeException("Failed to save: " + e.getMessage(), e);
        }
    }

    public void update(Occupation occupation) {
        try {
            dao.updateHasLeft(occupation);
        } catch (Exception e) {
            throw new RuntimeException("Failed to update: " + e.getMessage(), e);
        }
    }

    public void delete(int id) {
        try {
            dao.delete(id);
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete: " + e.getMessage(), e);
        }
    }

    public boolean isPersonCompatibleWithRoom(Person person, Room room) {
        int age = person.getAge();
        boolean ageOk = age >= room.getMinAge() && age <= room.getMaxAge();
        boolean genderOk = room.getGenderRestriction().equals("ALL") ||
                room.getGenderRestriction().equalsIgnoreCase(person.getGender());
        return ageOk && genderOk;
    }

    public boolean isBedAvailable(int bedId, LocalDate start, LocalDate end) {
        List<Occupation> occupations = OccupationDAO.getOccupationsForBed(bedId);

        for (Occupation o : occupations) {
            if (!(end.isBefore(o.getStartDate()) || start.isAfter(o.getEndDate()))) {
                return false;
            }
        }
        return true;
    }

    public boolean hasCoupure(int bedId, LocalDate rangeStart, LocalDate rangeEnd) {
        List<Occupation> occupations = OccupationDAO.getOccupationsForBed(bedId);
        occupations.sort(Comparator.comparing(Occupation::getStartDate));

        LocalDate expected = rangeStart;

        for (Occupation occ : occupations) {
            if (occ.getEndDate().isBefore(rangeStart) || occ.getStartDate().isAfter(rangeEnd)) {
                continue;
            }

            if (occ.getStartDate().isAfter(expected)) {

                return true;
            }


            if (occ.getEndDate().isAfter(expected)) {
                expected = occ.getEndDate().plusDays(1);
            }
        }

        return false;
    }

    public List<Occupation> getOccupationsForBed(int bedId) throws SQLException {
        List<Occupation> occupations = new ArrayList<>();
        String sql = "SELECT * FROM occupation WHERE bed_id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, bedId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                int personId = rs.getInt("person_id");
                LocalDate startDate = rs.getDate("start_date").toLocalDate();
                LocalDate endDate = rs.getDate("end_date") != null ? rs.getDate("end_date").toLocalDate() : null;
                boolean hasLeft = rs.getBoolean("has_left");

                Occupation occupation = new Occupation(id, personId, bedId, startDate, endDate, hasLeft);
                occupations.add(occupation);
            }
        }

        return occupations;
    }

}
