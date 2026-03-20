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
// SQLDelete se mantiene igual
@SQLDelete(sql = "UPDATE categoria SET deleted = true WHERE id=?")
// CAMBIO AQUÍ: @Where se reemplaza por @SQLRestriction
@SQLRestriction("deleted = false")
public class Categoria {
    @Id
    @GeneratedValue
    private Long id;

    @Column(updatable = false, nullable = false, unique = true, length = 36)
    private String uuid;
    private String nombre;
    private String descripción;

    @CreatedDate
    @Column(updatable = false, columnDefinition = "timestamp without time zone NOT NULL DEFAULT CURRENT_TIMESTAMP")
    private Date createDate;

    @LastModifiedDate
    @Column(updatable = false, columnDefinition = "timestamp without time zone NOT NULL DEFAULT CURRENT_TIMESTAMP")
    private Date notifieldDate;

    @Column(columnDefinition = "BOOLEAN NOT NULL DEFAULT '0'")
    private boolean deleted;

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
