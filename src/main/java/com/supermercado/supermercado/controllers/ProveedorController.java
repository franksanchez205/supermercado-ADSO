package com.supermercado.supermercado.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.supermercado.supermercado.dtos.ProductoDTO;
import com.supermercado.supermercado.dtos.ProveedorDTO;
import com.supermercado.supermercado.services.ProveedorServices;

@RestController
@RequestMapping("/proveedor")
public class ProveedorController {

    @Autowired
    private ProveedorServices proveedorServices;

    @GetMapping
    public List<ProveedorDTO> getAllProveedores() {
        return proveedorServices.getAllProveedores();
    }

    @PostMapping
    public ProveedorDTO saveProveedor(@RequestBody ProveedorDTO proveedorDTO) {
        return proveedorServices.saveProveedor(proveedorDTO);
    }

    @PutMapping("/{proveedorUuId}")
    public ProveedorDTO updateProveedor(@PathVariable String proveedorUuId, @RequestBody ProveedorDTO proveedorDTO) {
        return proveedorServices.updateProveedor(proveedorUuId, proveedorDTO);
    }

    @DeleteMapping("/{proveedorUuId}")
    public ProveedorDTO deleteProveedor(@PathVariable String proveedorUuId) {
        return proveedorServices.deleteProveedor(proveedorUuId);
    }

    @GetMapping("/{proveedorUuId}/productos")
    public List<ProductoDTO> getProductosByProveedor(@PathVariable String proveedorUuId) {
        return proveedorServices.getProductosByProveedor(proveedorUuId);
    }

    @PostMapping("/{proveedorUuId}/productos/{productoId}/cantidad/{cantidad}")
    public ProductoDTO entradaAlmacen(@PathVariable String proveedorUuId, @PathVariable Long productoId,
            @PathVariable Integer cantidad) {

        return proveedorServices.entradaAlmacen(productoId, productoId, cantidad);
    }

    @PostMapping("/{proveedorUuId}/productos/{productoId}/cantidad/{cantidad}")
    public ProductoDTO salidaAlmacen(@PathVariable String proveedorUuId, @PathVariable Long productoId,
            @PathVariable Integer cantidad) {

        return proveedorServices.salidaAlmacen(productoId, cantidad);
    }
}
