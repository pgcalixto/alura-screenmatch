package br.com.alura.screenmatch.model;

import java.util.Map;

public enum Genero {
    ACAO,
    ANIMACAO,
    AVENTURA,
    COMEDIA,
    CRIME,
    DRAMA,
    ROMANCE;

    private static final Map<String, Genero> ENUM_MAP = Map.ofEntries(
        Map.entry("action", ACAO),
        Map.entry("adventure", AVENTURA),
        Map.entry("animation", ANIMACAO),
        Map.entry("comedy", COMEDIA),
        Map.entry("crime", CRIME),
        Map.entry("drama", DRAMA),
        Map.entry("romance", ROMANCE)
    );

    public static Genero fromCategoriaImdb(final String categoriaImdb) {
        return ENUM_MAP.get(categoriaImdb.toLowerCase());
    }
}
