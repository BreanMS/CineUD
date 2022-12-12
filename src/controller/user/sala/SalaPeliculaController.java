package controller.user.sala;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static utils.JavaFXHelpers.initializeHelper;

public class SalaPeliculaController implements Initializable {
    @FXML
    GridPane gridSillas;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < 40; i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/views/user/sala/silla.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();;

                SillaController itemController = fxmlLoader.getController();
                itemController.setInformacion("C" + column + " : F" + row);

                if (column == 10) {
                    column = 0;
                    row++;
                }

                gridSillas.add(anchorPane, column++, row); //(child,column,row)
                //set gridSillas width
                gridSillas.setMinWidth(Region.USE_COMPUTED_SIZE);
                gridSillas.setPrefWidth(Region.USE_COMPUTED_SIZE);
                gridSillas.setMaxWidth(Region.USE_PREF_SIZE);

                //set gridSillas height
                gridSillas.setMinHeight(Region.USE_COMPUTED_SIZE);
                gridSillas.setPrefHeight(Region.USE_COMPUTED_SIZE);
                gridSillas.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            } }catch (IOException e) {
            throw new RuntimeException(e);
        }
//            try {
//                initializeHelper(10, 40, "/views/user/sillas/silla.fxml", gridSillasSillas);
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }

    }
}
