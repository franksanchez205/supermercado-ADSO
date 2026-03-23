package com.supermercado.supermercado.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ProductoDTO {

    @Size(min = 3, max = 36, message = "El uuid debe tener entre 3 y 36 caracteres")
    private String uuidCodigo;

    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
    private String nombre;

    @NotBlank(message = "La descripción no puede estar vacía")
    @Size(min = 3, max = 100, message = "La descripción debe tener entre 3 y 100 caracteres")
    private String descripción;

    @NotNull(message = "El precio es obligatorio")
    @Positive(message = "El precio debe ser mayor a cero")
    private double precio;

    @Min(value = 0, message = "El stock no puede ser negativo")
    private int stock;

    private CategoriaDTO categoria;

    public ProductoDTO() {
    }

    public ProductoDTO(String uuidCodigo) {
        this.uuidCodigo = uuidCodigo;
    }

    public ProductoDTO(String uuidCodigo, String nombre, String descripción, double precio, int stock) {

        this.uuidCodigo = uuidCodigo;
        this.nombre = nombre;
        this.descripción = descripción;
        this.precio = precio;
        this.stock = stock;

    }

}
