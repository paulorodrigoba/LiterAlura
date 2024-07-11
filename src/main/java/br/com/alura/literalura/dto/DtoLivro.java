package br.com.alura.literalura.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public record DtoLivro(
        @JsonAlias("id") int registro,
        @JsonAlias("title")String titulo,
        @JsonAlias("authors")String autor,
        @JsonAlias("languages")String idioma
) {
}

