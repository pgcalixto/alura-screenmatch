package br.com.alura.screenmatch.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.alura.screenmatch.dto.TemporadaDto;
import br.com.alura.screenmatch.model.Temporada;

@Mapper(componentModel = "spring", uses = EpisodioMapper.class)
public interface TemporadaMapper {

    @Mapping(target = "episodios", qualifiedByName = "episodioDtosToEpisodios")
    Temporada temporadaDtoToTemporada(TemporadaDto temporadaDto);

}
