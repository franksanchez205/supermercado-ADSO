package com.supermercado.supermercado.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.supermercado.supermercado.dtos.ProductoDTO;
import com.supermercado.supermercado.services.ProveedorServices;

@RestController
@RequestMapping("/almacen")
public class AlmacenController {

    @Autowired
    private ProveedorServices proveedorServices;

    @PostMapping()
    public ResponseEntity<ProductoDTO> entradaAlmacen(
            @PathVariable Long productoId,
            @PathVariable Long proveedorId,
            @PathVariable int cantidad) {

        ProductoDTO productoActualizado = proveedorServices.entradaAlmacen(productoId, proveedorId, cantidad);
        return ResponseEntity.ok(productoActualizado);
    }
}