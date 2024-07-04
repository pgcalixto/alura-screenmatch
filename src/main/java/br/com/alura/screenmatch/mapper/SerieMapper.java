package br.com.alura.screenmatch.mapper;

import org.mapstruct.Mapper;

import br.com.alura.screenmatch.dto.SerieDto;
import br.com.alura.screenmatch.model.Serie;

@Mapper(componentModel = "spring")
public interface SerieMapper {

    Serie serieDtoToSerie(SerieDto serie);

}
