package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.io.IOException;

public class CineUD extends Application {
    @Override
    public void start(Stage stage) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(CineUD.class.getResource("/views/admin/admin-menu.fxml"));
//        FXMLLoader fxmlLoader = new FXMLLoader(CineUD.class.getResource("/views/user/sala/pagar-ticket.fxml"));
        FXMLLoader fxmlLoader = new FXMLLoader(CineUD.class.getResource("/views/iniciar-sesion.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("CineUD");
        stage.setScene(scene);
        stage.show();
        stage.setMaximized(true);
    }

    public static void main(String[] args) {
        launch();
    }
}