package br.com.alura.screenmatch.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.screenmatch.dto.EpisodioDto;
import br.com.alura.screenmatch.dto.SerieDto;
import br.com.alura.screenmatch.service.EpisodioService;
import br.com.alura.screenmatch.service.SerieService;

@RestController
@RequestMapping("/series")
class SerieController {

    @Autowired
    private EpisodioService episodioService;

    @Autowired
    private SerieService serieService;

    @GetMapping
    List<SerieDto> obterTodasSeries() {
        return serieService.obterTodasSeries();
    }

    @GetMapping("/top5")
    List<SerieDto> obterSeriesMaisBemAvaliadas() {
        return serieService.obterSeriesMaisBemAvaliadas();
    }

    @GetMapping("/lancamentos")
    public List<SerieDto> obterLancamentos() {
        return serieService.obterLancamentos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SerieDto> obterSeriePorId(@PathVariable Long id) {

        final SerieDto serieDto = serieService.obterSeriePorId(id);

        if (serieDto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(serieDto, HttpStatus.OK);
    }

    @GetMapping("/{id}/temporadas/todas")
    public List<EpisodioDto> obterTodasTemporadasDeSerie(@PathVariable Long id) {
        return episodioService.obterTodasTemporadasDeSerie(id);
    }

    @GetMapping("/{id}/temporadas/{temporada}")
    public List<EpisodioDto> obterTemporadaDeSerie(
            @PathVariable Long id,
            @PathVariable int temporada) {

        return episodioService.obterTemporadaDeSerie(id, temporada);
    }

}
