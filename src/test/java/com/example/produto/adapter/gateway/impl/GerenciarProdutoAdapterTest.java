package com.example.produto.adapter.gateway.impl;

import com.example.produto.ProdutoBuilder;
import com.example.produto.adapter.infra.ProdutoRepository;
import com.example.produto.adapter.infra.repository.entity.ProdutoEntity;
import com.example.produto.core.domain.CategoriaEnum;
import com.example.produto.core.domain.Produto;
import com.example.produto.exception.CategoriaInvalidaException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class GerenciarProdutoAdapterTest {

    @Mock
    ProdutoRepository repository;

    @InjectMocks
    GerenciarProdutoAdapter adapter;

    AutoCloseable openMocks;

    @BeforeEach
    void setUp() {
        openMocks = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    @Nested
    class SalvarProduto {

        @Test
        void devePermitirSalvarProduto(){
            when(repository.save(any(ProdutoEntity.class))).thenAnswer(item -> item.getArgument(0));

            final var produtoCommon = ProdutoBuilder.factory();

            var result = adapter.salvar(produtoCommon);

            assertThat(result).isNotNull().isInstanceOf(Produto.class);
            verify(repository, times(1)).save(any(ProdutoEntity.class));

        }

    }

    @Nested
    class BuscarProduto{

        @Test
        void devePermitirBuscarProdutoPorCategoria(){

            when(repository.findByCategoriaAndStatus(any(String.class), any(Boolean.class)))
                    .thenReturn(List.of(ProdutoBuilder.factoryEntity()));

            var result = adapter.buscarProdutoPorCategoria(CategoriaEnum.ACOMPANHAMENTO.name());

            verify(repository, times(1)).findByCategoriaAndStatus(anyString(), anyBoolean());

            assertThat(result).isNotNull().isInstanceOf(List.class);
            assertThat(result).isNotNull().isNotEmpty();

        }

        @Test
        void deveGerarExcecao_quandoBuscarCategoriaInvalida(){

            assertThatThrownBy( () -> adapter.buscarProdutoPorCategoria("Categoria invalida"))
                    .isInstanceOf(CategoriaInvalidaException.class).hasMessage("Categoria invalida");

            verify(repository, never()).findByCategoriaAndStatus(anyString(), anyBoolean());

        }

        @Test
        void devePermitirBuscarProdutoPorId() {

            when(repository.findById(anyLong())).thenReturn(Optional.of(ProdutoBuilder.factoryEntity()));

            final var result = adapter.buscarProdutoPorId(1L);
            verify(repository, times(1)).findById(anyLong());

            assertThat(result).isNotNull().isInstanceOf(Produto.class);

        }

    }

}