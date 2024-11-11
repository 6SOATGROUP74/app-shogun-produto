package com.example.produto.core.domain;

import com.example.produto.adapter.controller.request.ProdutoRequest;

public class ProdutoFactory {

    public static Produto create(ProdutoRequest produtoRequest){
        Produto produto = new Produto();

        produto.setStatus(Boolean.TRUE);
        produto.setCategoria(CategoriaEnum.valueOf(produtoRequest.categoria()));
        produto.setNome(produtoRequest.nome());
        produto.setValor(produtoRequest.valor());
        return produto;
    }
}
