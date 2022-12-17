package utils.BaseDatos;

public class RHException extends Exception{
    private String detalle;
    private String clase;
    /**
     * Método constructor que recoge la descripción del
     * error que genero la excepción.
     *
     * @param error
     *        cadena que contiene la descripción del error.
     */

    public RHException(String clase,String error) {
        super(error);
        this.clase = clase;
        detalle = error;
    }

    public String toString() {
        return "["+clase+"] "+detalle;
    }
}
