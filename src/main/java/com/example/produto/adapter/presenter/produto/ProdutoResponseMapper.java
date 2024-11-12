package com.example.produto.adapter.presenter.produto;

import com.example.produto.adapter.controller.response.ProdutoResponse;
import com.example.produto.core.domain.Produto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProdutoResponseMapper {

    ProdutoResponseMapper INSTANCE = Mappers.getMapper(ProdutoResponseMapper.class);

    ProdutoResponse mapFrom(final Produto produto);
    List<ProdutoResponse> mapListFrom(final List<Produto> produto);

}
