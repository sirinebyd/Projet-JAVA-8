package org.house.projetjava8.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.house.projetjava8.model.Room;
import org.house.projetjava8.service.RoomService;

public class RoomController {
    private final RoomService service = new RoomService();

    @FXML private TextField nameField;
    @FXML private TextField genderRestrictionField;
    @FXML private TextField minAgeField;
    @FXML private TextField maxAgeField;

    @FXML
    public void onSave() {
        Room room = new Room();
        room.setName(nameField.getText());
        room.setGenderRestriction(genderRestrictionField.getText());
        room.setMinAge(Integer.parseInt(minAgeField.getText()));
        room.setMaxAge(Integer.parseInt(maxAgeField.getText()));
        service.save(room);
    }
    @FXML
private void handleDeleteRoom() {
    try {
        roomService.deleteRoomIfPossible(selectedRoom.getId());
        refreshRoomList();
    } catch (IllegalStateException e) {
        showAlert("Erreur", e.getMessage());
    }
}
}
