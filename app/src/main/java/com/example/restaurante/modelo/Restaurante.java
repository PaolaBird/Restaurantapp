package com.example.restaurante.modelo;

public class Restaurante {
    private String restaurante;
    private String nombre;
    private String descripcion;
    private double longitud;
    private double latitud;
    private int like;

    public Restaurante() {
    }

    public Restaurante(String restaurante, String nombre, String descripcion, double longitud, double latitud, int like) {
        this.restaurante = restaurante;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.longitud = longitud;
        this.latitud = latitud;
        this.like = like;
    }

    public String getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(String restaurante) {
        this.restaurante = restaurante;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }
}
