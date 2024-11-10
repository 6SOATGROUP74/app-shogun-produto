package com.example.produto.adapter.infra.repository.presenter;

import com.example.produto.adapter.infra.repository.entity.ProdutoEntity;
import com.example.produto.core.domain.Produto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProdutoEntityMapper {

    ProdutoEntityMapper INSTANCE = Mappers.getMapper(ProdutoEntityMapper.class);

    ProdutoEntity mapFrom(Produto produto);
    Produto mapFrom(ProdutoEntity produto);
    List<Produto> mapFrom(List<ProdutoEntity> produto);


}
