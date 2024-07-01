package br.com.alura.screenmatch.model;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum Genero {
    ACAO("action"),
    ANIMACAO("animation"),
    AVENTURA("adventure"),
    COMEDIA("comedy"),
    CRIME("crime"),
    DRAMA("drama"),
    ROMANCE("romance");

    private String categoriaImdb;

    private static final Map<String, Genero> ENUM_MAP = Arrays
            .stream(Genero.values())
            .collect(Collectors.toMap(
                    Genero::getCategoriaImdb,
                    Function.identity()));

    Genero(final String categoriaImdb) {
        this.categoriaImdb = categoriaImdb;
    }

    public static Genero fromCategoriaImdb(final String categoriaImdb) {
        return ENUM_MAP.get(categoriaImdb.toLowerCase());
    }

    private String getCategoriaImdb() {
        return categoriaImdb;
    }
}
