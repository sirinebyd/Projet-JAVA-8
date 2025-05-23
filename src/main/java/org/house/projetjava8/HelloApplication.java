package org.house.projetjava8;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(
            getClass().getResource("/org/house/view/MainView.fxml")
        );
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        stage.setTitle("Centre d’hébergement - Accueil");
        stage.setScene(scene);
        stage.show();
    }



    public static void main(String[] args) {
        launch();
    }
}
