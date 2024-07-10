package br.com.alura.screenmatch.service;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class DoubleWithInvalidAsNullDeserializer extends JsonDeserializer<Double> {

    @Override
    public Double deserialize(JsonParser parser, DeserializationContext context)
            throws IOException, JsonProcessingException {

        String valor = parser.getText();

        try {
            return Double.valueOf(valor);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
