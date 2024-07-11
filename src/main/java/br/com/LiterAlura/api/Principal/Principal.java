package br.com.LiterAlura.api.Principal;

import br.com.LiterAlura.api.livro.Livro;
import br.com.LiterAlura.api.service.ConverterDados;
import br.com.LiterAlura.api.service.LivroService;
import br.com.LiterAlura.api.autor.Autor;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {

    private final Scanner leitura = new Scanner(System.in);
    private final LivroService livroService = new LivroService();
    private final ConverterDados converterDados = new ConverterDados();

    public void exibeMenu() {
        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Buscar Livro pelo Título");
            System.out.println("2. Listar Livros Registrados");
            System.out.println("3. Listar Nossos Autores");
            System.out.println("4. Listar Autores por Ano de Nascimento");
            System.out.println("0. Sair");

            int opcao = leitura.nextInt();
            leitura.nextLine();  // consumir a nova linha

            switch (opcao) {
                case 1:
                    buscarLivroPeloTitulo();
                    break;
                case 2:
                    listarLivrosRegistrados();
                    break;
                case 3:
                    listarNossosAutores();
                    break;
                case 4:
                    listarAutoresAno();
                    break;
                case 0:
                    System.exit(0);
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private void buscarLivroPeloTitulo() {
        System.out.print("Digite o título do livro: ");
        String titulo = leitura.nextLine();
        try {
            List<Livro> livros = converterDados.buscarLivro(titulo);
            livroService.salvarLivros(livros);
            System.out.println("Livros salvos com sucesso.");
        } catch (IOException | InterruptedException e) {
            System.out.println("Erro ao buscar livros: " + e.getMessage());
        }
    }

    private void listarLivrosRegistrados() {
        List<Livro> livros = livroService.listarLivros();
        livros.forEach(livro -> {
            System.out.println("Título: " + livro.getTitulo());
            System.out.println("Autores: " + livro.getAutores().stream().map(Autor::getNome).collect(Collectors.joining(", ")));
            System.out.println("----");
        });
    }

    private void listarNossosAutores() {
        List<String> autores = livroService.listarAutores();
        autores.forEach(System.out::println);
    }

    private void listarAutoresAno() {
        System.out.print("Digite o ano de nascimento: ");
        int ano = leitura.nextInt();
        leitura.nextLine();  // consumir a nova linha
        List<String> autores = livroService.listarAutoresPorAno(ano);
        autores.forEach(System.out::println);
    }
}
