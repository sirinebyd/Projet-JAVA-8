package org.house.projetjava8.service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.house.projetjava8.dao.OccupationDAO;
import org.house.projetjava8.model.OccupancyRequest;
import org.house.projetjava8.model.Occupation;
import org.house.projetjava8.model.Person;
import org.house.projetjava8.model.Room;

public class OccupationService {
    private final OccupationDAO occupationDAO = new OccupationDAO();

    /**
     * Récupère toutes les occupations.
     */
    public List<Occupation> getAll() {
        try {
            return occupationDAO.getAll();
        } catch (SQLException e) {
            throw new RuntimeException("Échec de la récupération des occupations: " + e.getMessage(), e);
        }
    }

    /**
     * Récupère une occupation par ID.
     */
    public Occupation getById(int id) {
        try {
            return occupationDAO.getById(id);
        } catch (SQLException e) {
            throw new RuntimeException("Échec de la récupération de l'occupation: " + e.getMessage(), e);
        }
    }

    /**
     * Ajoute une nouvelle occupation.
     */
    public boolean addOccupation(Occupation occupation) {
        try {
            return occupationDAO.add(occupation);
        } catch (SQLException e) {
            throw new RuntimeException("Échec de l'ajout de l'occupation: " + e.getMessage(), e);
        }
    }

    /**
     * Met à jour le flag hasLeft d'une occupation.
     */
    public boolean updateHasLeft(Occupation occupation) {
        try {
            return occupationDAO.updateHasLeft(occupation);
        } catch (SQLException e) {
            throw new RuntimeException("Échec de la mise à jour de l'occupation: " + e.getMessage(), e);
        }
    }

    /**
     * Supprime une occupation par ID.
     */
    public boolean deleteOccupation(int id) {
        try {
            return occupationDAO.delete(id);
        } catch (SQLException e) {
            throw new RuntimeException("Échec de la suppression de l'occupation: " + e.getMessage(), e);
        }
    }

    /**
     * Vérifie si une personne est compatible avec une salle.
     */
    public boolean isPersonCompatibleWithRoom(Person person, Room room) {
        int age = person.getAge();
        boolean ageOk = age >= room.getMinAge() && age <= room.getMaxAge();
        boolean genderOk = "ALL".equalsIgnoreCase(room.getGenderRestriction())
                || room.getGenderRestriction().equalsIgnoreCase(person.getGender());
        return ageOk && genderOk;
    }

    /**
     * Vérifie la disponibilité d'un lit sur une plage de dates.
     */
    public boolean isBedAvailable(int bedId, LocalDate start, LocalDate end) {
        try {
            List<Occupation> occupations = occupationDAO.getOccupationsForBed(bedId);
            for (Occupation occ : occupations) {
                if (!(end.isBefore(occ.getStartDate()) || start.isAfter(occ.getEndDate()))) {
                    return false;
                }
            }
            return true;
        } catch (SQLException e) {
            throw new RuntimeException("Échec de la vérification de disponibilité: " + e.getMessage(), e);
        }
    }

    /**
     * Vérifie s'il existe une coupure dans les occupations d'un lit.
     */
    public boolean hasCoupure(int bedId, LocalDate rangeStart, LocalDate rangeEnd) {
        try {
            List<Occupation> occupations = occupationDAO.getOccupationsForBed(bedId);
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
        } catch (SQLException e) {
            throw new RuntimeException("Échec de la vérification de coupure: " + e.getMessage(), e);
        }
    }

    /**
     * Génère une liste d'occupations basée sur une demande.
     */
    public List<Occupation> genererOccupations(OccupancyRequest request) {
        List<Occupation> occupations = new ArrayList<>();
        for (int i = 0; i < request.getNumberOfPeople(); i++) {
            Occupation occupation = new Occupation();
            occupation.setStartDate(request.getStartDate());
            occupation.setEndDate(request.getEndDate());
            occupation.setHasLeft(false);
            occupations.add(occupation);
        }
        return occupations;
    }

   public void save(Occupation occupation) {
        try {
            occupationDAO.add(occupation);
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de l'ajout de l'affectation : " + e.getMessage(), e);
        }
    }

    public void update(Occupation occupation) {
        try {
            occupationDAO.updateHasLeft(occupation);
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la mise à jour de l'affectation : " + e.getMessage(), e);
        }
    }

    public void delete(Occupation occupation) {
        try {
            occupationDAO.delete(occupation.getId());
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la suppression de l'affectation : " + e.getMessage(), e);
        }
    }
   
}