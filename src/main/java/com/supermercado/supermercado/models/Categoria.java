package com.supermercado.supermercado.models;

import java.util.Date;
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

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.EntityListeners;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
@SQLDelete(sql = "UPDATE categoria SET deleted = true WHERE id=?")
@SQLRestriction("deleted = false")
public class Categoria {
    @Id
    @GeneratedValue
    private Long id;

    @Column(updatable = false, nullable = false, unique = true, length = 36)
    private String uuidCodigo;

    private String nombre;
    private String descripcion;

    @CreatedDate
    @Column(updatable = false, columnDefinition = "timestamp without time zone NOT NULL DEFAULT CURRENT_TIMESTAMP")
    private Date createDate;

    @LastModifiedDate
    @Column(updatable = false, columnDefinition = "timestamp without time zone NOT NULL DEFAULT CURRENT_TIMESTAMP")
    private Date notifieldDate;

    @OneToMany(mappedBy = "categoria", cascade = CascadeType.REMOVE)
    private List<Producto> producList;

    @Column(columnDefinition = "BOOLEAN NOT NULL DEFAULT '0'")
    private boolean deleted;

    public Categoria() {
    }

    public Categoria(String uuidCodigo) {
        this.uuidCodigo = uuidCodigo;

    }

    public Categoria(Long id, String uuidCodigo, String nombre, String descripción) {
        this.id = id;
        this.uuidCodigo = uuidCodigo;
        this.nombre = nombre;
        this.descripcion = descripción;
    }

    @PrePersist
    public void initializeUuid() {
        this.setUuidCodigo(UUID.randomUUID().toString());

    }

}
