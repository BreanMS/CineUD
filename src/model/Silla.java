package model;

// las silla tendrian patron decorator ya que el precio cambia?
//el wrapper define el precio de la silla segun tipo
//mientras el estado seria una clase para definir cambios dependiendo el estado


public abstract class Silla {
    private String id;
    private TipoSilla tipo;
    private Integer precio;
    private EstadoSilla estadoSilla;

    enum TipoSilla {
        GENERAL,
        PREFERENCIAL
    }
    enum EstadoSilla {
        DISPONIBLE,
        ELEGIDA,
        OCUPADA,
    }
    public Silla(String id, TipoSilla tipo, EstadoSilla estadoSilla,Integer precio){
        this.id = id;
        this.tipo = tipo;
        this.precio = precio;
        this.estadoSilla = estadoSilla;
    }
    public void setId(String id) {
        this.id = id;
    }

    public void setTipo(TipoSilla tipo) {
        this.tipo = tipo;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getId() {
        return id;
    }

    public TipoSilla getTipo() {
        return tipo;
    }

    public int getPrecio() {
        return precio;
    }

}