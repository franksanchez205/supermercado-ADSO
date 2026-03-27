package com.supermercado.supermercado.dtos;

import java.sql.Date;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import lombok.Data;

@Data
public class EmpleadoDTO {

    private String uuidCodigo;

    @Size(min = 3, max = 50, message = "El nombre debe tener entre 3 y 50 caracteres")
    private String nombre;


    @Size(min = 3, max = 50, message = "La cedula debe tener entre 3 y 50 caracteres")
    private String cedula;

    private Date createDate;
    private String cargo;
    private double salario;

    public EmpleadoDTO() {
    }

    public EmpleadoDTO(String uuidCodigo, String nombre, String cedula, Date createDate, String cargo,
            double salario) {

        this.uuidCodigo = uuidCodigo;
        this.nombre = nombre;
        this.cedula = cedula;
        this.createDate = createDate;
        this.cargo = cargo;
        this.salario = salario;
    }
}