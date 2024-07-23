package br.com.alura.screenmatch.dto;

public record EpisodioDto(
        Long id,
        String titulo,
        Integer numeroEpisodio,
        Double avaliacao,
        String dataLancamento,
        int temporada) {
}
