package utils.designpatterns.Memento;

import java.util.ArrayList;

public class SillaCaretaker {

    private ArrayList<SillaMemento> mementos = new ArrayList<>();

    public void addMemento(SillaMemento m) {
        this.mementos.add(m);
    }

    public SillaMemento getMemento(int index) {
        return this.mementos.get(index);
    }
}
