package com.supermercado.supermercado.models;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import lombok.Data;

@Entity
@Data
public class Categoria {
    @Id
    @GeneratedValue
    private Long id;

    @Column(updatable = false, nullable = false, unique = true, length = 36)
    private String uuid;
    private String nombre;
    private String descripción;

    @OneToMany(mappedBy = "categoria", cascade = CascadeType.REMOVE)
    private List<Producto> producList;

    public Categoria() {
    }

    public Categoria(String uuid) {
        this.uuid = uuid;

    }

    public Categoria(Long id, String uuid, String nombre, String descripción) {
        this.id = id;
        this.uuid = uuid;
        this.nombre = nombre;
        this.descripción = descripción;
    }

    @PrePersist
    public void initializeUuid() {
        this.setUuid(UUID.randomUUID().toString());

    }

}
