package utils;

import java.util.List;

public class MementoSillas implements Memento
{

    List<String> estadoSillas;


    public MementoSillas(List<String> estadoSillas) {
        this.estadoSillas = estadoSillas;
    }
    @Override
    public void restore() {

    }
}
