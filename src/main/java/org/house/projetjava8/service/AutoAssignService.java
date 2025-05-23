package org.house.projetjava8.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.house.projetjava8.dao.BedDAO;
import org.house.projetjava8.dao.OccupationDAO;
import org.house.projetjava8.dao.PersonDAO;
import org.house.projetjava8.model.AutoAssignment;
import org.house.projetjava8.model.Bed;
import org.house.projetjava8.model.Occupation;
import org.house.projetjava8.model.Person;

public class AutoAssignService {

    public List<AutoAssignment> generateAssignments(int nb, LocalDate start, LocalDate end, boolean sameRoom) {
        List<AutoAssignment> result = new ArrayList<>();

        for (int i = 1; i <= nb; i++) {
            result.add(new AutoAssignment("Personne " + i, "Lit " + i, sameRoom ? "Salle A" : "Salle " + i));
        }

        return result;
    }

    public void saveAssignments(List<AutoAssignment> assignments, LocalDate start, LocalDate end) {
        PersonDAO personDAO = new PersonDAO();
        BedDAO bedDAO = new BedDAO();
        OccupationDAO occupationDAO = new OccupationDAO();

        for (AutoAssignment a : assignments) {
            Person person = personDAO.getByName(a.getPersonName());
            Bed bed = bedDAO.getByLabel(a.getBedLabel());

            if (person != null && bed != null) {
                Occupation occ = new Occupation();
                occ.setPersonId(person.getId());
                occ.setBedId(bed.getId());
                occ.setStartDate(start);
                occ.setEndDate(end);
                occ.setExited(false);
                try {
                    occupationDAO.add(occ);
                } catch (Exception e) {
                    System.err.println("Erreur enregistrement occupation : " + e.getMessage());
                }
            }
        }
    }

}
