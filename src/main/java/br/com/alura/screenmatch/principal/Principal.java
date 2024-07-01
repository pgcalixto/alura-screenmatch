package br.com.alura.screenmatch.principal;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alura.screenmatch.model.Episodio;
import br.com.alura.screenmatch.model.Serie;
import br.com.alura.screenmatch.model.Temporada;
import br.com.alura.screenmatch.service.ConsumoApi;

@Service
public class Principal {

    @Autowired
    private ConsumoApi consumoApi;

    private Scanner scanner = new Scanner(System.in);

    private List<Serie> series = new ArrayList<Serie>();

    public void exibeMenu() {

        final String menu = """
                MENU:
                1 - Buscar séries
                2 - Buscar episódios
                3 - Listar séries buscadas

                0 - Sair""";

        int opcao = -1;

        while (opcao != 0) {

            System.out.println(menu);
    
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 0:
                    System.out.println("Saindo...");
                    break;

                case 1:
                    buscarSerie();
                    break;

                case 2:
                    buscarEpisodiosSerie();
                    break;

                case 3:
                    listarSeriesBuscadas();
                    break;

                default:
                    System.out.println("Opção inválida");
                    break;
            }
        }
    }

    private Serie buscarSerie() {

        System.out.println("Digite o nome da série para a busca");
        String nomeSerie = scanner.nextLine();

        String nomeSerieFormatada = nomeSerie.replace(" ", "+");

        Serie serie = consumoApi.getSerie(nomeSerieFormatada);

        series.add(serie);

        System.out.println(serie);

        return serie;
    }

    private void buscarEpisodiosSerie() {

        Serie serie = buscarSerie();

        String nomeSerieFormatada = serie.titulo().replace(" ", "+");

        List<Temporada> temporadas = IntStream
                .range(1, serie.totalTemporadas() + 1)
                .mapToObj(i -> consumoApi.getTemporada(nomeSerieFormatada, i))
                .collect(Collectors.toList());

        temporadas.forEach(System.out::println);

        final List<Episodio> episodios = temporadas.stream()
                .flatMap(temporada -> temporada.episodios().stream())
                .collect(Collectors.toList());

        episodios
            .stream()
            .filter(episodio -> !episodio.avaliacao().equalsIgnoreCase("N/A"))
            .sorted(Comparator.comparing(Episodio::avaliacao).reversed())
            .limit(5)
            .forEach(System.out::println);
    }

    private void listarSeriesBuscadas() {

        series.stream()
                .sorted(Comparator.comparing(
                        serie -> !serie.generos().isEmpty()
                                ? serie.generos().get(0)
                                : null,
                        Comparator.nullsLast(Comparator.naturalOrder())
                ))
                .forEach(System.out::println);
    }
}
