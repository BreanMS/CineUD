package model;

public class Cliente {
    Usuario usuario;
    Integer puntos;

    public Cliente(Usuario usuario, Integer puntos) {
        this.usuario = usuario;
        this.puntos = puntos;

    }

    private String cedula;
    private String nombre;
    private String apellido;
    private long telefono;
    private String contrasena;
    private String email;

    public Cliente(String cedula, String nombre, String apellido, long telefono, String contrasena, Integer puntos, String email) {
        this.puntos = puntos;
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.contrasena = contrasena;
        this.email = email;
    }

    public void setPuntos(Integer puntos) {
        this.puntos = puntos;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
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

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public Integer getPuntos() {
        return puntos;
    }
}
