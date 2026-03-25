package com.supermercado.supermercado.dtos;

import com.supermercado.supermercado.models.Producto;
import com.supermercado.supermercado.models.Venta;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class DetalleVentaDTO {

    private String uuidCodigo;

    @NotNull(message = "La cantidad es requerida")
    @Size(min = 1, max = 100, message = "La cantidad debe tener entre 1 y 100 caracteres")
    private int cantidad;

    @NotNull(message = "El precio unitario es requerido")
    @Size(min = 1, max = 100, message = "El precio unitario debe tener entre 1 y 100 caracteres")
    private double precioUnitario;

    @Size(min = 1, max = 100, message = "El producto debe tener entre 1 y 100 caracteres")
    private Producto producto;

    @Size(min = 1, max = 100, message = "La venta debe tener entre 1 y 100 caracteres")
    private Venta venta;

    public DetalleVentaDTO() {
    }

    public DetalleVentaDTO(String uuidCodigo, int cantidad, double precioUnitario) {

        this.uuidCodigo = uuidCodigo;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }
}