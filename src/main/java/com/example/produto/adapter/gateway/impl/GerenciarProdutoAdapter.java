package com.example.produto.adapter.gateway.impl;

import com.example.produto.adapter.gateway.interfaces.GerenciarProdutoAdapterPort;
import com.example.produto.adapter.infra.ProdutoRepository;
import com.example.produto.adapter.infra.repository.presenter.ProdutoEntityMapper;
import com.example.produto.core.domain.CategoriaEnum;
import com.example.produto.core.domain.Produto;
import com.example.produto.exception.CategoriaInvalidaException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GerenciarProdutoAdapter implements GerenciarProdutoAdapterPort {

    private static final Logger logger = LogManager.getLogger(GerenciarProdutoAdapter.class);

    private final ProdutoRepository produtoRepository;

    public GerenciarProdutoAdapter(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Override
    public Produto salvar(Produto produto) {


        var produtoEntity = ProdutoEntityMapper.INSTANCE.mapFrom(produto);
        return ProdutoEntityMapper.INSTANCE.mapFrom(produtoRepository.save(produtoEntity));
    }

    @Override
    public List<Produto> buscarProdutoPorCategoria(String categoria) {
        logger.info("m=buscarProdutoPorCategoria, msg=Buscando produto por categoria, categoria={}", categoria);

        if(!CategoriaEnum.contains(categoria)){
            throw new CategoriaInvalidaException("Categoria invalida");
        }

        return ProdutoEntityMapper.INSTANCE.mapFrom(produtoRepository.findByCategoriaAndStatus(categoria,true));
    }

    @Override
    public Produto buscarProdutoPorId(Long id) {
        logger.info("m=buscarProdutoPorId, msg=Buscando produto por id, id={}", id);
        return ProdutoEntityMapper.INSTANCE.mapFrom(produtoRepository.findById(id).orElse(null));
    }
}
