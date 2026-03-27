package com.supermercado.supermercado.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ProveedorDTO {

    private String uuidCodigo;

    @NotBlank(message = "El nit es requerido")
    @Size(min = 3, max = 50, message = "El nit debe tener entre 3 y 50 caracteres")
    private String nit;

    @NotBlank(message = "El nombre es requerido")
    @Size(min = 3, max = 50, message = "El nombre debe tener entre 3 y 50 caracteres")
    private String nombre;

    @NotBlank(message = "El telefono es requerido")
    @Size(min = 3, max = 50, message = "El telefono debe tener entre 3 y 50 caracteres")
    private String telefono;

    @NotBlank(message = "La direccion es requerida")
    @Size(min = 3, max = 100, message = "La direccion debe tener entre 3 y 100 caracteres")
    private String direccion;

    public ProveedorDTO() {
    }

    public ProveedorDTO(String uuidCodigo, String nit, String nombre, String telefono, String direccion) {

        this.uuidCodigo = uuidCodigo;
        this.nit = nit;
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
    }

}
