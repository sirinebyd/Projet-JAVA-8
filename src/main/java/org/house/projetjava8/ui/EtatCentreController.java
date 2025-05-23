package org.house.projetjava8.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import org.house.projetjava8.model.Bed;
import org.house.projetjava8.model.Occupation;
import org.house.projetjava8.service.BedService;
import org.house.projetjava8.service.OccupationService;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class EtatCentreController {

    @FXML
    private GridPane bedGrid;

    private final BedService bedService = new BedService();
    private final OccupationService occupationService = new OccupationService();

    @FXML
    public void initialize() {
        try {
            List<Bed> beds = bedService.getAll();
            int col = 0, row = 0;

            for (Bed bed : beds) {
                ImageView bedIcon = new ImageView(getBedImage(bed));
                bedIcon.setFitWidth(40);
                bedIcon.setFitHeight(40);
                bedGrid.add(bedIcon, col, row);
                col++;
                if (col > 5) {
                    col = 0;
                    row++;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Impossible de charger l'état des lits : " + e.getMessage());
            alert.showAndWait();
        }
    }

    private Image getBedImage(Bed bed) throws SQLException {
        List<Occupation> occs = new org.house.projetjava8.dao.OccupationDAO().getOccupationsForBed(bed.getId());
        LocalDate today = LocalDate.now();

        for (Occupation occ : occs) {
            // Si l'occupation est active
            if (!occ.isHasLeft() && (occ.getEndDate() == null || !today.isAfter(occ.getEndDate()))) {
                LocalDate endDate = occ.getEndDate() != null ? occ.getEndDate() : today;
                long joursRestants = ChronoUnit.DAYS.between(today, endDate);

                boolean coupure = occupationService.hasCoupure(bed.getId(), today, endDate);

                if (coupure) {
                    return new Image(getClass().getResourceAsStream("/img/bed_coupure.png"));
                }
                if (joursRestants > 14) {
                    return new Image(getClass().getResourceAsStream("/img/bed_red.png"));
                } else if (joursRestants > 7) {
                    return new Image(getClass().getResourceAsStream("/img/bed_yellow.png"));
                } else {
                    return new Image(getClass().getResourceAsStream("/img/bed_green.png"));
                }
            }
        }
        // Lit libre
        return new Image(getClass().getResourceAsStream("/img/bed_white.png"));
    }
}
