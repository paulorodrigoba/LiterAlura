package br.com.LiterAlura.api.service;

import br.com.LiterAlura.api.autor.Autor;
import br.com.LiterAlura.api.dto.DadosLivro;
import br.com.LiterAlura.api.livro.Livro;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConsumoApi {

    public List<Livro> listaDeLivros(JsonNode results) {
        List<Livro> livros = new ArrayList<>();
        for (JsonNode livroNode : results) {
            Long id = livroNode.get("id").asLong();
            String titulo = livroNode.get("title").asText();

            List<Autor> autores = new ArrayList<>();
            for (JsonNode autorNode : livroNode.get("authors")) {
                String nome = autorNode.get("name").asText();
                int anoNascimento = autorNode.get("birth_year").asInt();
                int anoMorte = autorNode.get("death_year").asInt();
                autores.add(new Autor(null, nome, anoNascimento, anoMorte));
            }

            DadosLivro dadosLivro = new DadosLivro(titulo, autores);
            Livro livro = new Livro(dadosLivro);
            livro.setId(id);
            livros.add(livro);
        }
        return livros;
    }
}
