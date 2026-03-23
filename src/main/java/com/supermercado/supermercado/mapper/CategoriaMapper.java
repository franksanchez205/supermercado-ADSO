package com.supermercado.supermercado.mapper;

import org.springframework.stereotype.Component;

import com.supermercado.supermercado.dtos.CategoriaDTO;
import com.supermercado.supermercado.models.Categoria;

@Component
public class CategoriaMapper {

    public CategoriaDTO toDTO(Categoria categoria) {

        CategoriaDTO categoriaDTO = new CategoriaDTO();
        categoriaDTO.setUuidCodigo(categoria.getUuidCodigo());
        categoriaDTO.setNombre(categoria.getNombre());
        categoriaDTO.setDescripción(categoria.getDescripción());

        return categoriaDTO;

    }

    public Categoria getCategoria(CategoriaDTO categoriaDTO) {

        Categoria categoria = new Categoria();
        categoria.setUuidCodigo(categoriaDTO.getUuidCodigo());
        categoria.setNombre(categoriaDTO.getNombre());
        categoria.setDescripción(categoriaDTO.getDescripción());

        return categoria;

    }
}
