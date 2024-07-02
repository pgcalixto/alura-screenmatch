package br.com.alura.screenmatch.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.alura.screenmatch.dto.SerieDto;
import br.com.alura.screenmatch.model.Serie;

@Mapper(componentModel = "spring")
public interface SerieMapper {

    SerieMapper INSTANCE = Mappers.getMapper( SerieMapper.class );

    Serie serieDtoToSerie(SerieDto serie);
}
