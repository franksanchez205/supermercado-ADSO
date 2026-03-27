package com.supermercado.supermercado.services;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.supermercado.supermercado.dtos.DetalleVentaDTO;
import com.supermercado.supermercado.dtos.VentaDTO;
import com.supermercado.supermercado.mapper.VentaMapper;
import com.supermercado.supermercado.exceptions.NotFoundException;
import com.supermercado.supermercado.models.DetalleVenta;
import com.supermercado.supermercado.models.Producto;
import com.supermercado.supermercado.models.Venta;
import com.supermercado.supermercado.repositories.ProductoRepository;
import com.supermercado.supermercado.repositories.VentaRepository;

@Service
public class VentaService {

    @Autowired
    private VentaRepository ventaRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private VentaMapper ventaMapper;

    public List<VentaDTO> listarPorRangoFecha(LocalDate inicio, LocalDate fin) {
        return ventaRepository.findByFechaBetween(inicio, fin)
                .stream()
                .map(venta -> ventaMapper.toDTO(venta))
                .collect(Collectors.toList());
    }

    public VentaDTO procesarVenta(VentaDTO request) throws BadRequestException {
        Venta venta = new Venta();
        // Setear empleado y datos iniciales...

        double acumuladoSubtotal = 0;

        for (DetalleVentaDTO item : request.getDetalleVentaList()) {
            Producto producto = productoRepository.findById(item.getProducto().getId())
                    .orElseThrow(() -> new NotFoundException("Producto no existe", null));

            // RN 2: Validar Stock Suficiente
            if (producto.getStock() < item.getCantidad()) {
                // Lanzamos excepción que Spring convertirá en 400 Bad Request
                throw new BadRequestException("Stock insuficiente para: " + producto.getNombre());
            }

            // RN 1: Restar Stock automáticamente
            producto.setStock(producto.getStock() - item.getCantidad());
            productoRepository.save(producto);

            // Cálculos para la factura
            DetalleVenta detalle = new DetalleVenta();
            detalle.setProducto(producto);
            detalle.setCantidad(item.getCantidad());
            detalle.setPrecioUnitario(producto.getPrecio());

            acumuladoSubtotal += (detalle.getPrecioUnitario() * detalle.getCantidad());
            // Agregar detalle a la lista de la venta...
        }

        // RN 3: Cálculos Automáticos
        venta.setSubTotal(acumuladoSubtotal);
        venta.setIva(acumuladoSubtotal * 0.19); // IVA 19%
        venta.setTotal(venta.getSubTotal() + venta.getIva());

        return ventaMapper.toDTO(ventaRepository.save(venta));
    }

    public VentaDTO saveVenta(VentaDTO ventaDTO) {
        Venta venta = ventaMapper.toVenta(ventaDTO);
        return ventaMapper.toDTO(ventaRepository.save(venta));
    }

}
