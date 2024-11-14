package com.example.produto;

import com.example.produto.adapter.controller.request.ProdutoRequest;
import com.example.produto.adapter.infra.repository.entity.ProdutoEntity;
import com.example.produto.core.domain.CategoriaEnum;
import com.example.produto.core.domain.Produto;

import java.math.BigDecimal;

public abstract class ProdutoCommon {

    public static Produto factory(){
        Produto produto = new Produto();
        produto.setValor(BigDecimal.ONE);
        produto.setNome("Any product");
        produto.setCategoria(CategoriaEnum.ACOMPANHAMENTO);
        produto.setIdProduto(1L);

        return produto;
    }

    public static ProdutoEntity factoryEntity(){
        ProdutoEntity produtoEntity = new ProdutoEntity();

        produtoEntity.setCategoria("BEBIDA");
        produtoEntity.setIdProduto(1L);
        produtoEntity.setNome("Produto entity");
        produtoEntity.setValor(BigDecimal.ONE);
        produtoEntity.setStatus(Boolean.TRUE);

        return produtoEntity;
    }


    public static ProdutoRequest factoryProdutoRequest(){
        return new ProdutoRequest("nome", CategoriaEnum.ACOMPANHAMENTO.name(), BigDecimal.ONE);
    }

}
