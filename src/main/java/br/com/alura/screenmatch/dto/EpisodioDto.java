package br.com.alura.screenmatch.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import br.com.alura.screenmatch.service.DoubleWithInvalidAsNullDeserializer;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
public class EpisodioDto {

    private String titulo;

    private Integer numero;

    private Double avaliacao;

    private String dataLancamento;

    @Setter
    private int temporada;

    public EpisodioDto(
            @JsonAlias("Title")
            String titulo,

            @JsonAlias("Episode")
            Integer numero,

            @JsonAlias("imdbRating")
            @JsonDeserialize(using = DoubleWithInvalidAsNullDeserializer.class)
            Double avaliacao,

            @JsonAlias("Released")
            String dataLancamento) {

        this.titulo = titulo;
        this.numero = numero;
        this.avaliacao = avaliacao;
        this.dataLancamento = dataLancamento;
    }

}
