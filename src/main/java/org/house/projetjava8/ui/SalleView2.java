package org.house.projetjava8.ui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class SalleView2 extends Application {

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();

        //menu latéral
        VBox sidebar = new VBox(15);
        sidebar.setPadding(new Insets(20));
        sidebar.setStyle("-fx-background-color: #4DB8FF;");
        sidebar.setPrefWidth(250);

        Button homeBtn = new Button("🏠 Home");
        TextField searchField = new TextField("🔍 Rechercher");
        searchField.setMaxWidth(200);
        VBox salleList = new VBox(5);
        for (int i = 1; i <= 7; i++) {
            salleList.getChildren().add(new Label("Salle " + i + " - Description"));
        }
        salleList.setStyle("-fx-background-color: #eeeeee; -fx-padding: 10; -fx-background-radius: 20;");
        sidebar.getChildren().addAll(homeBtn, searchField, salleList);

        // haut
        VBox topBox = new VBox(10);
        Label title = new Label("Salle 5 - Description de la salle");
        title.setFont(Font.font("Serif", 28));
        title.setStyle("-fx-padding: 10 0 0 20;");
        HBox topButtons = new HBox(20);
        Button addBtn = new Button("Ajouter une personne");
        Button editBtn = new Button("Modifier la salle");
        topButtons.getChildren().addAll(addBtn, editBtn);
        topButtons.setAlignment(Pos.CENTER_RIGHT);
        topButtons.setPadding(new Insets(0, 30, 10, 0));
        topBox.getChildren().addAll(title, new Separator(), topButtons);
    
        // Centre
        VBox scrollContent = new VBox(0);
        scrollContent.setAlignment(Pos.TOP_CENTER);

        
        Pane litPane = new Pane();
        litPane.setPrefSize(800, 400);
        litPane.setStyle("-fx-background-color: #D6F2FF; -fx-background-radius: 30 30 0 0;");
        Image bedRed = new Image("file:img/bed_red.png", 60, 60, true, true);
        Image bedGreen = new Image("file:img/bed_green.png", 60, 60, true, true);

        double[][] positions = {
            {100, 50}, {200, 50}, {100, 130}, {200, 130},
            {500, 50}, {600, 50}, {500, 130}, {600, 130},
            {100, 250}, {200, 250}, {500, 250}, {600, 250},
            {100, 330}, {200, 330}, {500, 330}, {600, 330}
        };

        boolean[] occupation = {
            true, true, true, true,
            false, true, false, true,
            false, true, false, false,
            false, true, false, true
        };

        String[][] personnes = {
            {"Jean Dupont", "14/04/2025", "25/04/2025"},
            {"Marie Dubois", "30/03/2025", "20/04/2025"},
            {"Prénom Nom", "Date arrivée", "Date sortie"},
            {"Prénom Nom", "Date arrivée", "Date sortie"},
            {"", "", ""},
            {"Prénom Nom", "Date arrivée", "Date sortie"},
            {"", "", ""},
            {"Prénom Nom", "Date arrivée", "Date sortie"},
            {"", "", ""},
            {"Prénom Nom", "Date arrivée", "Date sortie"},
            {"", "", ""},
            {"", "", ""},
            {"", "", ""},
            {"Prénom Nom", "Date arrivée", "Date sortie"},
            {"", "", ""},
            {"Prénom Nom", "Date arrivée", "Date sortie"}
        };

        int redCount = 0;
        int greenCount = 0;

        for (int i = 0; i < positions.length; i++) {
            ImageView lit = new ImageView(occupation[i] ? bedRed : bedGreen);
            lit.setLayoutX(positions[i][0]);
            lit.setLayoutY(positions[i][1]);
            litPane.getChildren().add(lit);
            if (occupation[i]) redCount++;
            else greenCount++;
        }

        //bas
        Label stats = new Label(redCount + " lits occupés / " + greenCount + " disponibles");
        stats.setFont(Font.font("Serif", 18));
        stats.setPadding(new Insets(15, 0, 10, 0));
        stats.setAlignment(Pos.CENTER);
        stats.setStyle("-fx-background-color: white;");

        
        VBox listeOccupants = new VBox(15);
        listeOccupants.setPadding(new Insets(20));
        listeOccupants.setStyle("-fx-background-color: white;");
        Label occupantsTitle = new Label("Liste des personnes :");
        occupantsTitle.setFont(Font.font("Serif", 20));
        listeOccupants.getChildren().add(occupantsTitle);

        for (int i = 0; i < occupation.length; i++) {
            if (occupation[i] && !personnes[i][0].isEmpty()) {
                String nom = personnes[i][0];
                String arrivee = personnes[i][1];
                String sortie = personnes[i][2];
                Hyperlink personneLink = new Hyperlink(nom + " : " + arrivee + " - " + sortie);
                personneLink.setStyle("-fx-font-size: 16px;");
                int index = i;
                personneLink.setOnAction(e -> {
                    try {
                        new Occupant().start(new Stage());
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                });
                listeOccupants.getChildren().add(personneLink);
            }
        }

        
        scrollContent.getChildren().addAll(litPane, stats, listeOccupants);
        ScrollPane scrollPane = new ScrollPane(scrollContent);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background: white;");



        // === Final layout ===
        root.setLeft(sidebar);
        root.setTop(topBox);
        root.setCenter(scrollPane);

        Scene scene = new Scene(root, 1200, 800);
        primaryStage.setTitle("Salle");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

