package br.com.alura.screenmatch.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import br.com.alura.screenmatch.model.Genero;
import br.com.alura.screenmatch.service.DoubleWithInvalidAsNullDeserializer;
import br.com.alura.screenmatch.service.GeneroDeserializer;
import br.com.alura.screenmatch.service.SinopseDeserializer;

@JsonIgnoreProperties(ignoreUnknown = true)
public record SerieImdbDto(
        @JsonAlias("Title")
        String titulo,

        @JsonAlias("totalSeasons")
        Integer totalTemporadas,

        @JsonAlias("imdbRating")
        @JsonDeserialize(using = DoubleWithInvalidAsNullDeserializer.class)
        Double avaliacao,

        @JsonAlias("Genre")
        @JsonDeserialize(using = GeneroDeserializer.class)
        List<Genero> generos,

        @JsonAlias("Actors")
        String atores,

        @JsonAlias("Poster")
        String poster,

        @JsonAlias("Plot")
        @JsonDeserialize(using = SinopseDeserializer.class)
        String sinopse) {

}
