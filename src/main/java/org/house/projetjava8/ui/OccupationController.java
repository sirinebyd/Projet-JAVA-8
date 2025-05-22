package org.house.projetjava8.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.house.projetjava8.model.Occupation;
import org.house.projetjava8.service.OccupationService;

public class OccupationController {
    private final OccupationService service = new OccupationService();

    @FXML private TextField occupationInfoField;

    @FXML
    public void onSave() {
        Occupation occ = new Occupation();
        occ.setInfo(occupationInfoField.getText());
        service.save(occ);
    }
}
