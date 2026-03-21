package com.supermercado.supermercado.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.supermercado.supermercado.dtos.ProductoDTO;
import com.supermercado.supermercado.models.Categoria;
import com.supermercado.supermercado.models.Producto;

@Component
public class ProductoMapper {

    @Autowired
    private CategoriaMapper categoriaMapper;

    public ProductoDTO toDTO(Producto producto, boolean mapCategoria) {

        ProductoDTO productoDTO = new ProductoDTO();
        productoDTO.setUuid(producto.getUuid());
        productoDTO.setNombre(producto.getNombre());
        productoDTO.setDescripción(producto.getDescripción());
        productoDTO.setPrecio(producto.getPrecio());
        productoDTO.setStock(producto.getStock());
        if (mapCategoria) {
            productoDTO.setCategoria(categoriaMapper.toDTO(producto.getCategoria()));
        }

        return productoDTO;

    }

    public Producto getProducto(ProductoDTO productoDTO, Categoria categoria) {

        Producto producto = new Producto();
        producto.setUuid(productoDTO.getUuid());
        producto.setNombre(productoDTO.getNombre());
        producto.setDescripción(productoDTO.getDescripción());
        producto.setPrecio(productoDTO.getPrecio());
        producto.setStock(productoDTO.getStock());
        producto.setCategoria(categoria);

        return producto;

    }

}
