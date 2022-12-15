package utils.designpatterns;

import model.Pelicula;
import model.Snack;

public class SnackHolder {

    private Snack snack;
    private final static SnackHolder INSTANCE = new SnackHolder();

    private SnackHolder() {}

    public static SnackHolder getInstance() {
        return INSTANCE;
    }

    public void setSnack(Snack snack) {
        this.snack = snack;
    }

    public Snack getSnack() {
        return this.snack;
    }
}
