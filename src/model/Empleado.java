package model;

import model.enums.Rol;

public class Empleado {

    private
    Usuario persona;
    Rol rol;

    public Empleado(Usuario usuario, Rol rol) {
        this.persona = usuario;
        this.rol = rol;
    }

    private String cedula;
    private int codigo;
    private String nombre;
    private long telefono;
    private String contrasena;
    private String email;

    public Empleado(String cedula, int codigo, String nombre, long telefono, String contrasena, String email) {
        this.cedula = cedula;
        this.codigo = codigo;
        this.nombre = nombre;
        this.telefono = telefono;
        this.contrasena = contrasena;
        this.email = email;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public long getTelefono() {
        return telefono;
    }

    public void setTelefono(long telefono) {
        this.telefono = telefono;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
