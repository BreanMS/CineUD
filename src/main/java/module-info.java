module ud.mp.proyecto.cineud {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires eu.hansolo.tilesfx;

    opens ud.mp.proyecto.cineud to javafx.fxml;
    exports ud.mp.proyecto.cineud;
}