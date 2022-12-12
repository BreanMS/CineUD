package model;

import java.lang.constant.Constable;
import java.util.ArrayList;

public class Sala {
    private String id;
    private Pelicula pelicula;
    private ArrayList<Silla> sillas = new ArrayList<Silla>();

    public Sala(String id, Pelicula pelicula){
        this.id = id;
        this.pelicula = pelicula;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
    }
    public String getId() {
        return id;
    }

    public Pelicula getPelicula() {
        return pelicula;
    }

    public void addSilla(Silla silla){
        sillas.add(silla);
    }
    public void delSilla(Silla silla){
        sillas.remove(silla);
    }
}