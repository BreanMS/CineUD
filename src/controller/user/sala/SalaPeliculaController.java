package controller.user.sala;

import com.dlsc.formsfx.model.validators.RegexValidator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import model.Pelicula;
import model.Silla;
import utils.Memento;
import utils.MementoSillas;
import utils.designpatterns.PeliculaHolder;
import utils.designpatterns.SillaHolder;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static utils.JavaFXHelpers.changeScene;
import static utils.JavaFXHelpers.initializeHelper;
//Elegir botones especificos
//https://stackoverflow.com/questions/47700220/getting-the-id-of-a-button-in-javafx
public class SalaPeliculaController implements Initializable {
    @FXML
    StackPane rootStackPane;
    @FXML
    GridPane gridSillas;
    @FXML
    Button btnProcederPago;
    @FXML
    Label lblPelicula;
    @FXML
    Label lblMultiplex;
    @FXML
    Label lblHorario;
    SillaController sillaController;
    public List<Silla> sillasGNRL = new ArrayList<>();
    public List<Silla> sillasPRFR = new ArrayList<>();
    Button[] buttonList = new Button[40];

    Silla.TipoSilla sillaGeneral = Silla.TipoSilla.GENERAL;
    Silla.TipoSilla sillaPreferencial = Silla.TipoSilla.PREFERENCIAL;
    Silla.EstadoSilla elegido = Silla.EstadoSilla.ELEGIDA;

    PeliculaHolder peliculaHolder = PeliculaHolder.getInstance();
    SillaHolder sillaHolder = SillaHolder.getInstance();
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < 40; i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/views/user/sala/silla.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();


                sillaController = fxmlLoader.getController();
                sillaController.setInformacion("C" + column + ":F" + row);
//                sillas.add(sillaController.btnSilla.getId());
                buttonList[i] = sillaController.btnSilla;

                if(i >= 20){
                    buttonList[i].setId("PRFR C" + column + ":F" + row);
                } else{
                    buttonList[i].setId("GNRL C" + column + ":F" + row);
                }

                buttonList[i].setOnAction(event -> checkID((Button) event.getSource()));
//                sillaController.btnSilla.setOnAction(event -> checkID( sillaController.btnSilla));


                if (column == 10) {
                    column = 0;
                    row++;
                }



                column++;
                gridSillas.add(anchorPane, column, row); //(child,column,row)

                //set gridSillas width
                gridSillas.setMinWidth(Region.USE_COMPUTED_SIZE);
                gridSillas.setPrefWidth(Region.USE_COMPUTED_SIZE);
                gridSillas.setMaxWidth(Region.USE_PREF_SIZE);

                //set gridSillas height
                gridSillas.setMinHeight(Region.USE_COMPUTED_SIZE);
                gridSillas.setPrefHeight(Region.USE_COMPUTED_SIZE);
                gridSillas.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void checkID(Button button){

        String[] arrOfStr = button.getId().split(" ", 2);

        if (arrOfStr[0].equals("GNRL")){
            Silla sillaGNRL = new Silla(button.getId(), sillaGeneral, elegido,11000);
            sillasGNRL.add(sillaGNRL);
            return;
        }
//        if (arrOfStr[0].equals("PRFR")){
        Silla sillaPRFR = new Silla(button.getId(), sillaPreferencial, elegido,11000);
        sillasPRFR.add(sillaPRFR);
//        }

        button.setStyle("-fx-background-color: #FCD34D; -fx-font-fill: -black; -fx-text-fill: -black;");

        //So on
    }

    @FXML
    private void procederPago(ActionEvent event) throws IOException {
//        MementoSillas recordadSillas = new MementoSillas(sillas);
        //Conseguir el id de la base de datos
        //Hacer una query en la base de datos de la pelicula y retornar idiomas duracion duraicon y genero


        String[] lenguajes = {"Ingles","Some"};
        Pelicula pelicula = new Pelicula("id",lblPelicula.getText(),lenguajes,123,"Accion");

        peliculaHolder.setPelicula(pelicula);
        sillaHolder.setSilla(sillasGNRL);
        sillaHolder.setSilla(sillasPRFR);


        System.out.println(this.sillasGNRL);
        System.out.println(this.sillasPRFR);

        changeScene("/views/user/sala/pagar-ticket.fxml", rootStackPane);

    }
}
