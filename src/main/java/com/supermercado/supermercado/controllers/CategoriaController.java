package com.supermercado.supermercado.controllers;

import java.util.List;
import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.HttpStatus;
import com.supermercado.supermercado.dtos.CategoriaDTO;
import com.supermercado.supermercado.dtos.ProductoDTO;
import com.supermercado.supermercado.services.CategoriaServices;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/categorias")
public clas {

    @Autowired
    private CategoriaServices categoriaServices;

    @GetMapping
    public List<CategoriaDTO> getAllCategorias() {
        return categoriaServices.getAllCategorias();
    }

    @GetMapping("/{categoriaUuId}")
    public ResponseEntity<CategoriaDTO> getCategorias(@Valid @PathVariable String categoriaUuId) {
        return ResponseEntity.ok(categoriaServices.getCategorias(categoriaUuId));
    }

    @PostMapping()
    public ResponseEntity<CategoriaDTO> saveCategoria(@Valid @RequestBody CategoriaDTO categoriaDTO) {

        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaServices.saveCategoria(categoriaDTO));
    }

    @PutMapping("/{categoriaUuId}")
    public CategoriaDTO updateCategoria(@Valid @PathVariable String categoriaUuId, @RequestBody CategoriaDTO categoriaDTO) {

        return categoriaServices.updateCategoria(categoriaUuId, categoriaDTO);
    }

    @DeleteMapping("/{categoriaUuId}")
    public ResponseEntity<CategoriaDTO> deleteCategoria(@Valid @PathVariable String categoriaUuId) {
        return ResponseEntity.ok(categoriaServices.deleteCategoria(categoriaUuId));
    }

    @GetMapping("/{categoriaUuId}/productos")
    public ResponseEntity<List<ProductoDTO>> getProductos(@Valid @PathVariable String categoriaUuId) {
        return ResponseEntity.ok(categoriaServices.getProductos(categoriaUuId));
    }

}