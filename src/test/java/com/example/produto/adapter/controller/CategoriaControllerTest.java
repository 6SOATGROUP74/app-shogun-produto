package com.example.produto.adapter.controller;

import com.example.produto.ProdutoCommon;
import com.example.produto.adapter.controller.request.ProdutoRequest;
import com.example.produto.core.domain.Produto;
import com.example.produto.core.usecase.GerenciarProdutoUseCasePort;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CategoriaControllerTest {

    private MockMvc mockMvc;

    @Mock
    GerenciarProdutoUseCasePort gerenciarProdutoUseCasePort;

    @InjectMocks
    CategoriaController categoriaController;

    AutoCloseable openMocks;

    @BeforeEach
    void setUp() {
        openMocks = MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(categoriaController)
                .setControllerAdvice(new CustomExceptionHandler())
                .addFilter((request, response, chain) -> {
                    response.setCharacterEncoding("UTF-8");
                    chain.doFilter(request, response);
                }, "/*")
                .build();
    }


    @Test
    void devePermitirSalvarProduto() throws Exception {

        ProdutoRequest produtoRequest = ProdutoCommon.factoryProdutoRequest();

        when(gerenciarProdutoUseCasePort.buscarProdutoPorCategoria(anyString()))
                .thenReturn(List.of(ProdutoCommon.factory()));

        mockMvc.perform(get("/v1/categoria/LANCHES/produto")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(produtoRequest)))
                    .andDo(print())
                .andExpect(status().isOk());
        verify(gerenciarProdutoUseCasePort, times(1))
                .buscarProdutoPorCategoria(anyString());
    }


    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}