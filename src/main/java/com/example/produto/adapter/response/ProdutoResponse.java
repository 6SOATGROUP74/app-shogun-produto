package com.example.produto.adapter.response;

import java.math.BigDecimal;

public record ProdutoResponse (Long idProduto, String nome, String categoria, BigDecimal valor){}
