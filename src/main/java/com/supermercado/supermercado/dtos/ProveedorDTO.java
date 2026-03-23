package com.supermercado.supermercado.dtos;

import lombok.Data;

@Data
public class ProveedorDTO {

    private int nit;
    private String uuid;
    private String nombre;
    private String telefono;
    private String direccion;

    public ProveedorDTO() {
    }

    public ProveedorDTO(int nit, String uuid, String nombre, String telefono, String direccion) {

        this.nit = nit;
        this.uuid = uuid;
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
    }

}
