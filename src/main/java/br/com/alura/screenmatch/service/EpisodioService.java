package br.com.alura.screenmatch.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alura.screenmatch.dto.EpisodioDto;
import br.com.alura.screenmatch.mapper.EpisodioMapper;
import br.com.alura.screenmatch.model.Episodio;
import br.com.alura.screenmatch.repository.EpisodioRepository;

@Service
public class EpisodioService {

    @Autowired
    private EpisodioMapper episodioMapper;

    @Autowired
    private EpisodioRepository episodioRepository;

    public List<EpisodioDto> obterTodasTemporadasDeSerie(Long id) {

        final List<Episodio> episodios = episodioRepository.findBySerieId(id);

        final List<EpisodioDto> episodioDtos = episodioMapper.episodiosToEpisodioDtos(episodios);

        return episodioDtos;
    }

    public List<EpisodioDto> obterTemporadaDeSerie(Long serieId, int temporada) {

        final List<Episodio> episodios = episodioRepository.findBySerieIdAndTemporada(serieId, temporada);

        final List<EpisodioDto> episodioDtos = episodioMapper.episodiosToEpisodioDtos(episodios);

        return episodioDtos;
    }

}
