package br.com.alura.screenmatch.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alura.screenmatch.dto.SerieDto;
import br.com.alura.screenmatch.mapper.SerieMapper;
import br.com.alura.screenmatch.model.Genero;
import br.com.alura.screenmatch.model.Serie;
import br.com.alura.screenmatch.repository.SerieRepository;

@Service
public class SerieService {

    @Autowired
    private SerieMapper serieMapper;

    @Autowired
    private SerieRepository serieRepository;

    public List<SerieDto> obterTodasSeries() {

        final List<Serie> series = serieRepository.findAll();

        final List<SerieDto> serieDtos = serieMapper.seriesToSerieDtos(series);

        return serieDtos;
    }

    public List<SerieDto> obterSeriesMaisBemAvaliadas() {

        final List<Serie> series = serieRepository.findFirst5ByOrderByAvaliacaoDesc();

        final List<SerieDto> serieDtos = serieMapper.seriesToSerieDtos(series);

        return serieDtos;
    }

    public List<SerieDto> obterLancamentos() {

        final List<Serie> series = serieRepository.findTop5Lancamentos();

        final List<SerieDto> serieDtos = serieMapper.seriesToSerieDtos(series);

        return serieDtos;
    }

    public SerieDto obterSeriePorId(Long id) {

        final Serie serie = serieRepository
                .findById(id)
                .orElse(null);

        final SerieDto serieDto = serieMapper.serieToSerieDto(serie);

        return serieDto;
    }

    public List<SerieDto> obterSeriesPorGenero(String nomeGenero) {

        final Genero genero = Genero.fromNome(nomeGenero.toLowerCase());

        final List<Serie> series = serieRepository.findByGenerosIn(genero);

        final List<SerieDto> serieDtos = serieMapper.seriesToSerieDtos(series);

        return serieDtos;
    }

}
