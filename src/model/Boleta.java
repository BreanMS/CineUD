package model;

public class Boleta {
    private String id;
    private int precio;
    private Sala sala;
    private Multiplex Multiplex;
    private String fecha;

    public Boleta(String id, int precio, Sala sala, Multiplex Multiplex, String fecha) {
        this.id = id;
        this.precio = precio;
        this.sala = sala;
        this.Multiplex = Multiplex;
        this.fecha = fecha;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public Multiplex getMultiplex() {
        return Multiplex;
    }

    public void setMultiplex(Multiplex Multiplex) {
        this.Multiplex = Multiplex;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}