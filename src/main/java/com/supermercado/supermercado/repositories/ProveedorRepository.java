package com.supermercado.supermercado.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.supermercado.supermercado.models.Proveedor;

@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor, Long> {

    @Query("SELECT p FROM Proveedor p WHERE p.nit = ?1")
    Optional<Proveedor> findOneByNit(String nit);

    @Query("SELECT p FROM Proveedor p WHERE p.nit = ?1")
    Proveedor findByNit(String nit);

}