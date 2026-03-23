package com.supermercado.supermercado.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.supermercado.supermercado.dtos.ProductoDTO;
import com.supermercado.supermercado.mapper.ProductoMapper;
import com.supermercado.supermercado.dtos.ProveedorDTO;
import com.supermercado.supermercado.mapper.ProveedorMapper;
import com.supermercado.supermercado.exceptions.NotFoundException;
import com.supermercado.supermercado.models.Product;
import com.supermercado.supermercado.models.Proveedor;
import com.supermercado.supermercado.repositories.ProductoRepository;
import com.supermercado.supermercado.repositories.ProveedorRepository;

@Service
public class ProveedorServices {

    @Autowired
    private ProductoMapper productoMapper;

    @Autowired
    private ProveedorMapper proveedorMapper;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private ProveedorRepository proveedorRepository;

    public List<ProveedorDTO> getAllProveedores() {

        return proveedorRepository
                .findAll()
                .stream()
                .map(proveedor -> proveedorMapper.toDTO(proveedor))
                .collect(Collectors.toList());

    }

    public ProveedorDTO saveProveedor(ProveedorDTO proveedorDTO) {

        Proveedor proveedor = proveedorMapper.getProveedor(proveedorDTO);
        proveedorRepository.save(proveedor);

        return proveedorMapper.toDTO(proveedor);
    }

    public ProveedorDTO updateProveedor(String proveedorUuId, ProveedorDTO proveedorDTO) {

        Proveedor proveedor = proveedorRepository.findByUuid(proveedorUuId);

        if (proveedor == null) {
            throw new NotFoundException("Proveedor NO ENCONTRADO con uuid ", proveedorUuId);
        }

        proveedor.setNombre(proveedorDTO.getNombre());
        proveedor.setTelefono(proveedorDTO.getTelefono());
        proveedor.setDirección(proveedorDTO.getDireccion());

        proveedorRepository.save(proveedor);

        return proveedorMapper.toDTO(proveedor);
    }

    public ProveedorDTO deleteProveedor(String proveedorUuId) {

        Optional<Proveedor> optionalProveedor = proveedorRepository.findOneByUuid(proveedorUuId);

        if (optionalProveedor.isEmpty()) {
            throw new NotFoundException("Proveedor NO ENCONTRADO con uuid ", proveedorUuId);
        }

        Proveedor proveedor = optionalProveedor.get();

        proveedorRepository.delete(proveedor);

        return proveedorMapper.toDTO(proveedor);
    }

    public List<ProductoDTO> getProductosByProveedor(String proveedorUuId) {

        return productoRepository
                .findByProveedores_Uuid(proveedorUuId)
                .stream()
                .map(producto -> productoMapper.toDTO(producto, true))
                .collect(Collectors.toList());
    }

    public ProductoDTO entradaAlmacen(Long productoId, Long proveedorId, Integer cantidad) {
        // 1. Validar que el producto existe
        Product producto = productoRepository.findById(productoId)
                .orElseThrow(() -> new NotFoundException("Producto no encontrado", productoId.toString()));

        // 2. Validar que el proveedor existe
        Proveedor proveedor = proveedorRepository.findById(proveedorId)
                .orElseThrow(() -> new NotFoundException("Proveedor no encontrado", proveedorId.toString()));

        // 3. Regla de Negocio: Sumar unidades al stock actual
        Integer nuevoStock = (producto.getStock() != 0 ? producto.getStock() : 0) + cantidad;
        producto.setStock(nuevoStock);

        // 4. (Opcional) Vincular el proveedor al producto si no existía la relación
        if (!producto.getProveedorList().contains(proveedor)) {
            producto.getProveedorList().add(proveedor);
        }

        productoRepository.save(producto);

        // 5. Retornar DTO (puedes usar tu mapper aquí)
        return productoMapper.toDTO(producto, true);
    }
}
