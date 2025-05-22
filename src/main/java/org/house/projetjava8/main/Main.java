import org.house.projetjava8.dao.DatabaseManager;
import org.house.projetjava8.model.*;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        try {
            DatabaseManager.connect();

            
            Person p1 = new Person(1, "Amine", 22, "Homme");
            Person p2 = new Person(2, "Leïla", 25, "Femme");
            DatabaseManager.getPersonDAO().add(p1);
            DatabaseManager.getPersonDAO().add(p2);

            
            Room r1 = new Room(1, "Salle Alpha", 4, false);
            Room r2 = new Room(2, "Salle Bêta", 6, true); 
            DatabaseManager.getRoomDAO().add(r1);
            DatabaseManager.getRoomDAO().add(r2);

           
            Bed b1 = new Bed(1, 1, true);
            Bed b2 = new Bed(2, 1, true);
            Bed b3 = new Bed(3, 2, true);
            DatabaseManager.getBedDAO().add(b1);
            DatabaseManager.getBedDAO().add(b2);
            DatabaseManager.getBedDAO().add(b3);

            
            Occupation o1 = new Occupation(1, 1, 1, LocalDate.of(2025, 6, 1), LocalDate.of(2025, 6, 15));
            Occupation o2 = new Occupation(2, 2, 3, LocalDate.of(2025, 6, 10), LocalDate.of(2025, 6, 20));
            DatabaseManager.getOccupationDAO().add(o1);
            DatabaseManager.getOccupationDAO().add(o2);

            System.out.println("Données insérées avec succès !");
            DatabaseManager.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
