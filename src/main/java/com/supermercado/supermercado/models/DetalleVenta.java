package com.supermercado.supermercado.models;

import org.hibernate.annotations.SQLRestriction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import lombok.Data;

import java.util.UUID;

import org.hibernate.annotations.SQLDelete;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import jakarta.persistence.EntityListeners;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
@SQLDelete(sql = "UPDATE detalle_venta SET deleted = true WHERE id=?")
@SQLRestriction("deleted = false")
public class DetalleVenta {

    @Id
    @GeneratedValue
    private Long id;

    @Column(updatable = false, nullable = false, unique = true, length = 36)
    private String uuidCodigo;
    private int cantidad;
    private double precioUnitario;

    @ManyToOne(fetch = FetchType.LAZY)
    private Venta venta;

    @ManyToOne(fetch = FetchType.LAZY)
    private Producto producto;

    @Column(columnDefinition = "BOOLEAN NOT NULL DEFAULT '0'")
    private boolean deleted;

    public DetalleVenta() {
    }

    public DetalleVenta(Long id, String uuidCodigo, int cantidad, double precioUnitario) {
        this.id = id;
        this.uuidCodigo = uuidCodigo;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }

    @PrePersist
    public void initializeUuid() {
        this.setUuidCodigo(UUID.randomUUID().toString());

    }

}
