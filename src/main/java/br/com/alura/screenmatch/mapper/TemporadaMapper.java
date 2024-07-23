package br.com.alura.screenmatch.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.alura.screenmatch.dto.TemporadaImdbDto;
import br.com.alura.screenmatch.model.Temporada;

@Mapper(componentModel = "spring", uses = EpisodioMapper.class)
public interface TemporadaMapper {

    @Mapping(target = "episodios", qualifiedByName = "episodioImdbDtosToEpisodios")
    Temporada temporadaImdbDtoToTemporada(TemporadaImdbDto temporadaImdbDto);

}
