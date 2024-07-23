package br.com.alura.screenmatch.dto;

public record EpisodioDto(
        String titulo,
        Integer numero,
        Double avaliacao,
        String dataLancamento,
        int temporada) {
}
