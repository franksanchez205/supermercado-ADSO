package com.supermercado.supermercado.dtos;

import lombok.Data;

@Data
public class CategoriaDTO {


    private String uuidCodigo;
    private String nombre;
    private String descripción;

    public CategoriaDTO() {
    }

    public CategoriaDTO(String uuidCodigo, String nombre, String descripción) {

        this.uuidCodigo = uuidCodigo;
        this.nombre = nombre;
        this.descripción = descripción;
    }
}