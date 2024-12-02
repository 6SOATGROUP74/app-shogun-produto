package com.example.produto.core.usecase.impl;

import com.example.produto.ProdutoCommon;
import com.example.produto.adapter.gateway.interfaces.GerenciarProdutoAdapterPort;
import com.example.produto.core.domain.CategoriaEnum;
import com.example.produto.core.domain.Produto;
import com.example.produto.exception.CategoriaInvalidaException;
import com.example.produto.exception.ProdutoNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class GerenciarProdutoUseCaseTest {


    @Mock
    GerenciarProdutoAdapterPort gerenciarProdutoAdapterPort;

    @InjectMocks
    GerenciarProdutoUseCase gerenciarProdutoUseCase;

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
    class SalvarProduto{

        @Test
        void devePermitirSalvarProduto(){

            when(gerenciarProdutoAdapterPort.salvar(any(Produto.class))).thenReturn(ProdutoCommon.factory());
            final var result = gerenciarProdutoUseCase.salvar(ProdutoCommon.factory());

            assertThat(result).isInstanceOf(Produto.class).isNotNull();
            verify(gerenciarProdutoAdapterPort, times(1)).salvar(any(Produto.class));

        }
    }

    @Nested
    class BuscarProduto {

        @Test
        void devePermitirBuscarProdutoPorCategoria(){

            when(gerenciarProdutoAdapterPort.buscarProdutoPorCategoria(anyString()))
                    .thenReturn(List.of(ProdutoCommon.factory()));

            final var result = gerenciarProdutoUseCase.buscarProdutoPorCategoria(CategoriaEnum.ACOMPANHAMENTO.name());

            assertThat(result)
                    .isNotNull()
                    .isNotEmpty()
                    .isInstanceOf(List.class);
        }

        @Test
        void deveGerarExcecao_quandoBuscarProdutoPorCategoria(){

            assertThatThrownBy(() -> gerenciarProdutoUseCase.buscarProdutoPorCategoria("Categoria invalida"))
                    .isInstanceOf(CategoriaInvalidaException.class)
                    .hasMessage("A categoria invalida");

            verify(gerenciarProdutoAdapterPort, never()).buscarProdutoPorId(anyLong());
        }

        @Test
        void deveLancarExcecao_QuandoBuscarProdutoInexistente(){

            when(gerenciarProdutoAdapterPort.buscarProdutoPorId(anyLong()))
                    .thenReturn(null);

            assertThatThrownBy(() -> gerenciarProdutoUseCase.buscarProdutoPorId(1L))
                    .isInstanceOf(ProdutoNotFoundException.class)
                    .hasMessage("Produto nao localizado na base.");

            verify(gerenciarProdutoAdapterPort, times(1))
                    .buscarProdutoPorId(anyLong());

            verify(gerenciarProdutoAdapterPort, never()).salvar(any(Produto.class));

        }

        @Test
        void devePermitirBuscarProdutoPorId(){

            when(gerenciarProdutoAdapterPort.buscarProdutoPorId(anyLong()))
                    .thenReturn(ProdutoCommon.factory());

            gerenciarProdutoUseCase.buscarProdutoPorId(1L);

            verify(gerenciarProdutoAdapterPort, times(1))
                    .buscarProdutoPorId(anyLong());

            verify(gerenciarProdutoAdapterPort, never()).salvar(any(Produto.class));

        }
    }



    @Nested
    class DeletarProduto {

        @Test
        void devePermitirDeletarProduto(){

            when(gerenciarProdutoAdapterPort.buscarProdutoPorId(anyLong()))
                    .thenReturn(ProdutoCommon.factory());


            when(gerenciarProdutoAdapterPort.salvar(any(Produto.class)))
                    .thenAnswer(item -> item.getArgument(0));


            gerenciarProdutoUseCase.deletarProduto(1L);

            verify(gerenciarProdutoAdapterPort, times(1)).buscarProdutoPorId(anyLong());
            verify(gerenciarProdutoAdapterPort, times(1)).salvar(any(Produto.class));

        }

        @Test
        void deveLancarExcecao_QuandoDeletarProdutoInexistente(){

            when(gerenciarProdutoAdapterPort.buscarProdutoPorId(anyLong()))
                    .thenReturn(null);

            assertThatThrownBy(() -> gerenciarProdutoUseCase.deletarProduto(1L))
                    .isInstanceOf(ProdutoNotFoundException.class)
                    .hasMessage("Produto nao localizado na base.");

            verify(gerenciarProdutoAdapterPort, times(1))
                    .buscarProdutoPorId(anyLong());

            verify(gerenciarProdutoAdapterPort, never()).salvar(any(Produto.class));

        }

    }

    @Nested
    class AlterarProduto{

        @Test
        void devePermitirAlterarProduto(){
            when(gerenciarProdutoAdapterPort.buscarProdutoPorId(anyLong()))
                    .thenReturn(ProdutoCommon.factory());

            when(gerenciarProdutoAdapterPort.salvar(any(Produto.class)))
                    .thenReturn(ProdutoCommon.factory());

            final var result = gerenciarProdutoUseCase.alterarProduto(ProdutoCommon.factory(), 1L);

            assertThat(result)
                .isNotNull()
                .isInstanceOf(Produto.class);


            verify(gerenciarProdutoAdapterPort, times(1))
                    .salvar(any(Produto.class));

            verify(gerenciarProdutoAdapterPort, times(1))
                    .buscarProdutoPorId(anyLong());

        }



        @Test
        void deveLancarExcecao_QuandoAlterarProduto(){
            when(gerenciarProdutoAdapterPort.buscarProdutoPorId(anyLong()))
                    .thenReturn(null);

            assertThatThrownBy(() ->
                    gerenciarProdutoUseCase.alterarProduto(ProdutoCommon.factory(), 1L))
                    .isInstanceOf(ProdutoNotFoundException.class)
                            .hasMessage("Produto nao localizado na base.");

            verify(gerenciarProdutoAdapterPort, times(1))
                    .buscarProdutoPorId(anyLong());

            verify(gerenciarProdutoAdapterPort, never())
                    .salvar(any(Produto.class));

        }
    }

}