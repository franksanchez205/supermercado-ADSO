package com.supermercado.supermercado.models;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import lombok.Data;


@Entity
@Data
public class Empleado {

    @Id
    @GeneratedValue
    private Long id;

    @Column(updatable = false, nullable = false, unique = true, length = 36)
    private String uuid;
    private String nombre;
    private String cedula;
    private LocalDate fechaIngreso;

    @Enumerated(EnumType.STRING)
    private Cargo cargo;

    private double salario;

    @OneToMany(mappedBy = "empleado", cascade = CascadeType.REMOVE)
    private List<Venta> ventaList;

    public Empleado() {
    }

    public Empleado(Long id, String uuid, String nombre, String cedula, LocalDate fechaIngreso, Cargo cargo,
            double salario) {
        this.id = id;
        this.uuid = uuid;
        this.nombre = nombre;
        this.cedula = cedula;
        this.fechaIngreso = fechaIngreso;
        this.cargo = cargo;
        this.salario = salario;
    }

    @PrePersist
    public void initializeUuid() {
        this.setUuid(UUID.randomUUID().toString());

    }
}
