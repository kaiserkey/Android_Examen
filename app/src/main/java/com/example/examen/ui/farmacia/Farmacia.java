package com.example.examen.ui.farmacia;

public class Farmacia {
    private String nombre;
    private String lat;
    private String lon;
    private String direccion;

    public Farmacia(String nombre, String lat, String lon, String direccion) {
        this.nombre = nombre;
        this.lat = lat;
        this.lon = lon;
        this.direccion = direccion;
    }

    public String getNombre() {
        return nombre;
    }

    public String getLat() {
        return lat;
    }

    public String getLon() {
        return lon;
    }

    public String getDireccion() {
        return direccion;
    }
}
