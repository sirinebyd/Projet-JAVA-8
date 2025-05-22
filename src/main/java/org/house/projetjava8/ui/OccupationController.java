package org.house.projetjava8.controller;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import org.house.projetjava8.model.Occupation;
import org.house.projetjava8.service.OccupationService;

public class OccupationController {
    private final OccupationService service = new OccupationService();

    @FXML private TextField personIdField;
    @FXML private TextField bedIdField;
    @FXML private DatePicker startDatePicker;
    @FXML private DatePicker endDatePicker;
    @FXML private TextField hasLeftField;

    @FXML
    public void onSave() {
        Occupation occupation = new Occupation();
        occupation.setPersonId(Integer.parseInt(personIdField.getText()));
        occupation.setBedId(Integer.parseInt(bedIdField.getText()));
        occupation.setStartDate(startDatePicker.getValue());
        occupation.setEndDate(endDatePicker.getValue());
        occupation.setHasLeft(Boolean.parseBoolean(hasLeftField.getText()));
        service.save(occupation);
    }
}
