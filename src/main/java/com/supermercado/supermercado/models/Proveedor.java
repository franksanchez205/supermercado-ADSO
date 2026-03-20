package com.supermercado.supermercado.models;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
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

    @CreatedDate
    @Column(updatable = false, columnDefinition = "timestamp without time zone NOT NULL DEFAULT CURRENT_TIMESTAMP")
    private Date createDate;

    @LastModifiedDate
    @Column(updatable = false, columnDefinition = "timestamp without time zone NOT NULL DEFAULT CURRENT_TIMESTAMP")
    private Date notifieldDate;

    @Column(columnDefinition = "BOOLEAN NOT NULL DEFAULT '0'")
    private boolean deleted;

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
