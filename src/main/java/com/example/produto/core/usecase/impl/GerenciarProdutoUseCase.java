package com.example.produto.core.usecase.impl;


import com.example.produto.adapter.gateway.interfaces.GerenciarProdutoAdapterPort;
import com.example.produto.core.domain.CategoriaEnum;
import com.example.produto.core.domain.Produto;
import com.example.produto.core.domain.ProdutoFactory;
import com.example.produto.core.usecase.GerenciarProdutoUseCasePort;
import com.example.produto.exception.CategoriaInvalidaException;
import com.example.produto.exception.ProdutoNotFoundException;

import java.util.List;
import java.util.Objects;

public class GerenciarProdutoUseCase implements GerenciarProdutoUseCasePort {

    private final GerenciarProdutoAdapterPort gerenciarProdutoAdapterPort;

    public GerenciarProdutoUseCase(GerenciarProdutoAdapterPort gerenciarProdutoAdapterPort) {
        this.gerenciarProdutoAdapterPort = gerenciarProdutoAdapterPort;
    }

    @Override
    public Produto salvar(Produto produto) {

        return gerenciarProdutoAdapterPort.salvar(produto);
    }

    @Override
    public List<Produto> buscarProdutoPorCategoria(String categoria) {
        if(!CategoriaEnum.contains(categoria.toUpperCase())){
            throw new CategoriaInvalidaException("A categoria invalida");
        }
        return gerenciarProdutoAdapterPort.buscarProdutoPorCategoria(categoria);
    }

    @Override
    public void deletarProduto(final Long idProduto) {
        var produto = gerenciarProdutoAdapterPort.buscarProdutoPorId(idProduto);

        if(Objects.isNull(produto)){
            throw new ProdutoNotFoundException("Produto nao localizado na base.");
        }

        produto.setStatus(false);
        gerenciarProdutoAdapterPort.salvar(produto);
    }

    @Override
    public Produto alterarProduto(Produto produto, final Long idProduto) {
        final var result = gerenciarProdutoAdapterPort.buscarProdutoPorId(idProduto);

        if(Objects.isNull(result)){
            throw new ProdutoNotFoundException("Produto nao localizado na base.");
        }
        produto.setStatus(true);
        produto.setIdProduto(result.getIdProduto());
        return gerenciarProdutoAdapterPort.salvar(produto);
    }
}
