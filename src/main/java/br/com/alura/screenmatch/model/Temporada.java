package br.com.alura.screenmatch.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Temporada(
        @JsonAlias("Season") Integer numero,
        @JsonAlias("Episodes") List<Episodio> episodios) {

}
