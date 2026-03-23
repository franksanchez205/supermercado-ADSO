package com.supermercado.supermercado.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.supermercado.supermercado.dtos.EmpleadoDTO;
import com.supermercado.supermercado.services.EmpleadoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/empleado")
public class EmpleadoController {

    @Autowired
    private EmpleadoService empleadoService;

    @GetMapping("/cargo/{cargo}")
    public ResponseEntity<List<EmpleadoDTO>> listarPorCargo(
            @PathVariable String cargo) {
        return ResponseEntity.ok(empleadoService.listarPorCargo(cargo));
    }

    @GetMapping("/fecha-ingreso")
    public ResponseEntity<List<EmpleadoDTO>> listarPorRangoFecha(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fin) {
        return ResponseEntity.ok(empleadoService.listarPorRangoFecha(inicio, fin));
    }

    @PostMapping
    public ResponseEntity<EmpleadoDTO> crearEmpleado(@Valid @RequestBody EmpleadoDTO empleadoDTO) {

        return ResponseEntity.status(HttpStatus.CREATED).body(empleadoService.saveEmpleado(empleadoDTO));
    }

}
