package com.supermercado.supermercado.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CategoriaDTO {

    private String uuidCodigo;

    private String nombre;

    private String descripcion;

    public CategoriaDTO() {
    }

    public CategoriaDTO(String uuidCodigo, String nombre, String descripcion) {

        this.uuidCodigo = uuidCodigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }
}