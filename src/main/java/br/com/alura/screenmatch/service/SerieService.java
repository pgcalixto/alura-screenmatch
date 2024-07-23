package br.com.alura.screenmatch.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alura.screenmatch.dto.SerieDto;
import br.com.alura.screenmatch.mapper.SerieMapper;
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

}
