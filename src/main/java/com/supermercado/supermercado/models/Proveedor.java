package com.supermercado.supermercado.models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;


@Entity
@Data
public class Proveedor {

    @Id
    @GeneratedValue
    private Long id;

    @Column(updatable = false, nullable = false, unique = true, length = 36)
    private String uuid;

    private int nit;
    private String nombre;
    private String dirección;
    private String telefono;

    @ManyToMany(mappedBy = "proveedorList")
    private List<Producto> productoList;

    public Proveedor() {
    }

    public Proveedor(Long id, String uuid, int nit, String nombre, String dirección, String telefono) {
        this.id = id;
        this.uuid = uuid;
        this.nit = nit;
        this.nombre = nombre;
        this.dirección = dirección;
        this.telefono = telefono;
    }

}
