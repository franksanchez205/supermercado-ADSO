package com.supermercado.supermercado.dtos;

import com.supermercado.supermercado.models.Producto;
import com.supermercado.supermercado.models.Venta;

import lombok.Data;

@Data
public class DetalleVentaDTO {

    private String uuid;
    private int cantidad;

    private double precioUnitario;

    private Producto producto;

    private Venta venta;

    public DetalleVentaDTO() {
    }

    public DetalleVentaDTO(String uuid, int cantidad, double precioUnitario) {

        this.uuid = uuid;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }
}