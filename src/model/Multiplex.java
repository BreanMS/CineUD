package model;

import java.util.ArrayList;

public class Multiplex {

    private String id;
    private String nombreMultiplex;
    private ArrayList<Pelicula> peliculas;
    private ArrayList<Empleado> empleados;
    private ArrayList<Snack> snacks;
    private ArrayList<Sala> salas;

    public Multiplex(MultiplexBuilder builder) {
        this.id = builder.id;
        this.nombreMultiplex = builder.nombreMultiplex;
        this.peliculas = builder.peliculas;
        this.empleados = builder.empleados;
        this.snacks = builder.snacks;
        this.salas = builder.salas;
    }

}
