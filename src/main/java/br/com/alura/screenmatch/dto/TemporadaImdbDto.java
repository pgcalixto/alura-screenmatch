package br.com.alura.screenmatch.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
public class TemporadaImdbDto {

    private Integer numero;

    private List<EpisodioImdbDto> episodios;

    @JsonCreator
    public TemporadaImdbDto(
            @JsonAlias("Season") Integer numero,
            @JsonAlias("Episodes") List<EpisodioImdbDto> episodios) {

        this.numero = numero;

        this.episodios = episodios;

        Optional
                .ofNullable(episodios)
                .orElse(new ArrayList<>())
                .forEach(episodio -> episodio.setTemporada(numero));
   }
}
