package br.com.alura.screenmatch.dto;

import java.util.List;

import br.com.alura.screenmatch.model.Genero;

public record SerieDto(
        Long id,
        String titulo,
        Integer totalTemporadas,
        Double avaliacao,
        List<Genero> generos,
        String atores,
        String poster,
        String sinopse,
        List<EpisodioDto> episodios) {

}
