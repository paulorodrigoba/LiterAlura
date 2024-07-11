package br.com.alura.literalura.principal;

import br.com.alura.literalura.dto.DadosAutor;
import br.com.alura.literalura.model.Autor;
import br.com.alura.literalura.model.DadosLivro;
import br.com.alura.literalura.model.Livro;
import br.com.alura.literalura.service.AutorService;
import br.com.alura.literalura.service.ConsumoApi;
import br.com.alura.literalura.service.ConverteDados;
import br.com.alura.literalura.service.LivroService;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {

    private Scanner leitura = new Scanner(System.in);
    private LivroService livroService;
    private AutorService autorService;
    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();
    private final String ENDERECO = "https://gutendex.com/books/?search=";

    public Principal(LivroService livroService, AutorService autorService) {
        this.livroService = livroService;
        this.autorService = autorService;
    }

    public void exibeMenu() {
        var opcao = -1;
        while (opcao != 0) {
            var menu = """
                    1 - Buscar livro pelo título
                    2 - Listar livros registrados
                    3 - Listar nossos autores
                    4 - Listar autores em determinado ano
                    5 - Listar livros em determinado idioma
                                    
                    0 - Sair                                 
                    """;

            System.out.println(menu);
            try {
                opcao = leitura.nextInt();
                leitura.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Opção inválida. Por favor, digite um número inteiro.");
                leitura.nextLine();
                continue;
            }

            switch (opcao) {
                case 1:
                    buscarLivroTitulo();
                    break;
                case 2:
                    listarLivrosRegistrados();
                    break;
                case 3:
                    listarAutores();
                    break;
                case 4:
                    listarAutoresAno();
                    break;
                case 5:
                    listarLivrosIdioma();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }
        leitura.close();
    }

    private void buscarLivroTitulo() {
        System.out.println("Digite o título do livro:");
        String titulo = leitura.nextLine();
        DadosLivro dadosLivro = getDadosLivro(titulo);

        if (dadosLivro == null || dadosLivro.getTitulo() == null || dadosLivro.getTitulo().isBlank()) {
            System.out.println("Livro não encontrado.");
            return;
        }

        // Verifique se o livro já está no banco de dados
        if (livroService.buscarLivroTitulo(dadosLivro.getTitulo()) != null) {
            System.out.println("Livro já registrado no banco de dados.");
            return;
        }

        // Convertendo DadosAutor para Autor
        List<Autor> autores = dadosLivro.getAutores().stream()
                .map(dadosAutor -> new Autor(dadosAutor.getNome(), dadosAutor.getAnoNascimento()))
                .collect(Collectors.toList());

        Livro livro = new Livro(dadosLivro.getTitulo(), dadosLivro.getIdioma(), autores);

        livroService.salvarLivro(livro);

        System.out.println("Livro encontrado:");
        System.out.println("Título: " + dadosLivro.getTitulo());
        System.out.println("Autores:");
        for (DadosAutor dadosAutor : dadosLivro.getAutores()) {
            System.out.println("- " + dadosAutor.getNome());
        }
    }

    private DadosLivro getDadosLivro(String titulo) {
        var json = consumo.obterDados(ENDERECO + titulo.replace(" ", "%20"));
        return conversor.obterDados(json, DadosLivro.class);
    }

    private void listarLivrosRegistrados() {
        List<Livro> livros = livroService.listarLivrosRegistrados();
        System.out.println("Livros registrados:");
        for (Livro livro : livros) {
            System.out.println("- " + livro.getTitulo());
        }
    }

    private void listarAutores() {
        List<Autor> autores = autorService.listarNossosAutores();
        System.out.println("Nossos Autores:");
        for (Autor autor : autores) {
            System.out.println("- " + autor.getNome());
        }
    }

    private void listarAutoresAno() {
        System.out.println("Digite o ano:");
        int ano = leitura.nextInt();
        leitura.nextLine();

        List<Autor> autores = autorService.listarAutoresPorAnoNascimento(ano);
        System.out.println("Autores nascidos em " + ano + ":");
        for (Autor autor : autores) {
            System.out.println("- " + autor.getNome());
        }
    }

    private void listarLivrosIdioma() {
        System.out.println("Digite o idioma (português, inglês, espanhol, francês):");
        String idioma = leitura.nextLine();

        List<Livro> livros = livroService.listarLivrosPorIdioma(idioma);
        System.out.println("Livros em " + idioma + ":");
        for (Livro livro : livros) {
            System.out.println("- " + livro.getTitulo());
        }
    }
}
