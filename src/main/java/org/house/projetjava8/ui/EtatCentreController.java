package org.house.projetjava8.ui;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import org.house.projetjava8.model.Bed;
import org.house.projetjava8.model.Occupation;
import org.house.projetjava8.service.BedService;
import org.house.projetjava8.service.OccupationService;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class EtatCentreController {

    @FXML
    private GridPane bedGrid;

    private BedService bedService = new BedService();
    private OccupationService occupationService = new OccupationService();

    @FXML
    public void initialize() {
        List<Bed> beds = bedService.getAllBeds();
        int col = 0, row = 0;

        for (Bed bed : beds) {
            ImageView bedIcon = new ImageView(getBedImage(bed));
            bedIcon.setFitWidth(40);
            bedIcon.setFitHeight(40);
            bedGrid.add(bedIcon, col, row);
            col++;
            if (col > 5) { col = 0; row++; }
        }
    }
    private Image getBedImage(Bed bed) {
    List<Occupation> occs = occupationService.getOccupationsForBed(bed.getId());
    LocalDate today = LocalDate.now();

    for (Occupation occ : occs) {
        if (!occ.isExited() && !today.isAfter(occ.getEndDate())) {
            long joursRestants = ChronoUnit.DAYS.between(today, occ.getEndDate());

            boolean coupure = occupationService.hasCoupure(bed.getId(), today, occ.getEndDate());

            if (coupure) {
                return new Image(getClass().getResource("/img/bed_coupure.png").toString()); // à créer
            }

            if (joursRestants > 14) {
                return new Image(getClass().getResource("/img/bed_red.png").toString());
            } else if (joursRestants > 7) {
                return new Image(getClass().getResource("/img/bed_yellow.png").toString());
            } else {
                return new Image(getClass().getResource("/img/bed_green.png").toString());
            }
        }
    }
    return new Image(getClass().getResource("/img/bed_white.png").toString());
}

}
