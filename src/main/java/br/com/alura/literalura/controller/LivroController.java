package br.com.alura.literalura.controller;

import br.com.alura.literalura.model.Livro;
import br.com.alura.literalura.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/livros")
public class LivroController {
    @Autowired
    private LivroService livroService;

    @GetMapping("/buscar")
    public Livro buscarLivroPorTitulo(@RequestParam String titulo) {
        return livroService.buscarLivroTitulo(titulo);
    }

    // Implementar os métodos para listar livros, autores, etc.
}
