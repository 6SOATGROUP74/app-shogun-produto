package com.example.produto.adapter.gateway.interfaces;


import com.example.produto.core.domain.Produto;

import java.util.List;

public interface GerenciarProdutoAdapterPort {
    Produto salvar(Produto produto);
    List<Produto> buscarProdutoPorCategoria(String categoria);
    Produto buscarProdutoPorId(Long id);
}
