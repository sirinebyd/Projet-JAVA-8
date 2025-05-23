module org.house.projetjava8 {
    requires transitive javafx.controls;
    requires transitive javafx.fxml;
    requires transitive javafx.graphics;
    requires transitive javafx.base;
    requires transitive java.sql;
    requires javafx.web;
    requires javafx.media;
    requires javafx.swing;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires eu.hansolo.fx.countries;
    requires eu.hansolo.fx.heatmap;
    requires eu.hansolo.toolboxfx;
    requires eu.hansolo.toolbox;
    requires com.almasb.fxgl.all;

    requires com.gluonhq.attach.lifecycle;
    requires com.gluonhq.attach.util;
    requires com.gluonhq.attach.storage;
    requires com.gluonhq.attach.audio;

    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.databind;

    // Ouvre tous les packages nécessaires pour FXML
    opens org.house.projetjava8.ui to javafx.fxml;
    opens org.house.projetjava8.model to javafx.base, javafx.fxml;
    opens org.house.projetjava8.service to javafx.fxml;
    opens org.house.projetjava8.dao to javafx.fxml;

    // Exporte tous les packages nécessaires
    exports org.house.projetjava8;
    exports org.house.projetjava8.ui;
    exports org.house.projetjava8.model;
    exports org.house.projetjava8.service;
    exports org.house.projetjava8.dao;
}
