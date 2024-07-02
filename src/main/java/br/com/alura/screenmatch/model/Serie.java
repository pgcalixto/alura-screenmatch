package br.com.alura.screenmatch.model;

import java.util.List;

public record Serie(
        String titulo,
        Integer totalTemporadas,
        String avaliacao,
        List<Genero> generos,
        String atores,
        String poster,
        String sinopse) {

}
