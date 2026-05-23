package com.supermercado.supermercado.controllers;

import java.util.List;

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
import com.supermercado.supermercado.security.Permisos;
import com.supermercado.supermercado.security.RequiresRole;
import com.supermercado.supermercado.security.Roles;
import com.supermercado.supermercado.services.CategoriaServices;

import jakarta.validation.Valid;

/**
 * Controlador para gestionar las categorías de productos en el supermercado. 
 * Proporciona endpoints para crear, leer, actualizar y eliminar categorías, así como para obtener
 * los productos asociados a una categoría específica. 
 * Utiliza anotaciones de seguridad para restringir el acceso a ciertos endpoints según los roles y permisos del usuario.
 */
@RestController
@RequestMapping("/categorias")
public class CategoriaController {

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

    @RequiresRole(value = {Roles.ADMINISTRADOR, Roles.CLIENTE}, permisos = {Permisos.USERS_CREATE})
    @PostMapping()
    public ResponseEntity<CategoriaDTO> saveCategoria(@Valid @RequestBody CategoriaDTO categoriaDTO) {
      

        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaServices.saveCategoria(categoriaDTO));
    }

    @RequiresRole(value = {Roles.ADMINISTRADOR}, permisos = {Permisos.USERS_DELETE})
    @PutMapping("/{categoriaUuId}")
    public CategoriaDTO updateCategoria(@Valid @PathVariable String categoriaUuId, @RequestBody CategoriaDTO categoriaDTO) {

        return categoriaServices.modificarCategoria(categoriaUuId, categoriaDTO);
    }

    @RequiresRole(value = {Roles.ADMINISTRADOR}, permisos = {Permisos.USERS_DELETE})
    @DeleteMapping("/{categoriaUuId}")
    public ResponseEntity<CategoriaDTO> deleteCategoria(@Valid @PathVariable String categoriaUuId) {
        return ResponseEntity.ok(categoriaServices.eliminarCategoria(categoriaUuId));
    }

    @GetMapping("/{categoriaUuId}/productos")
    public ResponseEntity<List<ProductoDTO>> getProductos(@Valid @PathVariable String categoriaUuId) {
        return ResponseEntity.ok(categoriaServices.getProductos(categoriaUuId));
    }

}