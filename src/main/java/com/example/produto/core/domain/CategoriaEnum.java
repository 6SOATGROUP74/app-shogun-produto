package com.example.produto.core.domain;

public enum CategoriaEnum {
    LANCHE("lanche"),
    ACOMPANHAMENTO("acompanhamento"),
    BEBIDA("bebida"),
    SOBREMESA("sobremesa");

    CategoriaEnum(String sobremesa) {
    }

    public static boolean contains(String test) {
        for (CategoriaEnum c : CategoriaEnum.values()) {
            if (c.name().equalsIgnoreCase(test)) {
                return true;
            }
        }
        return false;
    }
}