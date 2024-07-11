package br.com.alura.literalura.model;

import br.com.alura.literalura.dto.DadosAutor;

import java.util.List;

public class DadosLivro {
    private String titulo;
    private List<DadosAutor> autores;

    // Getters e Setters
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<DadosAutor> getAutores() {
        return autores;
    }

    public void setAutores(List<DadosAutor> autores) {
        this.autores = autores;
    }

    public String getIdioma() {
        return null;
    }
}
