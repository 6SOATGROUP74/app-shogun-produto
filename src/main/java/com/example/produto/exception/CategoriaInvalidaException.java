package com.example.produto.exception;

public class CategoriaInvalidaException extends RuntimeException {

    public CategoriaInvalidaException(final String message){
        super(message);
    };

    public CategoriaInvalidaException(final String message, final Throwable cause){
        super(message, cause);
    };
}
