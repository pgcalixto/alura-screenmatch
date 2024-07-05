package br.com.alura.screenmatch.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.alura.screenmatch.dto.SerieDto;
import br.com.alura.screenmatch.model.Serie;

@Mapper(componentModel = "spring")
public interface SerieMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "episodios", ignore = true)
    Serie serieDtoToSerie(SerieDto serieDto);

}
