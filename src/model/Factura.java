package model;

public class Factura {

    private int id;
    private String fecha;
    private int monto_final;
    private String cliente;
    private Snack snack;
    private Silla silla;

    public Factura() {
    }

    public Factura(int id, String fecha, int monto_final, String cliente) {
        this.id = id;
        this.fecha = fecha;
        this.monto_final = monto_final;
        this.cliente = cliente;
    }

    public Factura(int id, String fecha, String cliente, Snack snack, Silla silla) {
        this.id = id;
        this.fecha = fecha;
        this.monto_final = 0;
        this.cliente = cliente;
        this.snack = snack;
        this.silla = silla;
    }

    public void calcularMonto(int monto) {
        monto_final = monto_final + monto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getMonto_final() {
        return monto_final;
    }

    public void setMonto_final(int monto_final) {
        this.monto_final = monto_final;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public Snack getSnack() {
        return snack;
    }

    public void setSnack(Snack snack) {
        this.snack = snack;
    }

    public Silla getSilla() {
        return silla;
    }

    public void setSilla(Silla silla) {
        this.silla = silla;
    }
}
