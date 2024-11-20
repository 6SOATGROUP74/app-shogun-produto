package com.example.produto.adapter.infra.repository.presenter;

import com.example.produto.adapter.infra.repository.entity.ProdutoEntity;
import com.example.produto.core.domain.CategoriaEnum;
import com.example.produto.core.domain.Produto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Objects;

@Mapper
public interface ProdutoEntityMapper {

    ProdutoEntityMapper INSTANCE = Mappers.getMapper(ProdutoEntityMapper.class);

    ProdutoEntity mapFrom(Produto produto);


    @Mapping(source = "categoria", target = "categoria", qualifiedByName = "stringToEnum")
    Produto mapFrom(ProdutoEntity produto);
    List<Produto> mapFrom(List<ProdutoEntity> produto);


    @Named("stringToEnum")
    default CategoriaEnum stringToEnum(String categoria) {
        if (Objects.isNull(categoria)) {
            return null;
        }
        return CategoriaEnum.valueOf(categoria.toUpperCase());
    }
}
