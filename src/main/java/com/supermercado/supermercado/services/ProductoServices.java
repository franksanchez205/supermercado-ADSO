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

    public ProductoDTO getProducto(String productoUuId) {

        Optional<Producto> optionalProducto = productoRepository.findOneByUuid(productoUuId);

        if (optionalProducto.isEmpty()) {
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

        Categoria categoria = categoriaRepository.findOneBYUuId(categoriaDTO.getUuid());

        if (categoria == null) {

            throw new NotFoundException("categoria ", categoriaDTO.getUuid());
        }

        Producto producto = productoMapper.getProducto(productoDTO, categoria);
        productoRepository.save(producto);

        return productoMapper.toDTO(producto, true);
    }

    public ProductoDTO updateProducto(String productoUuId, ProductoDTO productoDTO) {

        Producto producto = productoRepository.findByUuid(productoUuId);

        if (producto == null) {
            throw new NotFoundException("Producto NO ENCONTRADO con uuid ", productoUuId);
        }

        producto.setNombre(productoDTO.getNombre());
        producto.setDescripción(productoDTO.getDescripción());
        producto.setPrecio(productoDTO.getPrecio());
        producto.setStock(productoDTO.getStock());

        productoRepository.save(producto);

        return productoMapper.toDTO(producto, true);
    }

    public ProductoDTO deleteProducto(String productoUuId) {

        Optional<Producto> optionalProducto = productoRepository.findOneByUuid(productoUuId);

        if (optionalProducto.isEmpty()) {
            throw new NotFoundException("Producto NO ENCONTRADO con uuid ", productoUuId);
        }

        Producto producto = optionalProducto.get();

        productoRepository.delete(producto);

        return productoMapper.toDTO(producto, true);
    }

    public List<ProductoDTO> getAllProductos() {

        return productoRepository.findAll().stream().map(producto -> productoMapper.toDTO(producto, true))
                .collect(Collectors.toList());

    }
}