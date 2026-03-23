package com.supermercado.supermercado.dtos;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class VentaDTO {

    private String uuid;
    private Date fecha;
    private double subTotal;
    private double iva;
    private double total;

    private EmpleadoDTO empleado;

    private List<DetalleVentaDTO> detalleVentaList;

    public VentaDTO() {
    }

    public VentaDTO(String uuid, Date fecha, double subTotal, double iva, double total) {

        this.uuid = uuid;
        this.fecha = fecha;
        this.subTotal = subTotal;
        this.iva = iva;
        this.total = total;
    }
}
