package utils.designpatterns;

import model.Factura;
import model.Silla;
import model.Snack;
import utils.designpatterns.FacturaInvoker;
import utils.designpatterns.FacturaSilla;
import utils.designpatterns.FacturaSnack;

import java.util.ArrayList;

public class testFactura {

    public static void main (String[] args){

        Factura f = new Factura();

        Snack sk = new Snack("1", "Papitas", 200);

        ArrayList<Silla> carritoSilla = new ArrayList<>();
        //carritoSilla.add();
        ArrayList<Snack> carritoSnack = new ArrayList<>();
        carritoSnack.add(sk);
        carritoSnack.add(sk);
        carritoSnack.add(sk);

        FacturaSnack fsk = new FacturaSnack(f, carritoSnack);
        FacturaSilla fs = new FacturaSilla(f, carritoSilla);

        FacturaInvoker fi = new FacturaInvoker();
        fi.recibirPedidos(fsk);
        fi.recibirPedidos(fs);

        fi.calcularPedidos();

        System.out.println(f.getMonto_final());





    }
}
