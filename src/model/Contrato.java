package model;

public class Contrato {
    private int fechIn;
    private int salario;
    private String cargo;
    public Contrato(int fechIn, int salario, String cargo) {
        this.fechIn = fechIn;
        this.salario = salario;
        this.cargo = cargo;
    }
    public int getFechIn() {
        return fechIn;
    }

    public void setFechIn(int fechIn) {
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
