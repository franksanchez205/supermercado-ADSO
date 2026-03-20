package com.supermercado.supermercado.models;

import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
// SQLDelete se mantiene igual
@SQLDelete(sql = "UPDATE categoria SET deleted = true WHERE id=?")
// CAMBIO AQUÍ: @Where se reemplaza por @SQLRestriction
@SQLRestriction("deleted = false")
public class Venta {

    @Id
    @GeneratedValue
    private Long id;

    @Column(updatable = false, nullable = false, unique = true, length = 36)
    private String uuid;

    private Date fecha;
    private double subTotal;
    private double iva;
    private double total;

    @ManyToOne(fetch = FetchType.LAZY)
    private Empleado empleado;

    @OneToMany(mappedBy = "venta", cascade = CascadeType.REMOVE)
    private List<DetalleVenta> detalleVentaList;

    @CreatedDate
    @Column(updatable = false, columnDefinition = "timestamp without time zone NOT NULL DEFAULT CURRENT_TIMESTAMP")
    private Date createDate;

    @LastModifiedDate
    @Column(updatable = false, columnDefinition = "timestamp without time zone NOT NULL DEFAULT CURRENT_TIMESTAMP")
    private Date notifieldDate;

    @Column(columnDefinition = "BOOLEAN NOT NULL DEFAULT '0'")
    private boolean deleted;

    public Venta() {
    }

    public Venta(Long id, String uuid, Date fecha, double subTotal, double iva, double total) {
        this.id = id;
        this.uuid = uuid;
        this.fecha = fecha;
        this.subTotal = subTotal;
        this.iva = iva;
        this.total = total;
    }

}