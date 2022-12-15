package utils.designpatterns.Memento;

import model.Silla;

import java.util.ArrayList;

public class SillaMemento {

    private ArrayList<Silla> estado = new ArrayList<>();

    public SillaMemento(ArrayList<Silla> estado) {
        this.estado.addAll(estado);
    }

    public ArrayList<Silla> getEstado() {
        return this.estado;
    }
}
