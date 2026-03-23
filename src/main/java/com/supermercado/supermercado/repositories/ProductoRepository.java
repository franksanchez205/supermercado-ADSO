package com.supermercado.supermercado.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.supermercado.supermercado.models.DetalleVenta;
import com.supermercado.supermercado.models.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    @Query("SELECT p FROM Producto p WHERE p.uuidCodigo = ?1")
    Optional<Producto> findAllByUuIdProducto(String uuidCodigo);

    @Query("SELECT p FROM Producto p WHERE p.uuidCodigo = ?1")
    Optional<Producto> findByUuIdProducto(String uuidCodigo);

    @Query("SELECT p FROM Producto p WHERE p.uuidCodigo = ?1")
    Producto findByUuProducto(String uuidCodigo);

    @Query("SELECT p FROM DetalleVenta p WHERE p.producto.id = ?1")
    DetalleVenta findByProductoId(Long id);

    @Query("SELECT p FROM Producto p JOIN p.proveedorList prov WHERE prov.nit = ?1")
    List<Producto> findByProveedores_Nit(String nit);

    // Spring aplicará el filtro de 'deleted = false' automáticamente por la entidad
    List<Producto> findByCategoriaId(String uuid);

    @Query("SELECT p FROM Producto p WHERE p.categoria.uuidCodigo = ?1")
    List<Producto> findByCategoriaUuid(String uuid);

}