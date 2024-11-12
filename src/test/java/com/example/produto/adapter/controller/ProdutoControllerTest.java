package com.example.produto.adapter.controller;

import com.example.produto.ProdutoBuilder;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ProdutoControllerTest {

    private MockMvc mockMvc;

    @Mock
    GerenciarProdutoUseCasePort gerenciarProdutoUseCasePort;

    @InjectMocks
    ProdutoController produtoController;

    AutoCloseable openMocks;

    @BeforeEach
    void setUp() {
        openMocks = MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(produtoController)
                .setControllerAdvice(new CustomExceptionHandler())
                .addFilter((request, response, chain) -> {
                    response.setCharacterEncoding("UTF-8");
                    chain.doFilter(request, response);
                }, "/*")
                .build();
    }


    @Test
    void devePermitirRegistrarMensagem() throws Exception {

        ProdutoRequest produtoRequest = ProdutoBuilder.factoryProdutoRequest();

        when(gerenciarProdutoUseCasePort.salvar(any(Produto.class)))
                .thenAnswer(i -> i.getArgument(0));

        mockMvc.perform(post("/v1/produtos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(produtoRequest)))
                    .andDo(print())
                .andExpect(status().isCreated());
        verify(gerenciarProdutoUseCasePort, times(1))
                .salvar(any(Produto.class));
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}