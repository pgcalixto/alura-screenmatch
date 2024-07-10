package br.com.alura.screenmatch.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Episodio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Serie serie;

    private int temporada;

    private String titulo;

    private Integer numero;

    private Double avaliacao;

    private String dataLancamento;

    @Override
    public String toString() {
        final String template = """
                Episodio[
                    id=%d,
                    temporada=%d,
                    titulo='%s',
                    numero=%d,
                    avaliacao=%.1f,
                    dataLancamento='%s'
                ]
                """;

        return String.format(
                template,
                id,
                temporada,
                titulo,
                numero,
                avaliacao,
                dataLancamento);
    }

}
