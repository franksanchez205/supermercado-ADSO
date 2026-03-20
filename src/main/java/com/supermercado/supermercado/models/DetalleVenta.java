package com.supermercado.supermercado.models;

import org.hibernate.annotations.SQLRestriction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import jakarta.persistence.EntityListeners;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
// SQLDelete se mantiene igual
@SQLDelete(sql = "UPDATE categoria SET deleted = true WHERE id=?")
// CAMBIO AQUÍ: @Where se reemplaza por @SQLRestriction
@SQLRestriction("deleted = false")
public class DetalleVenta {

    @Id
    @GeneratedValue
    private Long id;

    @Column(updatable = false, nullable = false, unique = true, length = 36)
    private String uuid;
    private int cantidad;
    private double precioUnitario;

    @ManyToOne(fetch = FetchType.LAZY)
    private Venta venta;

    @ManyToOne(fetch = FetchType.LAZY)
    private Producto producto;

    public DetalleVenta() {
    }

    public DetalleVenta(Long id, String uuid, int cantidad, double precioUnitario) {
        this.id = id;
        this.uuid = uuid;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }


}
