package model;

import java.lang.constant.Constable;

public class Pelicula{
    private String id;
    private String titulo;
    private String[] idiomas;
    private Integer duracion;
    private String genero;


    public Pelicula(String id, String titulo, String[] idiomas, Integer duracion, String genero){
        this.id = id;
        this.titulo = titulo;
        this.idiomas = idiomas;
        this.duracion = duracion;
        this.genero = genero;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setIdioma(String[] idioma) {
        this.idiomas = idioma;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }


    public String getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String[] getIdioma() {
        return idiomas;
    }

    public int getDuracion() {
        return duracion;
    }

    public String getGenero() {
        return genero;
    }

}