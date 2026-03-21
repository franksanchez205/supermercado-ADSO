package com.supermercado.supermercado.dtos;

public class CategoriaDTO {

    private String uuid;
    private String nombre;
    private String descripción;

    public CategoriaDTO() {
    }

    public CategoriaDTO(String uuid, String nombre, String descripción) {

        this.uuid = uuid;
        this.nombre = nombre;
        this.descripción = descripción;
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

    public String getDescripción() {
        return descripción;
    }

    public void setDescripción(String descripción) {
        this.descripción = descripción;
    }

}