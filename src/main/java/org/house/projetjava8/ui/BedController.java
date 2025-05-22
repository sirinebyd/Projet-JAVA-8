package org.house.projetjava8.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.house.projetjava8.model.Bed;
import org.house.projetjava8.service.BedService;

public class BedController {
    private final BedService service = new BedService();

    @FXML private TextField capacityField;

    @FXML
    public void onSave() {
        Bed bed = new Bed();
        bed.setCapacity(Integer.parseInt(capacityField.getText()));
        service.save(bed);
    }
}
