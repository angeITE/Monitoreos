package com.example.monitoreos;

public class FormatoDirecciones {
    private String direccion;
    private String estado;
    private String nombre;
    FormatoDirecciones(String direccion, String estado, String nombre){
        this.direccion = direccion;
        this.estado = estado;
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
