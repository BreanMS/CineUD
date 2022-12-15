package main;

import model.Multiplex;

import java.util.ArrayList;
import model.*;
import model.enums.Rol;

import static model.MultiplexBuilder.multiplex;

public class CineUDFacade {

    private String id;
    private String nombreMultiplex;
    private ArrayList<Pelicula> peliculas;
    private ArrayList<Empleado> empleados;
    private ArrayList<Snack> snacks;
    private ArrayList<Sala> salas;
    private ArrayList<Multiplex> arrayMultiplex = new ArrayList<Multiplex>();

//    public void inicializarSilla(String id, TipoSilla tipoSilla, Integer precio, EstadoSilla estadoSilla ) {
//        Multiplex multiplex = new Multiplex();
//
//    }
    public void inicializarEmpleados(Usuario usuario, Rol rol ) {
        Empleado multiplex = new Empleado(usuario, rol);
    }
    public void inicializarSnacks(String id, String nombre, Integer precio) {
        Snack snack = new Snack(id, nombre, precio);
        snacks.add(snack);
    }
    public void inicializarPelicula(String id, String titulo, String[] idiomas, Integer duracion, String genero) {
        Pelicula pelicula = new Pelicula(id, titulo, idiomas, duracion, genero);
        peliculas.add(pelicula);
    }
    public void inicializarSalas(String id, Pelicula pelicula) {
        Sala sala = new Sala(id,pelicula);
        salas.add(sala);
    }
    // Por cada iteracion segun la longitud de entradas de la tabla de la base de datos, se va a llamar esta funcion para crear el multiplex con sus respectivos atributos
    public void inicializarMultiplex(String id,String nombreMultiplex,  ArrayList<Pelicula> peliculas, ArrayList<Empleado> empleados, ArrayList<Snack> snacks, ArrayList<Sala> salas) {
    Multiplex multiplex = multiplex()
                          .withID(id)
                          .withNombreMultiplex(nombreMultiplex)
                          .withPeliculas(peliculas)
                          .withEmpleados(empleados)
                          .withSnacks(snacks)
                          .withSalas(salas)
                          .buildMultiplex();
    }
    public void alimentarBasedeDatos(){

    }


}
