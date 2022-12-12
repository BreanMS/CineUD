package utils;

import controller.InfoController;
import controller.InicioSesionController;
import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class JavaFXHelpers {

    public void makeFadeOut(StackPane rootStackPane){
    FadeTransition fadeTransition = new FadeTransition();
    fadeTransition.setDuration(Duration.millis(1000));
    fadeTransition.setNode(rootStackPane);
    fadeTransition.setFromValue(1);
    fadeTransition.setToValue(0);
    fadeTransition.setOnFinished((ActionEvent event) -> {

    });
    fadeTransition.play();
    }

//    public static <T extends Event> void changeScene(String scenePath, T event, StackPane rootStackPane  ) throws IOException {
//
////        Node node = (Node) event.getSource();
////        Stage stage = (Stage) node.getScene().getWindow();
////        stage.close();
////        Scene scene = new Scene(FXMLLoader.load(JavaFXHelpers.class.getResource(scenePath)));
////        stage.setScene(scene);
////        stage.show();
////        stage.setMaximized(true);
//
//        FadeTransition fadeTransition = new FadeTransition();
//        fadeTransition.setDuration(Duration.millis(1000));
//        fadeTransition.setNode(rootStackPane);
//        fadeTransition.setFromValue(1);
//        fadeTransition.setToValue(0);
//        fadeTransition.setOnFinished((EventHandler<ActionEvent>) event) -> {
//            Parent nextView = FXMLLoader.load(JavaFXHelpers.class.getResource(scenePath));
//            Scene newScene = new Scene(nextView);
//            Stage currStage = (Stage) rootStackPane.getScene().getWindow();
//            currStage.setScene(newScene);
//        });
//        fadeTransition.play();
//    }

    public static <T extends Event> void changeScene(String scenePath, StackPane rootStackPane  ) throws IOException {

//        Node node = (Node) event.getSource();
//        Stage stage = (Stage) node.getScene().getWindow();
//        stage.close();
//        Scene scene = new Scene(FXMLLoader.load(JavaFXHelpers.class.getResource(scenePath)));
//        stage.setScene(scene);
//        stage.show();
//        stage.setMaximized(true);


            Parent nextView = FXMLLoader.load(JavaFXHelpers.class.getResource(scenePath));

            Scene newScene = new Scene(nextView);
            Stage currStage = (Stage) rootStackPane.getScene().getWindow();
            currStage.setScene(newScene);

    }

    public static void initializeHelper(int limitColumn, int instances, String scenePath, GridPane gridPane ) throws IOException{

        int row = 1;
        int column = 0;

            for (int i = 0; i < instances; i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(JavaFXHelpers.class.getResource(scenePath));
                AnchorPane anchorPane = fxmlLoader.load();


                if (column == limitColumn) {
                    column = 0;
                    row++;
                }

                gridPane.add(anchorPane, column++, row); //(child,column,row)
                //set gridPane width
                gridPane.setMinWidth(Region.USE_COMPUTED_SIZE);
                gridPane.setPrefWidth(Region.USE_COMPUTED_SIZE);
                gridPane.setMaxWidth(Region.USE_PREF_SIZE);

                //set gridPane height
                gridPane.setMinHeight(Region.USE_COMPUTED_SIZE);
                gridPane.setPrefHeight(Region.USE_COMPUTED_SIZE);
                gridPane.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }

    }

}
