package model;

import java.util.ArrayList;

//Aplicacion Fluent Interface Pattern para creacion de Multiplex con diversos argumentos
//Recursos : https://martinfowler.com/bliki/FluentInterface.html
// https://stackoverflow.com/questions/40264/how-many-constructor-arguments-is-too-many
public  class MultiplexBuilder {
    String id;
    String nombreMultiplex;
    ArrayList<Pelicula> peliculas;
    ArrayList<Empleado> empleados;
    ArrayList<Snack> snacks;
    ArrayList<Sala> salas;

    public static MultiplexBuilder multiplex() {
        return new MultiplexBuilder();
    }

    public MultiplexBuilder withID(String id) {
        this.id = id;
        return this;
    }

    public MultiplexBuilder withNombreMultiplex(String nombreMultiplex) {
        this.nombreMultiplex = nombreMultiplex;
        return this;
    }

    public MultiplexBuilder withPeliculas(ArrayList<Pelicula> peliculas) {
        this.peliculas = peliculas;
        return this;
    }

    public MultiplexBuilder withEmpleados(ArrayList<Empleado> empleados) {
        this.empleados = empleados;
        return this;
    }

    public MultiplexBuilder withSnacks(ArrayList<Snack> snacks) {
        this.snacks = snacks;
        return this;
    }

    public MultiplexBuilder withSalas(ArrayList<Sala> salas) {
        this.salas = salas;
        return this;
    }

    // client doesn't get to instantiate Customer directly
    public Multiplex buildMultiplex() {
        return new Multiplex(this);
    }
}
