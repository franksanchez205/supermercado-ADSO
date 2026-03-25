package com.supermercado.supermercado.services;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.supermercado.supermercado.dtos.EmpleadoDTO;
import com.supermercado.supermercado.dtos.VentaDTO;
import com.supermercado.supermercado.exceptions.NotFoundException;
import com.supermercado.supermercado.mapper.EmpleadoMapper;
import com.supermercado.supermercado.mapper.VentaMapper;
import com.supermercado.supermercado.models.Empleado;
import com.supermercado.supermercado.models.Venta;
import com.supermercado.supermercado.repositories.EmpleadoRepository;

@Service
public class EmpleadoService {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Autowired
    private VentaService ventaService;

    @Autowired
    private EmpleadoMapper empleadoMapper;

    @Autowired
    private VentaMapper ventaMapper;

    @Autowired
    private Venta venta;

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

    public EmpleadoDTO eliminarEmpleado(String empleadoUuId) {
        Empleado empleado = empleadoRepository.findByUuid(empleadoUuId);
        if (empleado == null) {
            throw new NotFoundException("Empleado NO ENCONTRADO", empleadoUuId);
        }
        EmpleadoDTO response = empleadoMapper.toDTO(empleado);
        empleadoRepository.delete(empleado);
        return response;
    }

    public EmpleadoDTO modificarEmpleado(String empleadoUuId, EmpleadoDTO empleadoDTO) {
        Empleado empleado = empleadoRepository.findByUuid(empleadoUuId);
        if (empleado == null) {
            throw new NotFoundException("Empleado NO ENCONTRADO", empleadoUuId);
        }
        empleado.setNombre(empleadoDTO.getNombre());
        empleado.setSalario(empleadoDTO.getSalario());
        return empleadoMapper.toDTO(empleadoRepository.save(empleado));
    }

    public EmpleadoDTO getEmpleado(String empleadoUuId) {
        Empleado empleado = empleadoRepository.findByUuid(empleadoUuId);
        if (empleado == null) {
            throw new NotFoundException("Empleado NO ENCONTRADO", empleadoUuId);
        }
        return empleadoMapper.toDTO(empleado);
    }

    public EmpleadoDTO realizarVenta(String empleadoUuId, VentaDTO ventaDTO) {
        Empleado empleado = empleadoRepository.findByUuid(empleadoUuId);
        if (empleado == null) {
            throw new NotFoundException("Empleado NO ENCONTRADO", empleadoUuId);
        }
        VentaDTO venta = ventaService.saveVenta(ventaDTO);
        empleado.getVentaList().add(ventaMapper.toVenta(venta));
        return empleadoMapper.toDTO(empleado);

    }

}