package br.com.alura.screenmatch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.alura.screenmatch.model.Serie;
import java.util.Optional;
import java.util.List;

import br.com.alura.screenmatch.model.Episodio;
import br.com.alura.screenmatch.model.Genero;

public interface SerieRepository extends JpaRepository<Serie, Long> {

    Optional<Serie> findByTituloContainingIgnoreCase(String titulo);

    List<Serie> findByAtoresContainingIgnoreCaseAndAvaliacaoGreaterThanEqual(
            String atores,
            Double avaliacao);

    List<Serie> findFirst5ByOrderByAvaliacaoDesc();

    List<Serie> findByGenerosIn(Genero... genero);

    @Query("SELECT DISTINCT g FROM Serie s JOIN s.generos g ORDER BY g")
    List<Genero> findDistinctGeneros();

    /**
     * @deprecated Use {@link SerieRepository#findByTotalTemporadasAvaliacao(Integer, Double)}
     */
    @Deprecated
    List<Serie> findByTotalTemporadasLessThanEqualAndAvaliacaoGreaterThanEqual(
            Integer totalTemporadas,
            Double avaliacao);

    @Query("SELECT s FROM Serie s WHERE s.totalTemporadas <= :totalTemporadas AND s.avaliacao >= :avaliacao")
    List<Serie> findByTotalTemporadasAvaliacao(Integer totalTemporadas, Double avaliacao);

    @Query("SELECT e FROM Serie s JOIN s.episodios e WHERE e.titulo ILIKE %:trechoTituloEpisodio%")
    List<Episodio> findEpisodiosPorTrecho(String trechoTituloEpisodio);

}
