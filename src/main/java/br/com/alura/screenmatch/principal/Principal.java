package br.com.alura.screenmatch.principal;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import br.com.alura.screenmatch.model.Serie;
import br.com.alura.screenmatch.model.Temporada;
import br.com.alura.screenmatch.repository.SerieRepository;
import br.com.alura.screenmatch.service.ConsumoApi;

@Service
public class Principal implements CommandLineRunner {

    @Autowired
    private ConsumoApi consumoApi;

    @Autowired
    private SerieRepository serieRepository;

    private Scanner scanner = new Scanner(System.in);

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Primeiro projeto Spring sem Web");

        exibeMenu();
    }

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
        final String nomeSerie = scanner.nextLine();

        final String nomeSerieFormatada = nomeSerie.replace(" ", "+");

        final Serie serie = consumoApi.getSerie(nomeSerieFormatada);

        serieRepository.save(serie);

        System.out.println(serie);

        return serie;
    }

    private void buscarEpisodiosSerie() {

        final List<Serie> series = serieRepository.findAll();

        final Set<String> nomeSeries = series.stream()
                .map(Serie::getTitulo)
                .map(String::toLowerCase)
                .collect(Collectors.toSet());

        System.out.println(
                "Digite o nome da série para buscar seus episódios: " + nomeSeries.toString());

        final String nomeSerie = scanner.nextLine();

        final Serie serieBuscada = series.stream()
                .filter(serie -> serie.getTitulo().toLowerCase().contains(nomeSerie))
                .findFirst()
                .orElse(null);

        if (serieBuscada == null) {
            System.out.println("Série não encontrada. Tente novamente.");
            return;
        }

        final String nomeSerieFormatada = nomeSerie.replace(" ", "+");

        final List<Temporada> temporadas = IntStream
                .range(1, serieBuscada.getTotalTemporadas() + 1)
                .mapToObj(i -> consumoApi.getTemporada(nomeSerieFormatada, i))
                .collect(Collectors.toList());

        temporadas.forEach(System.out::println);
    }

    private void listarSeriesBuscadas() {

        final List<Serie> series = serieRepository.findAll();

        series.stream()
                .sorted(Comparator.comparing(
                        serie -> !serie.getGeneros().isEmpty()
                                ? serie.getGeneros().get(0)
                                : null,
                        Comparator.nullsLast(Comparator.naturalOrder())
                ))
                .forEach(System.out::println);
    }
}
