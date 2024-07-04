package br.com.alura.screenmatch.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
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

    private String avaliacao;

    @ElementCollection(targetClass = Genero.class)
    @CollectionTable
    @Enumerated(EnumType.STRING)
    // @JdbcType(PostgreSQLEnumJdbcType.class)
    private List<Genero> generos;

    private String atores;

    private String poster;

    private String sinopse;

    @Transient
    List<Episodio> episodios = new ArrayList<Episodio>();
}
