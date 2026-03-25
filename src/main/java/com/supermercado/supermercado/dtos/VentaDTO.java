package com.supermercado.supermercado.dtos;

import java.util.Date;
import java.util.List;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class VentaDTO {

    private String uuidCodigo;

    @NotNull(message = "La fecha es requerida")

    private Date fecha;

    @NotNull(message = "El subtotal es requerido")
    private double subTotal;

    @NotNull(message = "El iva es requerido")
    private double iva;

    @NotNull(message = "El total es requerido")
    private double total;

    private EmpleadoDTO empleado;

    private List<DetalleVentaDTO> detalleVentaList;

    public VentaDTO() {
    }

    public VentaDTO(String uuidCodigo, Date fecha, double subTotal, double iva, double total) {

        this.uuidCodigo = uuidCodigo;
        this.fecha = fecha;
        this.subTotal = subTotal;
        this.iva = iva;
        this.total = total;
    }
}
