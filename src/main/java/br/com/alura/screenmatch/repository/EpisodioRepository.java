package br.com.alura.screenmatch.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alura.screenmatch.model.Episodio;

public interface EpisodioRepository extends JpaRepository<Episodio, Long> {

    List<Episodio> findBySerieId(Long serieId);

}
