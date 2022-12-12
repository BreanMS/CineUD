package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import utils.JavaFXHelpers;

import java.io.IOException;

import static utils.JavaFXHelpers.changeScene;

public class InicioSesionController
{
    @FXML
    Button btnRegistrate;
    @FXML
    StackPane rootStackPane;

    @FXML
    private void Registrarse( ) throws IOException {
    changeScene("/views/registro.fxml", rootStackPane);
    }
}