package br.com.LiterAlura.api.dto;

import br.com.LiterAlura.api.autor.Autor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.Map;

public record DadosLivro(
        @NotBlank @NotNull String titulo,
        @NotBlank @NotNull List<Autor> autores,
        List<String> tradutores,
        List<String> assuntos,
        List<String> estantes,
        List<String> linguas,
        boolean copyright,
        String tipoMidia,
        Map<String, String> formatos,
        int quantidadeDownloads
) {
}
