package org.house.projetjava8.main;

import org.house.projetjava8.dao.DatabaseManager;
import org.house.projetjava8.dao.RoomDAO;
import org.house.projetjava8.dao.BedDAO;
import org.house.projetjava8.dao.PersonDAO;
import org.house.projetjava8.dao.OccupationDAO;
import org.house.projetjava8.model.*;

import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            // Connexion à la base via DataManager
            DatabaseManager dbManager = DatabaseManager.getInstance();

            // DAOs
            RoomDAO roomDAO = dbManager.getRoomDAO();
            BedDAO bedDAO = dbManager.getBedDAO();
            PersonDAO personDAO = dbManager.getPersonDAO();
            OccupationDAO occupationDAO = dbManager.getOccupationDAO();

            // Ajout d'une chambre
            Room room = new Room(0, "Chambre 101", "F", 18, 60);
            roomDAO.add(room);
            System.out.println("✅ Chambre ajoutée.");

            // Ajout d’un lit dans la chambre
            Bed bed = new Bed(0, "Lit A", 1, room.getId());
            bedDAO.add(bed);
            System.out.println("✅ Lit ajouté.");

            // Ajout d'une personne
            Person person = new Person("Dupont", "Claire", "F", LocalDate.of(2000, 6, 12));
            personDAO.add(person);
            System.out.println("✅ Personne ajoutée.");

            // Ajout d'une occupation
            Occupation occupation = new Occupation(0, person.getId(), bed.getId(),
                    LocalDate.now(), null, false);
            occupationDAO.add(occupation);
            System.out.println("✅ Occupation ajoutée.");

            // Affichage des occupations par lit
            List<Occupation> occs = occupationDAO.getOccupationsForBed(bed.getId());
            occs.forEach(o -> System.out.println("📌 Occupation ID : " + o.getId()));

        } catch (Exception e) {
            System.err.println("❌ Erreur : " + e.getMessage());
            e.printStackTrace();
        }
    }
}

