package controller.user.sala;

import controller.InfoController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import model.Sala;
import model.Silla;

import java.util.Locale;

public class SillaController extends InfoController {

    @FXML
    Button btnSilla;
    Silla silla;

//    public void setInformacion(Sala sala, Silla silla, String number) {
//        String idSilla = sala.getId() + silla.getTipo().toUpperCase(Locale.ROOT).substring(0,3) + ":" + silla.getId() ;
//        this.silla = silla;
//        this.silla.setId(idSilla);
//        btnSilla.setText(number);
//    }
public void setInformacion(String number) {
    btnSilla.setText(number);
}
}
