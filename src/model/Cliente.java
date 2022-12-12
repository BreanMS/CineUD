package model;

public class Cliente extends Persona {
    Integer puntos;

    public Cliente(int cedula, String nombre, String email, int telefono, int puntos){
        super(cedula,nombre,email,telefono);
        this.puntos = puntos;

    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public Integer getPuntos() {
        return puntos;
    }
}
