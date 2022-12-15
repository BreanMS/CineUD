package utils.designpatterns;

import model.Factura;
import model.Snack;
import utils.designpatterns.FacturaCommand;

import java.util.ArrayList;

public class FacturaSnack implements FacturaCommand {

    private ArrayList<Snack> listaSnack;
    private Factura factura;
    private int monto;

    public FacturaSnack(Factura factura, ArrayList<Snack> listaSnack) {
        this.factura = factura;
        if (listaSnack.isEmpty()) {
            this.monto = 0;
        } else {
            this.listaSnack = listaSnack;
            this.listaSnack.forEach(sk -> monto = monto + sk.getPrecio());
        }
    }

    @Override
    public void execute() {
        this.factura.calcularMonto(this.monto);
    }
}
