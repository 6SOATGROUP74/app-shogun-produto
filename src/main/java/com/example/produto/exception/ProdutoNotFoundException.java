package com.example.produto.exception;

public class ProdutoNotFoundException extends RuntimeException {

    public ProdutoNotFoundException(final String message){
        super(message);
    };

}
