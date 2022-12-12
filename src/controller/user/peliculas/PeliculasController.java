package controller.user.peliculas;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static utils.JavaFXHelpers.changeScene;
import static utils.JavaFXHelpers.initializeHelper;

public class PeliculasController implements Initializable {

    @FXML
    GridPane gridPeliculas;
    @FXML
    StackPane rootStackPane;

    @Override
    public void initialize(URL location, ResourceBundle resources)  {
        try {
            initializeHelper(5,10,"/views/user/peliculas/pelicula.fxml",gridPeliculas);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}