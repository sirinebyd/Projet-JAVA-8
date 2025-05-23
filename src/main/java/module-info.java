module org.house.projetjava8 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires java.sql;
    requires javafx.base;

    requires org.controlsfx.controls;
    requires com.almasb.fxgl.scene;
    requires com.almasb.fxgl.gameplay;
    requires com.almasb.fxgl.io;
    requires com.almasb.fxgl.entity;
    requires com.dlsc.formsfx;
    requires kotlin.stdlib;
    requires eu.hansolo.fx.countries;
    requires eu.hansolo.fx.heatmap;
    requires eu.hansolo.toolboxfx;
    requires eu.hansolo.toolbox;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires org.junit.jupiter.api;
    requires org.opentest4j;

    opens org.house.projetjava8 to javafx.fxml;
    opens org.house.projetjava8.ui to javafx.fxml;

    exports org.house.projetjava8;
    exports org.house.projetjava8.ui;
}
