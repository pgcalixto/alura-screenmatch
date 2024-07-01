package br.com.alura.screenmatch.service;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.alura.screenmatch.model.Serie;
import br.com.alura.screenmatch.model.Temporada;

@Service
public class ConsumoApi {

    private final String ENDERECO_BASE = "https://www.omdbapi.com";

    @Autowired
    private RestTemplate restTemplate;

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

        Serie serie = restTemplate.getForObject(uri, Serie.class);

        return serie;
    }

    public Temporada getTemporada(final String nomeSerie, final int numeroTemporada) {

        URI uri = UriComponentsBuilder.fromHttpUrl(ENDERECO_BASE)
                .queryParam(CHAVE_API_KEY, API_KEY)
                .queryParam(CHAVE_SERIE, nomeSerie)
                .queryParam(CHAVE_TEMPORADA, numeroTemporada)
                .build()
                .encode()
                .toUri();

        Temporada temporada = restTemplate.getForObject(uri, Temporada.class);

        return temporada;
    }
}
