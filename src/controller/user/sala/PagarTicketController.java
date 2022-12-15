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
import java.util.ResourceBundle;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        Pelicula pelicula = peliculaHolder.getPelicula();

        lblNombrePelicula.setText(pelicula.getTitulo());

        Silla.TipoSilla general = Silla.TipoSilla.GENERAL;
        Silla.EstadoSilla elegido = Silla.EstadoSilla.ELEGIDA;
        Silla Silla = new Silla("dd", general, elegido,11000);
        System.out.println(Silla.tipo);
//
//        SalaPeliculaController salaPeliculaController = new SalaPeliculaController();
//        Label lblNumeroSillas = new Label("Preferencial: " + salaPeliculaController.sillas.size());
//        Label lblPrecio = new Label(300000);
//        vbxDetallesProducto.getChildren().clear();
//        vbxPrecioProducto.getChildren().clear();
//        lblNumeroSillas.getStyleClass().add("h4");
//        lblPrecio.setStyle("-fx-text-fill: -yellow;");
//        lblPrecio.getStyleClass().add("h4");
//        vbxDetallesProducto.getChildren().add(lblNumeroSillas);
//        vbxPrecioProducto.getChildren().add(lblPrecio);
    }
//
    private void calcularPrecio(){

        Label lblSubTotal = new Label("Preferencial: " + salaPeliculaController.sillas.size());
        Label lblIVA = new Label("300000");
        Label Total = new Label("300000");
        vbxPrecios.getChildren().add();
    }

}
