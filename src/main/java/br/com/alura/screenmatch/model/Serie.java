package br.com.alura.screenmatch.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Serie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String titulo;

    private Integer totalTemporadas;

    private Double avaliacao;

    @ElementCollection(targetClass = Genero.class, fetch = FetchType.EAGER)
    @CollectionTable
    @Enumerated(EnumType.STRING)
    // @JdbcType(PostgreSQLEnumJdbcType.class)
    private List<Genero> generos;

    private String atores;

    private String poster;

    private String sinopse;

    @OneToMany(mappedBy = "serie", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    List<Episodio> episodios = new ArrayList<Episodio>();

    public void setEpisodios(List<Episodio> episodios) {
        this.episodios = episodios;

        episodios.forEach(episodio -> episodio.setSerie(this));
    }

    @Override
    public String toString() {
        final String template = """
                Serie[
                    id=%d,
                    titulo='%s',
                    totalTemporadas=%d,
                    avaliacao=%.1f,
                    generos=%s,
                    atores='%s',
                    poster='%s',
                    sinopse='%s'
                ]""";

        return String.format(
                template,
                id,
                titulo,
                totalTemporadas,
                avaliacao,
                generos,
                atores,
                poster,
                sinopse);
    }
}
