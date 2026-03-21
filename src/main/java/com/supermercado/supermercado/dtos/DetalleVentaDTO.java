package com.supermercado.supermercado.dtos;

import com.supermercado.supermercado.models.Producto;
import com.supermercado.supermercado.models.Venta;

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

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

}