package com.supermercado.supermercado.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.supermercado.supermercado.models.Proveedor;

@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor, Long> {

    @Query("SELECT p FROM Proveedor p WHERE p.uuid = ?1")
    Optional<Proveedor> findOneByUuid(String uuid);

    @Query("SELECT p FROM Proveedor p WHERE p.uuid = ?1")
    Proveedor findByUuid(String uuid);

}