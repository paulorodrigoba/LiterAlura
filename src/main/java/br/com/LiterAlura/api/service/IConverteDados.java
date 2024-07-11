package br.com.LiterAlura.api.service;

import br.com.LiterAlura.api.livro.Livro;

import java.io.IOException;
import java.util.List;

public interface IConverteDados {
    List<Livro> buscarLivro(String query) throws IOException, InterruptedException;
}
