package com.example.produto.adapter.controller.response;

import java.math.BigDecimal;

public record ProdutoResponse (Long idProduto, String nome, String categoria, BigDecimal valor){}
