package br.com.alura.screenmatch.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.json.SpringHandlerInstantiator;
import org.springframework.web.client.RestTemplate;

import com.deepl.api.Translator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.cfg.HandlerInstantiator;

@Configuration
public class AppConfig {

    @Bean
    Translator translator(@Value("${deepl.api_key}") String apiKey) {
        return new Translator(apiKey);
    }

    @Bean
    RestTemplate restTemplate(ApplicationContext context) {
        HandlerInstantiator handlerInstantiator = new SpringHandlerInstantiator(
                context.getAutowireCapableBeanFactory());

        final ObjectMapper objectMapper = new Jackson2ObjectMapperBuilder()
                .handlerInstantiator(handlerInstantiator)
                .build();

        final MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setObjectMapper(objectMapper);

        final RestTemplate rt = new RestTemplate();

        rt.getMessageConverters().removeIf(m -> m.getClass().equals(MappingJackson2HttpMessageConverter.class));
        rt.getMessageConverters().add(converter);

        return rt;
    }
}
