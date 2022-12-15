package controller.user.sala;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import model.Pelicula;
import model.Silla;
import utils.designpatterns.PeliculaHolder;
import utils.designpatterns.SillaHolder;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import static model.Silla.TipoSilla.GENERAL;
import static model.Silla.TipoSilla.PREFERENCIAL;

public class PagarTicketController implements Initializable {

    @FXML
    Label lblNombrePelicula;
    @FXML
    VBox vbxDetallesProducto;
    @FXML
    VBox vbxPrecioProducto;
    @FXML
    VBox vbxPrecios;

    PeliculaHolder peliculaHolder = PeliculaHolder.getInstance();
    SillaHolder sillaHolder = SillaHolder.getInstance();
    SalaPeliculaController salaPelicula = new SalaPeliculaController();

    Label lblNumeroSillasPreferencial;
    Label lblNumeroSillasGeneral;
    Label lblPRecio;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        vbxDetallesProducto.getChildren().clear();
        vbxPrecios.getChildren().clear();
        vbxPrecioProducto.getChildren().clear();
        Pelicula pelicula = peliculaHolder.getPelicula();


        lblNombrePelicula.setText(pelicula.getTitulo());

        List<Silla> sillasGeneral = sillaHolder.getSilla().stream()
                .filter(n -> n.getTipo().equals(GENERAL))
                .collect(Collectors.toList());

        List<Silla> sillasPreferencial = sillaHolder.getSilla().stream()
                .filter(n -> n.getTipo().equals(PREFERENCIAL))
                .collect(Collectors.toList());
        if (sillasGeneral.size() > 0) {
             lblNumeroSillasGeneral = new Label(sillasGeneral.size() + "x General " );
        }
       if (sillasPreferencial.size() > 0) {
            lblNumeroSillasPreferencial = new Label(sillasPreferencial.size() + "x Preferencial " );
       }

       sillasGeneral.forEach(silla -> );

        lblNumeroSillasGeneral.getStyleClass().add("h4");
        lblNumeroSillasPreferencial.getStyleClass().add("h4");
        lblPRecio.setStyle("-fx-text-fill: -yellow;");
//        lblPrecio.getStyleClass().add("h4");

//        Label lblPrecio = new Label("300000");
//
//        vbxPrecioProducto.getChildren().clear();

//        vbxDetallesProducto.getChildren().add(lblNumeroSillas);
//        vbxPrecioProducto.getChildren().add(lblPrecio);
    }

    private void calcularPrecio(){


//        Label lblSubTotal = new Label("Preferencial: " + salaPeliculaController.sillas.size());
        Label lblIVA = new Label("300000");
        Label Total = new Label("300000");
//        vbxPrecios.getChildren().add();
    }

}
