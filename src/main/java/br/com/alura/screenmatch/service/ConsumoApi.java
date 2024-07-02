package br.com.alura.screenmatch.service;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.alura.screenmatch.dto.SerieDto;
import br.com.alura.screenmatch.dto.TemporadaDto;
import br.com.alura.screenmatch.mapper.SerieMapper;
import br.com.alura.screenmatch.model.Serie;

@Service
public class ConsumoApi {

    private final String ENDERECO_BASE = "https://www.omdbapi.com";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private SerieMapper serieMapper;

    private final String CHAVE_SERIE = "t";

    private final String CHAVE_TEMPORADA = "season";

    private final String CHAVE_API_KEY = "apikey";

    @Value("${imdb.api_key}")
    private String API_KEY;

    public Serie getSerie(final String nomeSerie) {

        URI uri = UriComponentsBuilder.fromHttpUrl(ENDERECO_BASE)
                .queryParam(CHAVE_API_KEY, API_KEY)
                .queryParam(CHAVE_SERIE, nomeSerie)
                .build()
                .encode()
                .toUri();

        SerieDto serieDto = restTemplate.getForObject(uri, SerieDto.class);

        Serie serie = serieMapper.serieDtoToSerie(serieDto);

        return serie;
    }

    public TemporadaDto getTemporada(final String nomeSerie, final int numeroTemporada) {

        URI uri = UriComponentsBuilder.fromHttpUrl(ENDERECO_BASE)
                .queryParam(CHAVE_API_KEY, API_KEY)
                .queryParam(CHAVE_SERIE, nomeSerie)
                .queryParam(CHAVE_TEMPORADA, numeroTemporada)
                .build()
                .encode()
                .toUri();

        TemporadaDto temporada = restTemplate.getForObject(uri, TemporadaDto.class);

        return temporada;
    }
}
