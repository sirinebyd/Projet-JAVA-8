package org.house.projetjava8.service;

import org.house.projetjava8.model.*;

import java.time.LocalDate;
import java.util.*;

public class BedAssignmentEngine {

    private RoomService roomService;
    private BedService bedService;
    private OccupationService occupationService;

    public BedAssignmentEngine(RoomService roomService, BedService bedService, OccupationService occupationService) {
        this.roomService = roomService;
        this.bedService = bedService;
        this.occupationService = occupationService;
    }

    public Map<Person, Bed> proposerAffectation(List<Person> personnes, LocalDate debut, LocalDate fin, boolean memeSalle) {
        Map<Person, Bed> affectations = new HashMap<>();
        List<Room> salles = roomService.getAllRooms();

        for (Room salle : salles) {
            if (salleRespecte(personnes, salle)) {
                List<Bed> lits = bedService.getBedsByRoom(salle.getId());

                // Filtrage : uniquement les lits disponibles dans la plage demandée
                List<Bed> litsDisponibles = new ArrayList<>();
                for (Bed lit : lits) {
                    if (occupationService.isBedAvailable(lit.getId(), debut, fin)) {
                        litsDisponibles.add(lit);
                    }
                }

                if (memeSalle && litsDisponibles.size() >= personnes.size()) {
                    // Affecter directement dans cette salle
                    for (int i = 0; i < personnes.size(); i++) {
                        affectations.put(personnes.get(i), litsDisponibles.get(i));
                    }
                    return affectations;
                } else if (!memeSalle) {
                    // Affectation répartie dans plusieurs salles
                    for (Person p : personnes) {
                        boolean affecte = false;
                        for (Room r : salles) {
                            if (salleRespecte(Collections.singletonList(p), r)) {
                                for (Bed lit : bedService.getBedsByRoom(r.getId())) {
                                    if (occupationService.isBedAvailable(lit.getId(), debut, fin)) {
                                        affectations.put(p, lit);
                                        affecte = true;
                                        break;
                                    }
                                }
                            }
                            if (affecte) break;
                        }
                    }
                    return affectations;
                }
            }
        }

        return affectations; // Vide si rien trouvé
    }

    private boolean salleRespecte(List<Person> personnes, Room salle) {
        for (Person p : personnes) {
            int age = p.calculateAge(); // À implémenter si manquant
            boolean ageOk = age >= salle.getMinAge() && age <= salle.getMaxAge();
            boolean genderOk = salle.getGenderRestriction().equals("ALL") ||
                    salle.getGenderRestriction().equalsIgnoreCase(p.getGender());
            if (!(ageOk && genderOk)) return false;
        }
        return true;
    }
}
