package br.com.LiterAlura.api.livro;

import br.com.LiterAlura.api.autor.Autor;
import br.com.LiterAlura.api.dto.DadosLivro;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Entity
@Table(name = "livros")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Livro {
    @Id
    private Long id;

    private String titulo;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "livro_autor",
            joinColumns = @JoinColumn(name = "livro_id"),
            inverseJoinColumns = @JoinColumn(name = "autor_id"))
    private List<Autor> autores;

    @ElementCollection
    private List<String> tradutores;

    @ElementCollection
    private List<String> assuntos;

    @ElementCollection
    private List<String> estantes;

    @ElementCollection
    private List<String> linguas;

    private boolean copyright;
    private String tipoMidia;

    @ElementCollection
    private Map<String, String> formatos;

    private int quantidadeDownloads;

    public Livro(DadosLivro dados) {
        this.titulo = dados.titulo();
        this.autores = dados.autores();
        this.tradutores = dados.tradutores();
        this.assuntos = dados.assuntos();
        this.estantes = dados.estantes();
        this.linguas = dados.linguas();
        this.copyright = dados.copyright();
        this.tipoMidia = dados.tipoMidia();
        this.formatos = dados.formatos();
        this.quantidadeDownloads = dados.quantidadeDownloads();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public List<Autor> getAutores() {
        return autores;
    }
}
