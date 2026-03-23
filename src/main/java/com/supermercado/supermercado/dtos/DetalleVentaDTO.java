package com.supermercado.supermercado.dtos;

import com.supermercado.supermercado.models.Producto;
import com.supermercado.supermercado.models.Venta;

import lombok.Data;

@Data
public class DetalleVentaDTO {

    private String uuidCodigo;

    private int cantidad;

    private double precioUnitario;

    private Producto producto;

    private Venta venta;

    public DetalleVentaDTO() {
    }

    public DetalleVentaDTO(String uuidCodigo, int cantidad, double precioUnitario) {

        this.uuidCodigo = uuidCodigo;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }
}