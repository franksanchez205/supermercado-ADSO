package com.supermercado.supermercado.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.supermercado.supermercado.models.Venta;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Long> {

    @Query("SELECT v FROM Venta v WHERE v.fecha BETWEEN :inicio AND :fin")
    List<Venta> findByFechaBetween(LocalDate inicio, LocalDate fin);
}





