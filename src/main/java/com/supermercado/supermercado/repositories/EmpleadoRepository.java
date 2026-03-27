package com.supermercado.supermercado.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.supermercado.supermercado.models.Empleado;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {

    @Query("SELECT e FROM Empleado e WHERE e.cargo = ?1")
    List<Empleado> findByCargo(String cargo);

    @Query("SELECT e FROM Empleado e WHERE e.uuidCodigo = ?1")
    Empleado findByUuid(String uuid);

    @Query("SELECT e FROM Empleado e WHERE e.createDate BETWEEN ?1 AND ?2")
    List<Empleado> findByFechaIngresoBetween(Date inicio, Date fin);
}