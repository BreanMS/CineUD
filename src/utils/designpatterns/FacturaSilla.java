package utils.designpatterns;

import model.Factura;
import model.Silla;

import java.util.ArrayList;

public class FacturaSilla implements FacturaCommand {
    private ArrayList<Silla> listaSilla;
    private Factura factura;
    private int monto;

    public FacturaSilla(Factura factura, ArrayList<Silla> listaSilla){
        this.factura = factura;
        if (listaSilla.isEmpty()){
            this.monto = 0;
        } else {
            this.listaSilla = listaSilla;
            this.listaSilla.forEach(sl -> monto = monto + sl.getPrecio());
        }
    }

    @Override
    public void execute() {
        this.factura.calcularMonto(this.monto);
    }
}
