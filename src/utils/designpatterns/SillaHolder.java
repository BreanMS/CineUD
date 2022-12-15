package utils.designpatterns;

import model.Silla;

import java.util.List;

public class SillaHolder {

    private List<Silla> silla;
    private final static SillaHolder INSTANCE = new SillaHolder();

    private SillaHolder() {}

    public static SillaHolder getInstance() {
        return INSTANCE;
    }

    public void setSilla(List<Silla>  silla) {
        this.silla = silla;
    }

    public List<Silla>  getSilla() {
        return this.silla;
    }
}
