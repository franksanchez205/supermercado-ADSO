package com.supermercado.supermercado.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CategoriaDTO {

    private String uuidCodigo;

    @NotBlank(message = "El nombre es requerido")
    private String nombre;

    @NotBlank(message = "La descripción es requerida")
    private String descripción;

    public CategoriaDTO() {
    }

    public CategoriaDTO(String uuidCodigo, String nombre, String descripción) {

        this.uuidCodigo = uuidCodigo;
        this.nombre = nombre;
        this.descripción = descripción;
    }
}