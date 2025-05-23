package org.house.projetjava8.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.house.projetjava8.model.Bed;
import org.house.projetjava8.service.BedService;

public class BedController {
    private final BedService service = new BedService();

    @FXML private TextField labelField;
    @FXML private TextField capacityField;
    @FXML private TextField roomIdField;

    @FXML
    public void onSave() {
        Bed bed = new Bed();
        bed.setLabel(labelField.getText());
        bed.setCapacity(Integer.parseInt(capacityField.getText()));
        bed.setRoomId(Integer.parseInt(roomIdField.getText()));
        service.save(bed);
    }

}
