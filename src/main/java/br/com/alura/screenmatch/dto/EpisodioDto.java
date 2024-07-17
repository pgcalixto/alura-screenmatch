package br.com.alura.screenmatch.dto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;

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

    private LocalDate dataLancamento;

    @Setter
    private int temporada;

    private static final DateTimeFormatter formatter = new DateTimeFormatterBuilder()
            .append(DateTimeFormatter.ISO_LOCAL_DATE)
            .toFormatter();

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

        try {
            this.dataLancamento = LocalDate.parse(dataLancamento, formatter);
        } catch (DateTimeParseException e) {
            this.dataLancamento = null;
        }
    }

}
