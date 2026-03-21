package com.supermercado.supermercado.dtos;

public class ProveedorDTO {

    private int nit;
    private String uuid;
    private String nombre;
    private String telefono;
    private String direccion;

    public ProveedorDTO() {
    }

    public ProveedorDTO(int nit, String uuid, String nombre, String telefono, String direccion) {

        this.nit = nit;
        this.uuid = uuid;
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
    }

    public int getNit() {
        return nit;
    }

    public void setNit(int nit) {
        this.nit = nit;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

}
