module main {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires de.jensd.fx.glyphs.fontawesome;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires eu.hansolo.tilesfx;
    requires org.json;
    requires MaterialFX;
    requires java.sql;

    exports main;
    opens main to javafx.fxml;
    exports controller;
    opens controller to javafx.fxml;
    exports controller.user.peliculas;
    opens controller.user.peliculas to javafx.fxml;
    exports controller.user.sala;
    opens controller.user.sala to javafx.fxml;
    exports controller.admin;
    opens controller.admin to javafx.fxml;
}