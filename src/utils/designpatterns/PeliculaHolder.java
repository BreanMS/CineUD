package utils.designpatterns;

import model.Pelicula;
import model.Silla;
//https://dev.to/devtony101/javafx-3-ways-of-passing-information-between-scenes-1bm8
public class PeliculaHolder {

    private Pelicula pelicula;
    private final static PeliculaHolder INSTANCE = new PeliculaHolder();

    private PeliculaHolder() {}

    public static PeliculaHolder getInstance() {
        return INSTANCE;
    }

    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
    }

    public Pelicula getPelicula() {
        return this.pelicula;
    }
}
