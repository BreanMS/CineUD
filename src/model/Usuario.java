package model;

//Se va a generar composcion a cliente y empleado con los atributos de usuario
public abstract class Usuario {
    protected int cedula;
    protected String nombre;
    protected String email;
    protected int telefono;

    public Usuario(int cedula, String nombre, String email, int telefono) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;

    }

}
