package br.com.alura.screenmatch.mapper;

import java.util.List;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import br.com.alura.screenmatch.dto.EpisodioDto;
import br.com.alura.screenmatch.model.Episodio;

@Mapper(componentModel = "spring")
public interface EpisodioMapper {

    @Named("episodioDtoToEpisodio")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "serie", ignore = true)
    Episodio episodioDtoToEpisodio(EpisodioDto episodioDto);

    @Named("episodioDtosToEpisodios")
    @IterableMapping(qualifiedByName="episodioDtoToEpisodio")
    List<Episodio> episodioDtosToEpisodios(List<EpisodioDto> episodioDtos);

}
