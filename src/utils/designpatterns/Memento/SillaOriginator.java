package utils.designpatterns.Memento;

import model.Silla;

import java.util.ArrayList;

public class SillaOriginator {

    private ArrayList<Silla> estadoActual = new ArrayList<>();

    public void setEstado(ArrayList<Silla> estadoActual) {this.estadoActual.addAll(estadoActual);}

    public ArrayList<Silla> getEstado() {
        return this.estadoActual;
    }

    public SillaMemento guardar() {
        return new SillaMemento(this.estadoActual);
    }

    public void restaurar(SillaMemento sm) {
        this.estadoActual = sm.getEstado();
    }

}
