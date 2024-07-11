package br.com.LiterAlura.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosAutor(
        @NotBlank String nome,
        @NotNull int anoNascimento,
        int anoMorte
) {
}
