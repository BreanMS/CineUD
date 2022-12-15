package model.enums;

public enum Rol {
    DIRECTOR, CAJERO, DESPACHADOR_COMIDA, ENCARGADO_SALA,ASEADOR;

    public Integer getSalario() {
        switch(this) {
        case DIRECTOR:
            return 4000000;

        case DESPACHADOR_COMIDA:
            return 1200000;

        case ENCARGADO_SALA:
            return 1200000;

        case ASEADOR:
            return 800000;

        default:
            return null;
    }
}
}

