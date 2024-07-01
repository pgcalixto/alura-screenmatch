package br.com.alura.screenmatch.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deepl.api.DeepLException;
import com.deepl.api.TextResult;
import com.deepl.api.Translator;

@Service
public class DeeplApi {

    @Autowired
    private Translator translator;

    private static final Logger LOGGER = LoggerFactory.getLogger(DeeplApi.class);

    private static final String SOURCE_LANGUAGE = "en";

    private static final String TARGET_LANGUAGE = "pt-BR";

    public String obterTraducao(String texto) {
        try {
            final TextResult textResult = translator.translateText(
                    texto,
                    SOURCE_LANGUAGE,
                    TARGET_LANGUAGE);

            return textResult.getText();
        } catch (DeepLException | InterruptedException e) {
            LOGGER.error("Falha ao traduzir texto", e);
        }

        return null;
    }
}
