package org.house.projetjava8.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.house.projetjava8.model.Room;
import org.house.projetjava8.service.RoomService;

public class RoomController {
    private final RoomService service = new RoomService();

    @FXML private TextField roomNameField;

    @FXML
    public void onSave() {
        Room room = new Room();
        room.setName(roomNameField.getText());
        service.save(room);
    }
}
