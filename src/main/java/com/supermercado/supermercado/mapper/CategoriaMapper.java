package com.supermercado.supermercado.mapper;

import org.springframework.stereotype.Component;

import com.supermercado.supermercado.dtos.CategoriaDTO;
import com.supermercado.supermercado.models.Categoria;

@Component
public class CategoriaMapper {

    public CategoriaDTO toDTO(Categoria categoria) {

        CategoriaDTO categoriaDTO = new CategoriaDTO();
        categoriaDTO.setUuid(categoria.getUuid());
        categoriaDTO.setNombre(categoria.getNombre());
        categoriaDTO.setDescripción(categoria.getDescripción());

        return categoriaDTO;

    }

    public Categoria getCategoria(CategoriaDTO categoriaDTO) {

        Categoria categoria = new Categoria();
        categoria.setUuid(categoriaDTO.getUuid());
        categoria.setNombre(categoriaDTO.getNombre());
        categoria.setDescripción(categoriaDTO.getDescripción());

        return categoria;

    }
}
