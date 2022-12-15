package model;

public class Cliente  {
    Usuario usuario;
    Integer puntos;

    public Cliente(Usuario usuario, Integer puntos){
        this.usuario = usuario;
        this.puntos = puntos;

    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public Integer getPuntos() {
        return puntos;
    }
}
