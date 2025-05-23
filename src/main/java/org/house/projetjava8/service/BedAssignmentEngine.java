package org.house.projetjava8.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.house.projetjava8.model.Bed;
import org.house.projetjava8.model.Person;
import org.house.projetjava8.model.Room;

public class BedAssignmentEngine {

    private RoomService roomService;
    private OccupationService occupationService;

    public BedAssignmentEngine(RoomService roomService, OccupationService occupationService) {
        this.roomService = roomService;
        this.occupationService = occupationService;
    }

    public Map<Person, Bed> proposerAffectation(List<Person> personnes, LocalDate debut, LocalDate fin,
            boolean memeSalle) {
        Map<Person, Bed> affectations = new HashMap<>();
        List<Room> salles = roomService.getAll();

        for (Room salle : salles) {
            if (salleRespecte(personnes, salle)) {
                List<Bed> lits = BedService.getByRoom(salle.getId());

                List<Bed> litsDisponibles = new ArrayList<>();
                for (Bed lit : lits) {
                    if (occupationService.isBedAvailable(lit.getId(), debut, fin)) {
                        litsDisponibles.add(lit);
                    }
                }

                if (memeSalle && litsDisponibles.size() >= personnes.size()) {
                    for (int i = 0; i < personnes.size(); i++) {
                        affectations.put(personnes.get(i), litsDisponibles.get(i));
                    }
                    return affectations;
                } else if (!memeSalle) {
                    for (Person p : personnes) {
                        boolean affecte = false;
                        for (Room r : salles) {
                            if (salleRespecte(Collections.singletonList(p), r)) {
                                for (Bed lit : BedService.getByRoom(r.getId())) {
                                    if (occupationService.isBedAvailable(lit.getId(), debut, fin)) {
                                        affectations.put(p, lit);
                                        affecte = true;
                                        break;
                                    }
                                }
                            }
                            if (affecte)
                                break;
                        }
                    }
                    return affectations;
                }
            }
        }

        return affectations;
    }

    private boolean salleRespecte(List<Person> personnes, Room salle) {
        for (Person p : personnes) {
            int age = p.getAge();
            boolean ageOk = age >= salle.getMinAge() && age <= salle.getMaxAge();
            boolean genderOk = salle.getGenderRestriction().equals("ALL") ||
                    salle.getGenderRestriction().equalsIgnoreCase(p.getGender());
            if (!(ageOk && genderOk))
                return false;
        }
        return true;
    }
}
