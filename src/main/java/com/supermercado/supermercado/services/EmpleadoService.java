package com.supermercado.supermercado.services;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.supermercado.supermercado.dtos.EmpleadoDTO;
import com.supermercado.supermercado.mapper.EmpleadoMapper;
import com.supermercado.supermercado.models.Empleado;
import com.supermercado.supermercado.repositories.EmpleadoRepository;

@Service
public class EmpleadoService {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Autowired
    private EmpleadoMapper empleadoMapper;

    public List<EmpleadoDTO> listarPorCargo(String cargo) {

        return empleadoRepository.findByCargo(cargo)
                .stream()
                .map(empleado -> empleadoMapper.toDTO(empleado))
                .collect(Collectors.toList());
    }

    public List<EmpleadoDTO> listarPorRangoFecha(Date inicio, Date fin) {
        return empleadoRepository.findByFechaIngresoBetween(inicio, fin)
                .stream()
                .map(empleado -> empleadoMapper.toDTO(empleado))
                .collect(Collectors.toList());
    }

    public EmpleadoDTO saveEmpleado(EmpleadoDTO empleadoDTO) {

        if (empleadoRepository.findByUuid(empleadoDTO.getUuidCodigo()) != null) {
            throw new RuntimeException("El empleado ya existe");
        }

        Empleado empleado = empleadoMapper.getEmpleado(empleadoDTO);
        return empleadoMapper.toDTO(empleadoRepository.save(empleado));
    }
}