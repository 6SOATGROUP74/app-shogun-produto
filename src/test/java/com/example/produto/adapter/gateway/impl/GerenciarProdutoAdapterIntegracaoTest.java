package com.example.produto.adapter.gateway.impl;

import com.example.produto.ProdutoCommon;
import com.example.produto.adapter.infra.ProdutoRepository;
import com.example.produto.core.domain.CategoriaEnum;
import com.example.produto.core.domain.Produto;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
@ActiveProfiles("test")
class GerenciarProdutoAdapterIntegracaoTest {


    @Autowired
    private ProdutoRepository repository;

    @Autowired
    private GerenciarProdutoAdapter gerenciarProdutoAdapter;

    @Nested
    class SalvarProduto {

        @Test
        void devePermitirSalvarProduto(){
            final var result = gerenciarProdutoAdapter.salvar(ProdutoCommon.factory());
            assertThat(result).isNotNull().isInstanceOf(Produto.class);
        }

    }

    @Nested
    class BuscarProduto{

        @Test
        void devePermitirBuscarProdutoPorCategoria(){
            final var result = gerenciarProdutoAdapter.
                    buscarProdutoPorCategoria("bebida");

            assertThat(result)
                    .isNotNull()
                    .isInstanceOf(List.class);

            assertThat(result.get(0).getCategoria())
                    .isNotNull()
                    .isInstanceOf(CategoriaEnum.class);

        }

        @Test
        void devePermitirBuscarProdutoPorId() {
            final var result = gerenciarProdutoAdapter.buscarProdutoPorId(1L);

            assertThat(result)
                    .isNotNull()
                    .isInstanceOf(Produto.class);

        }

    }


}