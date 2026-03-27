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
import com.supermercado.supermercado.services.ProductoServices;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoServices productoServices;

    @GetMapping
    public List<ProductoDTO> getAllProductos() {
        return productoServices.getAllProductos();
    }

    @GetMapping("/{productoUuId}")
    public ProductoDTO getProductos(@Valid @PathVariable String productoUuId) {
        return productoServices.getALLProducto(productoUuId);
    }

    @PostMapping()
    public ProductoDTO createProducto(@Valid @RequestBody ProductoDTO productoDTO) {

        return productoServices.saveProducto(productoDTO);

    }

    @PutMapping("/{productoUuId}")
    public ProductoDTO modificarProducto(@Valid @PathVariable String productoUuId,
            @RequestBody ProductoDTO productoDTO) {
        return productoServices.modificarProducto(productoUuId, productoDTO);
    }

    @DeleteMapping("/{productoUuId}")
    public ProductoDTO eliminarProducto(@Valid @PathVariable String productoUuId) {
        return productoServices.eliminarProducto(productoUuId);
    }
}
