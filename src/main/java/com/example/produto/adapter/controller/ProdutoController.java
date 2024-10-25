package com.example.produto.adapter.controller;

import com.example.produto.adapter.controller.request.ProdutoRequest;
import com.example.produto.adapter.controller.request.mapper.ProdutoMapper;
import com.example.produto.adapter.presenter.produto.ProdutoResponseMapper;
import com.example.produto.core.usecase.GerenciarProdutoUseCasePort;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/produtos")
public class ProdutoController {

    private static final Logger logger = LoggerFactory.getLogger(ProdutoController.class);

    private final GerenciarProdutoUseCasePort gerenciarProdutoUseCasePort;

    public ProdutoController(GerenciarProdutoUseCasePort gerenciarProdutoUseCasePort) {
        this.gerenciarProdutoUseCasePort = gerenciarProdutoUseCasePort;
    }

    @PostMapping
    public ResponseEntity<?> incluir(@RequestBody @Valid ProdutoRequest request){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ProdutoResponseMapper.INSTANCE.mapFrom(
                        gerenciarProdutoUseCasePort.salvar(ProdutoMapper.INSTANCE.mapFrom(request))));
    }

    @PatchMapping("/{idProduto}")
    public ResponseEntity<?> alterar(@RequestBody @Valid ProdutoRequest request,
                                     @PathVariable("idProduto") final Long idProduto){
        return ResponseEntity.ok().body(
                ProdutoResponseMapper.INSTANCE.mapFrom(
                        gerenciarProdutoUseCasePort.alterarProduto(
                ProdutoMapper.INSTANCE.mapFrom(request), idProduto)));
    }

    @DeleteMapping("/{idProduto}")
    public ResponseEntity<?> deletar(@PathVariable("idProduto") final Long idProduto){
        gerenciarProdutoUseCasePort.deletarProduto(idProduto);
        return ResponseEntity.ok().build();
    }

}
