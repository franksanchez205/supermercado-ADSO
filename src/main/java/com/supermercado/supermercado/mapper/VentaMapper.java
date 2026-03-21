package com.supermercado.supermercado.mapper;

import org.springframework.stereotype.Component;

import com.supermercado.supermercado.dtos.VentaDTO;
import com.supermercado.supermercado.models.Venta;

@Component
public class VentaMapper {

    public VentaDTO toDTO(Venta venta) {
        VentaDTO ventaDTO = new VentaDTO();
        ventaDTO.setUuid(venta.getUuid());
        ventaDTO.setFecha(venta.getFecha());
        ventaDTO.setSubTotal(venta.getSubTotal());
        ventaDTO.setIva(venta.getIva());
        ventaDTO.setTotal(venta.getTotal());
        return ventaDTO;
    }

    public Venta toVenta(VentaDTO ventaDTO) {
        Venta venta = new Venta();
        venta.setUuid(ventaDTO.getUuid());
        venta.setFecha(ventaDTO.getFecha());
        venta.setSubTotal(ventaDTO.getSubTotal());
        venta.setIva(ventaDTO.getIva());
        venta.setTotal(ventaDTO.getTotal());
        return venta;
    }
}
