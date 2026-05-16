package com.supermercado.supermercado.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import com.supermercado.supermercado.dtos.CategoriaDTO;
import com.supermercado.supermercado.dtos.ProductoDTO;
import com.supermercado.supermercado.security.Permisos;
import com.supermercado.supermercado.security.Roles;
import com.supermercado.supermercado.services.AuthorizationService;
import com.supermercado.supermercado.services.CategoriaServices;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaServices categoriaServices;

    @Autowired
    private AuthorizationService authorizationService;

    @GetMapping
    public List<CategoriaDTO> getAllCategorias() {
        return categoriaServices.getAllCategorias();
    }

    @GetMapping("/{categoriaUuId}")
    public ResponseEntity<CategoriaDTO> getCategorias(@Valid @PathVariable String categoriaUuId) {
        return ResponseEntity.ok(categoriaServices.getCategorias(categoriaUuId));
    }

    @PostMapping()
    public ResponseEntity<CategoriaDTO> saveCategoria(@Valid @RequestBody CategoriaDTO categoriaDTO, HttpServletRequest request) {

        authorizationService.requirePermission(request, Permisos.USERS_CREATE);
      

        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaServices.saveCategoria(categoriaDTO));
    }

    @PutMapping("/{categoriaUuId}")
    public CategoriaDTO updateCategoria(@Valid @PathVariable String categoriaUuId,
        
            @RequestBody CategoriaDTO categoriaDTO, HttpServletRequest request) {

    authorizationService.requirePermission(request, Permisos.USERS_CREATE);

        return categoriaServices.modificarCategoria(categoriaUuId, categoriaDTO);
    }

    @DeleteMapping("/{categoriaUuId}")
    public ResponseEntity<CategoriaDTO> deleteCategoria(@Valid @PathVariable String categoriaUuId) {
        return ResponseEntity.ok(categoriaServices.eliminarCategoria(categoriaUuId));
    }

    @GetMapping("/{categoriaUuId}/productos")
    public ResponseEntity<List<ProductoDTO>> getProductos(@Valid @PathVariable String categoriaUuId) {
        return ResponseEntity.ok(categoriaServices.getProductos(categoriaUuId));
    }

}