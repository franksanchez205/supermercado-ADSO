package com.supermercado.supermercado.dtos;

import lombok.Data;

@Data
public class ProveedorDTO {

    private String nit;
    private String nombre;
    private String telefono;
    private String direccion;

    public ProveedorDTO() {
    }

    public ProveedorDTO(String nit, String nombre, String telefono, String direccion) {

        this.nit = nit;
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
    }

}
