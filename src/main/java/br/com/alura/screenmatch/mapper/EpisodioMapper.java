package br.com.alura.screenmatch.mapper;

import java.util.List;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import br.com.alura.screenmatch.dto.EpisodioDto;
import br.com.alura.screenmatch.dto.EpisodioImdbDto;
import br.com.alura.screenmatch.model.Episodio;

@Mapper(componentModel = "spring")
public interface EpisodioMapper {

    @Named("episodioImdbDtoToEpisodio")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "serie", ignore = true)
    Episodio episodioImdbDtoToEpisodio(EpisodioImdbDto episodioImdbDto);

    @Named("episodioImdbDtosToEpisodios")
    @IterableMapping(qualifiedByName="episodioImdbDtoToEpisodio")
    List<Episodio> episodioImdbDtosToEpisodios(List<EpisodioImdbDto> episodioImdbDtos);

    @Named("episodioToEpisodioDto")
    @Mapping(source = "numero", target = "numeroEpisodio")
    EpisodioDto episodioToEpisodioDto(Episodio episodio);

    @IterableMapping(qualifiedByName = "episodioToEpisodioDto")
    List<EpisodioDto> episodiosToEpisodioDtos(List<Episodio> episodios);

}
