package br.com.alura.screenmatch.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.screenmatch.dto.SerieDto;
import br.com.alura.screenmatch.service.SerieService;

@RestController
@RequestMapping("/series")
class SerieController {

    @Autowired
    private SerieService serieService;

    @GetMapping
    List<SerieDto> obterTodasSeries() {
        return serieService.obterTodasSeries();
    }

}
