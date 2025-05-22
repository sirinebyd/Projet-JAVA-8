package org.house.projetjava8.service;

import org.house.projetjava8.dao.OccupationDao;
import org.house.projetjava8.model.Occupation;

import java.util.List;
import java.sql.SQLException;

public class OccupationService {
    private final OccupationDao dao = new OccupationDao();

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
    int age = person.calculateAge(); // Crée cette méthode si elle n'existe pas
    boolean ageOk = age >= room.getMinAge() && age <= room.getMaxAge();
    boolean genderOk = room.getGenderRestriction().equals("ALL") ||
                       room.getGenderRestriction().equalsIgnoreCase(person.getGender());
    return ageOk && genderOk;
}
    public boolean isBedAvailable(int bedId, LocalDate start, LocalDate end) {
    List<Occupation> occupations = occupationDao.getOccupationsForBed(bedId);

    for (Occupation o : occupations) {
        if (!(end.isBefore(o.getStartDate()) || start.isAfter(o.getEndDate()))) {
            return false;
        }
    }
    return true;
}
}
