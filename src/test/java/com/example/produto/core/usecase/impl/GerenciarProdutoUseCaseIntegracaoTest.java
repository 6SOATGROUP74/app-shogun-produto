package com.example.produto.core.usecase.impl;

import com.example.produto.ProdutoCommon;
import com.example.produto.adapter.gateway.interfaces.GerenciarProdutoAdapterPort;
import com.example.produto.core.domain.CategoriaEnum;
import com.example.produto.core.domain.Produto;
import com.example.produto.exception.CategoriaInvalidaException;
import com.example.produto.exception.ProdutoNotFoundException;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
@ActiveProfiles("test")
class GerenciarProdutoUseCaseIntegracaoTest {


    @Autowired
    GerenciarProdutoAdapterPort gerenciarProdutoAdapterPort;

    @Autowired
    GerenciarProdutoUseCase gerenciarProdutoUseCase;


    @Nested
    class SalvarProduto{

        @Test
        void devePermitirSalvarProduto(){

            final var result = gerenciarProdutoUseCase.salvar(ProdutoCommon.factory());
            assertThat(result).isNotNull().isInstanceOf(Produto.class);

        }
    }

    @Nested
    class BuscarProduto {

        @Test
        void devePermitirBuscarProdutoPorCategoria(){
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

        }
    }

    @Nested
    class DeletarProduto {



        @Test
        void deveLancarExcecao_QuandoDeletarProdutoInexistente(){

            assertThatThrownBy(() -> gerenciarProdutoUseCase.deletarProduto(1000L))
                    .isInstanceOf(ProdutoNotFoundException.class)
                    .hasMessage("Produto nao localizado na base.");

        }

    }

    @Nested
    class AlterarProduto{

        @Test
        void devePermitirAlterarProduto(){

            final var result = gerenciarProdutoUseCase.alterarProduto(ProdutoCommon.factory(), 1L);

            assertThat(result)
                .isNotNull()
                .isInstanceOf(Produto.class);

        }

        @Test
        void deveLancarExcecao_QuandoAlterarProduto(){

            assertThatThrownBy(() ->
                    gerenciarProdutoUseCase.alterarProduto(ProdutoCommon.factory(), 0L))
                    .isInstanceOf(ProdutoNotFoundException.class)
                            .hasMessage("Produto nao localizado na base.");

        }
    }

}