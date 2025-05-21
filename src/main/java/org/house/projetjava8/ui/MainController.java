package org.house.projetjava8.ui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class MainController {

    @FXML
    private StackPane contentPane;

    @FXML
    private void handleShowOccupants() {
        loadView("/org/house/projetjava8/ui/OccupantView.fxml"); // à créer
    }

    @FXML
    private void handleShowRooms() {
        loadView("/org/house/projetjava8/ui/RoomView.fxml"); // à créer
    }

    @FXML
    private void handleShowAssignments() {
        loadView("/org/house/projetjava8/ui/AssignementView.fxml"); // à créer
    }

    private void loadView(String fxmlPath) {
        try {
            Node node = FXMLLoader.load(getClass().getResource(fxmlPath));
            contentPane.getChildren().setAll(node);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
