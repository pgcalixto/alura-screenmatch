package br.com.alura.screenmatch.model;

import java.util.List;

public record Temporada(
    Integer numero,
    List<Episodio> episodios) {

}
