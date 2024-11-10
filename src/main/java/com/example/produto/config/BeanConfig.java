package com.example.produto.config;

import com.example.produto.adapter.gateway.interfaces.GerenciarProdutoAdapterPort;
import com.example.produto.core.usecase.GerenciarProdutoUseCasePort;
import com.example.produto.core.usecase.impl.GerenciarProdutoUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public GerenciarProdutoUseCasePort incluirProdutoUseCasePort(GerenciarProdutoAdapterPort gerenciarProdutoAdapterPort) {
        return new GerenciarProdutoUseCase(gerenciarProdutoAdapterPort);
    }
}
