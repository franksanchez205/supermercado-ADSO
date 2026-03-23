package com.supermercado.supermercado.dtos;

import lombok.Data;

@Data
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
}