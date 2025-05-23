package org.house.projetjava8.main;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import org.house.projetjava8.model.Bed;
import org.house.projetjava8.model.Occupation;
import org.house.projetjava8.model.Person;
import org.house.projetjava8.service.BedService;
import org.house.projetjava8.service.OccupationService;
import org.house.projetjava8.service.PersonService;

public class ConsoleMain {
    private static PersonService personService = new PersonService();
    private static BedService bedService = new BedService();
    private static OccupationService occupationService = new OccupationService();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choix;

        do {
            System.out.println("\n=== MENU PRINCIPAL ===");
            System.out.println("1. Lister les personnes");
            System.out.println("2. Ajouter une personne");
            System.out.println("3. Lister les lits disponibles");
            System.out.println("4. Affecter une personne à un lit");
            System.out.println("5. Voir les occupations");
            System.out.println("0. Quitter");
            System.out.print("Votre choix : ");
            choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1 -> listerPersonnes();
                case 2 -> ajouterPersonne(scanner);
                case 3 -> listerLitsDisponibles();
                case 4 -> affecterPersonne(scanner);
                case 5 -> voirOccupations();
            }
        } while (choix != 0);

        scanner.close();
    }

    private static void listerPersonnes() {
        List<Person> personnes = PersonService.getAll();
        if (personnes.isEmpty()) {
            System.out.println("Aucune personne trouvée.");
        } else {
            personnes.forEach(p -> System.out.println(p.getId() + " - " + p.getName()));
        }
    }

    private static void ajouterPersonne(Scanner scanner) {
        System.out.print("Nom : ");
        String nom = scanner.nextLine();
        System.out.print("Prénom : ");
        String prenom = scanner.nextLine();
        System.out.print("Genre (M/F) : ");
        String genre = scanner.nextLine();
        System.out.print("Date de naissance (YYYY-MM-DD) : ");
        String date = scanner.nextLine();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate birthDate = LocalDate.parse(date, formatter);

        String formattedDate = birthDate.format(formatter);
        Person person = new Person(nom, prenom, genre, formattedDate);

        personService.addPerson(person);
        System.out.println("Personne ajoutée.");
    }

    private static void listerLitsDisponibles() {
        List<Bed> lits = bedService.getAll();
        System.out.println("Liste des lits disponibles (tous, filtrage à améliorer) :");
        for (Bed bed : lits) {
            System.out.println("Lit ID: " + bed.getId() + " - Salle ID: " + bed.getRoomId());
        }
    }

    private static void affecterPersonne(Scanner scanner) {
        System.out.print("ID personne : ");
        int personId = scanner.nextInt();
        System.out.print("ID lit : ");
        int bedId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Date début (YYYY-MM-DD) : ");
        String debut = scanner.nextLine();
        System.out.print("Date fin (YYYY-MM-DD) : ");
        String fin = scanner.nextLine();

        Occupation o = new Occupation();
        o.setPersonId(personId);
        o.setBedId(bedId);
        o.setStartDate(LocalDate.parse(debut));
        o.setEndDate(LocalDate.parse(fin));
        o.setExited(false);

        occupationService.addOccupation(o);
        System.out.println("Occupation enregistrée.");
    }

    private static void voirOccupations() {
        List<Occupation> occupations = occupationService.getAll();
        for (Occupation o : occupations) {
            System.out.println("Personne ID " + o.getPersonId() + " - Lit ID " + o.getBedId()
                    + " du " + o.getStartDate() + " au " + o.getEndDate()
                    + " - Sortie : " + (o.isExited() ? "Oui" : "Non"));
        }
    }
}
