package br.com.alura.literalura.service;

import br.com.alura.literalura.model.Livro;
import br.com.alura.literalura.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    public void salvarLivro(Livro livro) {
        if (livro.getTitulo() != null && !livro.getTitulo().isBlank()) {
            livroRepository.save(livro);
        }
    }

    public List<Livro> listarLivrosRegistrados() {
        return livroRepository.findAll();
    }

    public Livro buscarLivroTitulo(String titulo) {
        // Implemente a lógica para buscar um livro pelo título
        return null;
    }

    public List<Livro> listarLivrosPorIdioma(String idioma) {
        return livroRepository.findByIdioma(idioma);
    }
}
