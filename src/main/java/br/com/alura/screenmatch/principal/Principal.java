package br.com.alura.screenmatch.principal;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import br.com.alura.screenmatch.model.Episodio;
import br.com.alura.screenmatch.model.Genero;
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
                4 - Buscar séries por título
                5 - Buscar séries por ator e avaliação
                6 - Buscar as 5 séries mais bem avaliadas
                7 - Buscar séries por gênero
                8 - Buscar séries por número máximo de temporadas e avaliação
                9 - Buscar episódios por trecho
                10 - Buscar 5 eposódios mais bem avaliados por série
                11 - Buscar episódios a partir de um ano

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

                case 4:
                    buscarSeriePorTitulo();
                    break;

                case 5:
                    buscarSeriePorAtorEAvaliacao();
                    break;

                case 6:
                    buscarSeriesMaisBemAvaliadas();
                    break;

                case 7:
                    buscarSeriesPorGenero();
                    break;

                case 8:
                    buscarSeriesPorMaximoDeTemporadasEAvaliacao();
                    break;

                case 9:
                    buscarEpisodioPorTrecho();
                    break;

                case 10:
                    buscarEpisodiosMaisBemAvaliadosPorSerie();
                    break;

                case 11:
                    buscarEpisodiosAPartirDeAno();
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
                "Digite o trecho do título da série para buscar seus episódios: " +
                        nomeSeries.toString());

        final String trechoTituloSerie = scanner.nextLine();

        final Optional<Serie> optionalSerieEncontrada = serieRepository
                .findByTituloContainingIgnoreCase(trechoTituloSerie);

        if (optionalSerieEncontrada.isEmpty()) {
            System.out.println("Série não encontrada.");
            return;
        }

        final Serie serieEncontrada = optionalSerieEncontrada.get();

        final String nomeSerie = serieEncontrada.getTitulo().toLowerCase().replace(" ", "+");

        final List<Temporada> temporadas = IntStream
                .range(1, serieEncontrada.getTotalTemporadas() + 1)
                .mapToObj(i -> consumoApi.getTemporada(nomeSerie, i))
                .collect(Collectors.toList());

        final List<Episodio> episodios = temporadas
                .stream()
                .flatMap(temporada -> temporada.episodios().stream())
                .collect(Collectors.toList());

        serieEncontrada.setEpisodios(episodios);

        serieRepository.save(serieEncontrada);

        temporadas.forEach(System.out::println);
    }

    private void listarSeriesBuscadas() {

        final List<Serie> series = serieRepository.findAll();

        series.stream()
                .sorted(Comparator.comparing(
                        serie -> !serie.getGeneros().isEmpty()
                                ? serie.getGeneros().get(0)
                                : null,
                        Comparator.nullsLast(Comparator.naturalOrder())))
                .forEach(System.out::println);
    }

    private Optional<Serie> buscarSeriePorTitulo() {

        System.out.println(
                "Digite o trecho do título da série para buscar:");

        final String trechoTituloSerie = scanner.nextLine();

        final Optional<Serie> optionalSerieEncontrada = serieRepository
                .findByTituloContainingIgnoreCase(trechoTituloSerie);

        if (optionalSerieEncontrada.isEmpty()) {
            System.out.println("Série não encontrada.");

            return optionalSerieEncontrada;
        }

        System.out.println(optionalSerieEncontrada.get());

        return optionalSerieEncontrada;
    }

    private void buscarSeriePorAtorEAvaliacao() {

        System.out.println(
                "Digite o nome de algum(a) ator(riz) das séries que deseja buscar:");

        final String ator = scanner.nextLine();

        System.out.println(
                "Digite a avaliação mínima das séries que deseja buscar:");

        final Double avaliacao = scanner.nextDouble();
        scanner.nextLine();

        final List<Serie> series = serieRepository
                .findByAtoresContainingIgnoreCaseAndAvaliacaoGreaterThanEqual(
                        ator,
                        avaliacao);

        if (series.isEmpty()) {
            System.out.println("Nenhuma série encontrada.");
            return;
        }

        series.forEach(System.out::println);
    }

    private void buscarSeriesMaisBemAvaliadas() {

        final List<Serie> seriesMaisBemAvaliadas = serieRepository.findFirst5ByOrderByAvaliacaoDesc();

        seriesMaisBemAvaliadas.forEach(System.out::println);
    }

    private void buscarSeriesPorGenero() {

        final List<Genero> generos = serieRepository.findDistinctGeneros();

        final List<String> nomesGeneros = generos.stream()
                .map(Genero::getNome)
                .collect(Collectors.toList());

        System.out.println(
                "Digite o nome de algum gênero: " + nomesGeneros.toString());

        final String nomeGenero = scanner.nextLine();

        final Genero genero = Genero.fromNome(nomeGenero);

        final List<Serie> series = serieRepository.findByGenerosIn(genero);

        series.forEach(System.out::println);
    }

    private void buscarSeriesPorMaximoDeTemporadasEAvaliacao() {

        System.out.println(
            "Digite o número máximo de temporadas das séries que deseja buscar:");

        final int numMaximoTemporadas = scanner.nextInt();
        scanner.nextLine();

        System.out.println(
                "Digite a avaliação mínima das séries que deseja buscar:");

        final Double avaliacao = scanner.nextDouble();
        scanner.nextLine();

        final List<Serie> series = serieRepository
                .findByTotalTemporadasAvaliacao(numMaximoTemporadas, avaliacao);

        if (series.isEmpty()) {
            System.out.println("Nenhuma série encontrada.");
            return;
        }

        series.forEach(System.out::println);
    }

    private void buscarEpisodioPorTrecho() {

        System.out.println("Qual o trecho do título do episódio para buscar?");

        final String trechoTituloEpisodio = scanner.nextLine();

        List<Episodio> episodios = serieRepository.findEpisodiosPorTrecho(trechoTituloEpisodio);

        episodios.forEach((episodio) -> {
            System.out.printf("Série \"%s\", Temporada %s, Episodio %d: %s\n",
                    episodio.getSerie().getTitulo(),
                    episodio.getTemporada(),
                    episodio.getNumero(),
                    episodio.getTitulo());
        });
    }

    private void buscarEpisodiosMaisBemAvaliadosPorSerie() {

        Optional<Serie> optionalSerie = buscarSeriePorTitulo();

        if (optionalSerie.isEmpty()) {
            return;
        }

        final Serie serie = optionalSerie.get();

        final List<Episodio> episodiosMaisBemAvaliados = serieRepository.findFirst5EpisodiosPorSerie(serie);

        episodiosMaisBemAvaliados.forEach(System.out::println);
    }

    private void buscarEpisodiosAPartirDeAno() {

        Optional<Serie> optionalSerie = buscarSeriePorTitulo();

        if (optionalSerie.isEmpty()) {
            return;
        }

        System.out.println("Digite o ano limite de lançamento");

        final int anoMinimo = scanner.nextInt();
        scanner.nextLine();

        Serie serie = optionalSerie.get();

        final List<Episodio> episodios = serieRepository.findEpisodiosByAnoMinimo(serie, anoMinimo);

        if (episodios.isEmpty()) {
            System.out.println("Nenhum episódio encontrado a partir deste ano.");
            return;
        }

        episodios.forEach(System.out::println);
    }

}
