package com.example.produto.core.domain;

public enum CategoriaEnum {
    LANCHE,
    ACOMPANHAMENTO,
    BEBIDA,
    SOBREMESA;

    public static boolean contains(String test) {
        for (CategoriaEnum c : CategoriaEnum.values()) {
            if (c.name().equalsIgnoreCase(test)) {
                return true;
            }
        }
        return false;
    }
}