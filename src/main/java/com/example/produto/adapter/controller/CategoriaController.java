package com.example.produto.adapter.controller;

import com.example.produto.adapter.controller.response.ProdutoResponse;
import com.example.produto.adapter.presenter.produto.ProdutoResponseMapper;
import com.example.produto.core.usecase.GerenciarProdutoUseCasePort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/categoria")
public class CategoriaController {

    private static final Logger logger = LoggerFactory.getLogger(ProdutoController.class);

    private final GerenciarProdutoUseCasePort gerenciarProdutoUseCasePort;

    public CategoriaController(GerenciarProdutoUseCasePort gerenciarProdutoUseCasePort) {
        this.gerenciarProdutoUseCasePort = gerenciarProdutoUseCasePort;
    }

    @GetMapping("/{categoria}/produto")
    public ResponseEntity<List<ProdutoResponse>> buscar(@PathVariable("categoria") final String categoria){
        return ResponseEntity.ok().body(
                ProdutoResponseMapper.INSTANCE.mapListFrom(
                        gerenciarProdutoUseCasePort.buscarProdutoPorCategoria(categoria)));
    }
}
