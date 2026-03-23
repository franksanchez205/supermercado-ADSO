package com.supermercado.supermercado.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.supermercado.supermercado.dtos.CategoriaDTO;
import com.supermercado.supermercado.dtos.ProductoDTO;
import com.supermercado.supermercado.mapper.ProductoMapper;
import com.supermercado.supermercado.exceptions.NotFoundException;
import com.supermercado.supermercado.models.Categoria;
import com.supermercado.supermercado.models.Producto;
import com.supermercado.supermercado.repositories.CategoriaRepository;
import com.supermercado.supermercado.repositories.ProductoRepository;


@Service
public class ProductoServices {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private ProductoMapper productoMapper;

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<ProductoDTO> getAllProductos() {
        return productoRepository.findAll()
                .stream()
                .map(producto -> productoMapper.toDTO(producto, true))
                .collect(Collectors.toList());
    }

    public ProductoDTO getALLProducto(String productoUuId) {

        Optional<Producto> optionalProducto = productoRepository.findAllByUuIdProducto(productoUuId);

        if (optionalProducto == null) {
            throw new NotFoundException("producto", productoUuId);
        }

        Producto producto = optionalProducto.get();

        return productoMapper.toDTO(producto, true);
    }

    public ProductoDTO saveProducto(ProductoDTO productoDTO) {

        CategoriaDTO categoriaDTO = productoDTO.getCategoria();

        if (categoriaDTO == null) {

            throw new NotFoundException("Categoria NO ENCONTRADA", null);
        }

        Categoria categoria = categoriaRepository.findOneBYUuId(categoriaDTO.getUuidCodigo());

        if (categoria == null) {

            throw new NotFoundException("categoria NO ENCONTRADA: ", categoriaDTO.getUuidCodigo());
        }

        Producto producto = productoMapper.getProducto(productoDTO, categoria);
        productoRepository.save(producto);

        return productoMapper.toDTO(producto, true);
    }

    public ProductoDTO modificarProducto(String productoUuId, ProductoDTO productoDTO) {

        Producto producto = productoRepository.findByUuProducto(productoUuId);

        if (producto == null) {
            throw new NotFoundException("Producto NO ENCONTRADO con el codigo: ", productoUuId);
        }

        producto.setNombre(productoDTO.getNombre());
        producto.setDescripción(productoDTO.getDescripción());
        producto.setPrecio(productoDTO.getPrecio());
        producto.setStock(productoDTO.getStock());

        productoRepository.save(producto);

        return productoMapper.toDTO(producto, true);
    }

    public ProductoDTO eliminarProducto(String productoUuId) {
        // 1. Buscar el producto (usando Optional de una vez)
        Producto producto = productoRepository.findByUuIdProducto(productoUuId)
                .orElseThrow(() -> new NotFoundException("Producto NO ENCONTRADO", productoUuId));

        // 2. Mapear ANTES de borrar (opcional, si quieres devolver los datos que
        // borraste)
        // Pero ojo: si la categoría no existe en DB, esto seguirá fallando.
        // Para evitarlo, el Mapper debería validar si la categoría es nula o manejar el
        // error.
        ProductoDTO response = productoMapper.toDTO(producto, true);

        // 3. Borrar
        productoRepository.delete(producto);

        return response;
    }
}