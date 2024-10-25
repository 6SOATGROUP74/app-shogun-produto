package com.example.produto.core.domain;


import java.math.BigDecimal;

public class Produto {

    private String nome;
    private Long quantidade;
    private String categoria;
    private BigDecimal valor;
    private Long idProduto;
    private boolean status;

    public String getNome() {
        return nome;
    }

    public Long getQuantidade() {
        return quantidade;
    }

    public String getCategoria() {
        return categoria;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public Long getIdProduto() {
        return idProduto;
    }

    public boolean isStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "nome='" + nome + '\'' +
                ", quantidade=" + quantidade +
                ", categoria='" + categoria + '\'' +
                ", valor=" + valor +
                ", idProduto=" + idProduto +
                ", status=" + status +
                '}';
    }
}
