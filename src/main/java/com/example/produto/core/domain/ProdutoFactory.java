package com.example.produto.core.domain;

import java.math.BigDecimal;

public class ProdutoFactory {

    public static Produto create(String nome, Long quantidade, CategoriaEnum categoria, BigDecimal valor){
        Produto produto = new Produto();
       // produto.setIdProduto();

        return produto;
    }
}
