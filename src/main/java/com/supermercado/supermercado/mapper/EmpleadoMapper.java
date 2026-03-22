package com.supermercado.supermercado.mapper;

import org.springframework.stereotype.Component;

import com.supermercado.supermercado.dtos.EmpleadoDTO;
import com.supermercado.supermercado.models.Empleado;

@Component
public class EmpleadoMapper {

    public EmpleadoDTO toDTO(Empleado empleado) {
        EmpleadoDTO empleadoDTO = new EmpleadoDTO();
        empleadoDTO.setUuid(empleado.getUuid());
        empleadoDTO.setNombre(empleado.getNombre());
        empleadoDTO.setCedula(empleado.getCedula());
        empleadoDTO.setCargo(empleado.getCargo().name());

        empleadoDTO.setSalario(empleado.getSalario());
        return empleadoDTO;
    }

    public Empleado getEmpleado(EmpleadoDTO empleadoDTO) {
        Empleado empleado = new Empleado();
        empleado.setUuid(empleadoDTO.getUuid());
        empleado.setNombre(empleadoDTO.getNombre());
        empleado.setCedula(empleadoDTO.getCedula());
        empleado.setCargo(Empleado.Cargo.valueOf(empleadoDTO.getCargo().toUpperCase()));
        empleado.setSalario(empleadoDTO.getSalario());
        return empleado;
    }

}
