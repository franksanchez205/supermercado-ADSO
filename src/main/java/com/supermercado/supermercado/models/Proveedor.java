package com.supermercado.supermercado.models;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.PrePersist;
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
@SQLDelete(sql = "UPDATE proveedor SET deleted = true WHERE id=?")
@SQLRestriction("deleted = false")
public class Proveedor {

    @Id
    @GeneratedValue
    private Long id;

    @Column(updatable = false, nullable = false, unique = true, length = 36)
    private String uuidCodigo;

    @Column(updatable = false, nullable = false, unique = true, length = 36)
    private String nit;
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

    public Proveedor(Long id, String uuidCodigo, String nit, String nombre, String dirección, String telefono) {
        this.id = id;
        this.uuidCodigo = uuidCodigo;
        this.nit = nit;
        this.nombre = nombre;
        this.dirección = dirección;
        this.telefono = telefono;
    }

    @PrePersist
    public void initializeUuid() {
        this.setNit(UUID.randomUUID().toString());

    }
}
