package com.supermercado.supermercado.mapper;

import org.springframework.stereotype.Component;

import com.supermercado.supermercado.dtos.ProveedorDTO;
import com.supermercado.supermercado.models.Proveedor;

@Component
public class ProveedorMapper {

    public ProveedorDTO toDTO(Proveedor proveedor) {

        ProveedorDTO proveedorDTO = new ProveedorDTO();
        proveedorDTO.setNit(proveedor.getNit());
        proveedorDTO.setUuid(proveedor.getUuid());
        proveedorDTO.setNombre(proveedor.getNombre());
        proveedorDTO.setTelefono(proveedor.getTelefono());
        proveedorDTO.setDireccion(proveedor.getDirección());

        return proveedorDTO;

    }

    public Proveedor getProveedor(ProveedorDTO proveedorDTO) {

        Proveedor proveedor = new Proveedor();
        proveedor.setNit(proveedorDTO.getNit());
        proveedor.setUuid(proveedorDTO.getUuid());
        proveedor.setNombre(proveedorDTO.getNombre());
        proveedor.setTelefono(proveedorDTO.getTelefono());
        proveedor.setDirección(proveedorDTO.getDireccion());

        return proveedor;
    }

}
