package br.com.alura.screenmatch.service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import br.com.alura.screenmatch.model.Genero;

public class GeneroDeserializer extends JsonDeserializer<List<Genero>> {

    @Override
    public List<Genero> deserialize(JsonParser parser, DeserializationContext context)
            throws IOException, JsonProcessingException {

        String genero = parser.getText();

        String[] generos = genero.split(",");

        return Arrays.stream(generos)
                .map(String::trim)
                .map(Genero::fromCategoriaImdb)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}
