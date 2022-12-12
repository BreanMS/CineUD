package model;
public class Silla {
    private String id;
    private String tipo;
    private int precio;

    public Silla(String id, String tipo, int precio){
        this.id = id;
        this.tipo = tipo;
        this.precio = precio;
    }
    public void setId(String id) {
        this.id = id;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }

    public int getPrecio() {
        return precio;
    }

}