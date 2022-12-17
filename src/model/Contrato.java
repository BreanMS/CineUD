package model;

public class Contrato {
    private int id;
    private String fechIn;
    private int salario;
    private String cargo;
    private int multiplex;
    private int cliente;

    public Contrato(int id, String fechIn, String cargo, int salario, int multiplex, int cliente) {
        this.fechIn = fechIn;
        this.salario = salario;
        this.cargo = cargo;
        this.multiplex = multiplex;
        this.cliente = cliente;
    }

    public int getMultiplex() {
        return multiplex;
    }

    public void setMultiplex(int multiplex) {
        this.multiplex = multiplex;
    }

    public int getCliente() {
        return cliente;
    }

    public void setCliente(int cliente) {
        this.cliente = cliente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFechIn() {
        return fechIn;
    }

    public void setFechIn(String fechIn) {
        this.fechIn = fechIn;
    }

    public int getSalario() {
        return salario;
    }

    public void setSalario(int salario) {
        this.salario = salario;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
}
