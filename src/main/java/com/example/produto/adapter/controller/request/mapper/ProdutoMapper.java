package com.example.produto.adapter.controller.request.mapper;

import com.example.produto.adapter.controller.request.ProdutoRequest;
import com.example.produto.core.domain.Produto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProdutoMapper {

    ProdutoMapper INSTANCE = Mappers.getMapper(ProdutoMapper.class);

    Produto mapFrom(ProdutoRequest produtoRequest);
}
