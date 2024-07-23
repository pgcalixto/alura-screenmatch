package br.com.alura.screenmatch.model;

import static java.util.function.Function.identity;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonValue;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Genero {
    ACAO("ação", "action"),
    ANIMACAO("animação", "animation"),
    AVENTURA("aventura", "adventure"),
    COMEDIA("comédia", "comedy"),
    CRIME("crime", "crime"),
    DRAMA("drama", "drama"),
    ROMANCE("romance", "romance");

    @JsonValue
    private final String nome;

    private final String categoriaImdb;

    private static final Map<String, Genero> NOME_PARA_GENERO = Arrays.stream(Genero.values())
            .collect(Collectors.toMap(
                    genero -> genero.getNome().toLowerCase(),
                    identity()));

    private static final Map<String, Genero> CATEGORIA_IMDB_PARA_GENERO = Arrays.stream(Genero.values())
            .collect(Collectors.toMap(
                    genero -> genero.getCategoriaImdb().toLowerCase(),
                    identity()));

    public static Genero fromNome(final String nome) {
        return NOME_PARA_GENERO.get(nome.toLowerCase());
    }

    public static Genero fromCategoriaImdb(final String categoriaImdb) {
        return CATEGORIA_IMDB_PARA_GENERO.get(categoriaImdb.toLowerCase());
    }

}
