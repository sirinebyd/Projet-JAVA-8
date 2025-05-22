package org.house.projetjava8.controller;

import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

public class MainController {
    @FXML
    private BorderPane mainPane;

    public void showPersonView() {
        loadView("/view/person_view.fxml");
    }

    public void showRoomView() {
        loadView("/view/room_view.fxml");
    }

    private void loadView(String fxmlPath) {
        try {
            Node view = FXMLLoader.load(getClass().getResource(fxmlPath));
            mainPane.setCenter(view);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
