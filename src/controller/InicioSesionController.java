package controller;

import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import utils.JavaFXHelpers;
import utils.Validators;

import java.io.IOException;
import java.util.HashMap;

import static utils.JavaFXHelpers.changeScene;

public class InicioSesionController
{
    @FXML
    TextField txtEmail;
    @FXML
    MFXButton btnIniciarSesion;
    @FXML
    StackPane rootStackPane;
    @FXML
    Label lblErrores;

    @FXML
    private void iniciarSesion(ActionEvent event ) throws IOException {

        System.out.println("Test del button");
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        //stage.setMaximized(true);
        stage.close();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/views/user/peliculas/peliculas.fxml")));
        stage.setScene(scene);
        stage.show();
    }
//
//
//    @FXML
//    public void handleButtonAction(MouseEvent event) {
//
//        if (event.getSource() == btnRegistrate) {
//            //login here
//            if (logIn().equals("Success")) {
//                try {
//
//                    //add you loading or delays - ;-)
//
//
//                } catch (IOException ex) {
//                    System.err.println(ex.getMessage());
//                }
//
//            }
//        }
//    }
//
//    private String logIn() {
//        String status = "Success";
//        String email = txtUsername.getText();
//        String password = txtPassword.getText();
//        if(email.isEmpty() || password.isEmpty()) {
//            setLblError(Color.TOMATO, "Empty credentials");
//            status = "Error";
//        } else {
//            //query
//            String sql = "SELECT * FROM admins Where email = ? and password = ?";
//            try {
//                preparedStatement = con.prepareStatement(sql);
//                preparedStatement.setString(1, email);
//                preparedStatement.setString(2, password);
//                resultSet = preparedStatement.executeQuery();
//                if (!resultSet.next()) {
//                    setLblError(Color.TOMATO, "Enter Correct Email/Password");
//                    status = "Error";
//                } else {
//                    setLblError(Color.GREEN, "Login Successful..Redirecting..");
//                }
//            } catch (SQLException ex) {
//                System.err.println(ex.getMessage());
//                status = "Exception";
//            }
//        }
//
//        return status;
//    }
//
//    private void onSucessLogin() {
//        lblErrores.setVisible(false);
//
//        if(onLogin()){
//                    try {
//                        changeScene("/user/peliculas/peliculas.fxml", rootStackPane);
//                    } catch (Exception e1) {
//                        e1.printStackTrace();
//                    }
//
//                };
//    }


    private void onErrorLogin() {
        lblErrores.setVisible(true);
    }
}

