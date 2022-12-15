package controller.user.peliculas;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.util.Stack;

import static utils.JavaFXHelpers.changeScene;

public class PeliculaController {

    @FXML
    Pane panePelicula;
    @FXML
    StackPane rootStackPane;


    @FXML
    private void detallesPelicula(MouseEvent event, StackPane rootStackPane) throws IOException {
        System.out.println("Change scene ma boy");
        changeScene("/views/user/peliculas/detalles-pelicula.fxml", rootStackPane);
    }


}
