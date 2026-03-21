package com.supermercado.supermercado.dtos;

import java.util.Date;
import java.util.List;

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

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public double getIva() {
        return iva;
    }

    public void setIva(double iva) {
        this.iva = iva;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public List<DetalleVentaDTO> getDetalleVentaList() {
        return detalleVentaList;
    }

    public void setDetalleVentaList(List<DetalleVentaDTO> detalleVentaList) {
        this.detalleVentaList = detalleVentaList;
    }

    public EmpleadoDTO getEmpleado() {
        return empleado;
    }

    public void setEmpleado(EmpleadoDTO empleado) {
        this.empleado = empleado;
    }

}
