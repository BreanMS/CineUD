package model;

import model.enums.Rol;

public class Empleado {

    Usuario persona;
    Rol rol;

    public Empleado(Usuario usuario, Rol rol)
    {
        this.persona = usuario;
        this.rol = rol;
    }
}
