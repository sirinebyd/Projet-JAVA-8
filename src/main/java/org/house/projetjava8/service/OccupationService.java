package org.house.projetjava8.service;

import org.house.projetjava8.dao.LitDAO;
import org.house.projetjava8.model.Lit;
import org.house.projetjava8.model.Occupation;
import org.house.projetjava8.model.OccupancyRequest;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OccupationService {

    private final LitDAO litDAO = new LitDAO();
  
    public List<Occupation> genererOccupations(OccupancyRequest request) throws SQLException {
        List<Occupation> resultats = new ArrayList<>();

        List<Lit> litsDisponibles = litDAO.trouverLitsDisponibles(
                request.getGenre(), request.getDateDebut(), request.getDateFin()
        );

        int personnesAReloger = request.getNombrePersonnes();

        for (Lit lit : litsDisponibles) {
            int places = lit.getNbPlaces();

            for (int i = 0; i < places && personnesAReloger > 0; i++) {
                Occupation o = new Occupation();
                o.setLitId(lit.getId());
                o.setDateDebut(request.getDateDebut());
                o.setDateFin(request.getDateFin());
                o.setSortie(false);

                resultats.add(o);
                personnesAReloger--;
            }

            if (personnesAReloger == 0) break;
        }

        return resultats;
    }
}
