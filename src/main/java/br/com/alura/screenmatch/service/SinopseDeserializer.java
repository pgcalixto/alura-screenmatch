package br.com.alura.screenmatch.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

@Component
public class SinopseDeserializer extends JsonDeserializer<String> {

    @Autowired
    private DeeplApi deeplApi;

    @Override
    public String deserialize(JsonParser parser, DeserializationContext context)
            throws IOException, JsonProcessingException {

        String sinopse = parser.getText();

        String sinopseTraduzida = deeplApi.obterTraducao(sinopse);

        return sinopseTraduzida;
    }
}
