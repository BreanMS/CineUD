package utils.designpatterns.Memento;

import model.Silla;
import utils.designpatterns.Memento.SillaCaretaker;
import utils.designpatterns.Memento.SillaOriginator;

import java.util.ArrayList;

public class testMemento {

    public static void main(String[] args) {

        //Definir silla
        Silla.TipoSilla general = Silla.TipoSilla.GENERAL;
        Silla.TipoSilla preferencial = Silla.TipoSilla.PREFERENCIAL;
        Silla.EstadoSilla elegido = Silla.EstadoSilla.ELEGIDA;
        Silla.EstadoSilla disponible = Silla.EstadoSilla.DISPONIBLE;

        Silla silla = new Silla("dd", general, disponible,11000);
        Silla silla1 = new Silla("dd", general, disponible,11000);

        // Instancias memento
        SillaOriginator so = new SillaOriginator();
        SillaCaretaker sc = new SillaCaretaker();

        ArrayList<Silla> carritoSilla = new ArrayList<>();

        carritoSilla.add(silla);
        carritoSilla.add(silla1);

        // Guarda estado en originator
        so.setEstado(carritoSilla);
        // Se agrega ese estado al historial de memento con index 0
        sc.addMemento(so.guardar());
        carritoSilla = so.getEstado();
        System.out.println("1"+carritoSilla.get(0).estadoSilla);
        System.out.println("1"+carritoSilla.get(1).estadoSilla);

        // cambios en las sillas
        silla = new Silla("dd", general, disponible,11000);
        silla1 = new Silla("dd", general, elegido,11000);
        carritoSilla.clear();
        carritoSilla.add(silla);
        carritoSilla.add(silla1);
        // Guarda estado en originator
        so.setEstado(carritoSilla);
        // Se agrega ese estado al historial de memento con index 1
        sc.addMemento(so.guardar());
        carritoSilla = so.getEstado();
        System.out.println("2"+carritoSilla.get(0).estadoSilla);
        System.out.println("2"+carritoSilla.get(1).estadoSilla);

        // más cambios en las sillas
        silla = new Silla("dd", general, elegido,11000);
        silla1 = new Silla("dd", general, elegido,11000);
        carritoSilla.clear();
        carritoSilla.add(silla);
        carritoSilla.add(silla1);
        // Guarda estado en originator
        so.setEstado(carritoSilla);
        // Se agrega ese estado al historial de memento con index 2
        sc.addMemento(so.guardar());
        carritoSilla = so.getEstado();
        System.out.println("3"+carritoSilla.get(0).estadoSilla);
        System.out.println("3"+carritoSilla.get(1).estadoSilla);

        // Se restaura el historial con el index correpondiente
        so.restaurar(sc.getMemento(1));
        carritoSilla = so.getEstado();
        System.out.println("4"+carritoSilla.get(0).estadoSilla);
        System.out.println("4"+carritoSilla.get(1).estadoSilla);


    }
}
