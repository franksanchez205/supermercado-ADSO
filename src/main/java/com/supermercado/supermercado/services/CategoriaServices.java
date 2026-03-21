package com.supermercado.supermercado.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.supermercado.supermercado.dtos.CategoriaDTO;
import com.supermercado.supermercado.exceptions.NotFoundException;
import com.supermercado.supermercado.mapper.CategoriaMapper;
import com.supermercado.supermercado.dtos.ProductoDTO;
import com.supermercado.supermercado.mapper.ProductoMapper;
import com.supermercado.supermercado.models.Categoria;
import com.supermercado.supermercado.models.Producto;
import com.supermercado.supermercado.repositories.CategoriaRepository;
import com.supermercado.supermercado.repositories.ProductoRepository;

@Service
public class CategoriaServices {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private CategoriaMapper categoriaMapper;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private ProductoMapper productoMapper;

    public List<CategoriaDTO> getAllCategorias() {

        return categoriaRepository
                .findAll()
                .stream()
                .map(categoria -> categoriaMapper.toDTO(categoria))
                .collect(Collectors.toList());
    }

    public CategoriaDTO getCategorias(String categoriaUuId) {

        Categoria categoria = categoriaRepository.findOneBYUuId(categoriaUuId);

        if (categoria == null) {
            throw new NotFoundException("categoria", categoriaUuId);
        }

        return categoriaMapper.toDTO(categoria);
    }

    public CategoriaDTO saveCategoria(CategoriaDTO categoriaDTO) {

        Categoria categoria = categoriaMapper.getCategoria(categoriaDTO);

        return categoriaMapper.toDTO(categoriaRepository.save(categoria));
    }

    public CategoriaDTO updateCategoria(CategoriaDTO categoriaDTO) {

        Categoria example1 = new Categoria(categoriaDTO.getUuid());

        Optional<Categoria> categoriaOptional = categoriaRepository.findOne(Example.of(example1));

        if (categoriaOptional.isEmpty()) {

            throw new NotFoundException("categoria NO ENCONTRADA", categoriaDTO.getUuid());
        }
        Categoria categoria = categoriaOptional.get();

        categoria.setNombre(categoriaDTO.getNombre());
        categoria.setDescripción(categoriaDTO.getDescripción());

        categoria = categoriaRepository.save(categoria);

        return categoriaMapper.toDTO(categoria);

    }

    public CategoriaDTO deleteCategoria(String categoriaUuId) {

        Categoria example1 = new Categoria(categoriaUuId);

        Optional<Categoria> categoriaOptional = categoriaRepository.findOne(Example.of(example1));
        if (categoriaOptional.isEmpty()) {

            throw new NotFoundException("categoria", categoriaUuId);
        }
        Categoria categoria = categoriaOptional.get();

        categoriaRepository.delete(categoria);

        return categoriaMapper.toDTO(categoria);
    }

    public List<ProductoDTO> getProductos(String categoriaUuid) {
        // 1. Buscar la categoría por UUID (esto está bien)
        Categoria categoria = categoriaRepository.findOneBYUuId(categoriaUuid);
        if (categoria == null) {

            throw new NotFoundException("categoria NO ENCONTRADA", categoriaUuid);
        }
        // 2. Buscar productos usando el ID de la categoría encontrada
        // Aquí el filtro de Soft Delete se aplica solo y de forma limpia
        List<Producto> productos = productoRepository.findByCategoriaUuid(categoria.getUuid());

        // 3. Mapear a DTO
        return productos.stream()
                .map(producto -> productoMapper.toDTO(producto, true))
                .collect(Collectors.toList());
    }
}