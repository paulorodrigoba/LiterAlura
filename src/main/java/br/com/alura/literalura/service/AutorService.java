package br.com.alura.literalura.service;

import br.com.alura.literalura.model.Autor;
import br.com.alura.literalura.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutorService {

    @Autowired
    private AutorRepository autorRepository;

    public Autor salvarAutor(Autor autor) {
        return autorRepository.save(autor);
    }

    public List<Autor> listarNossosAutores() {
        return autorRepository.findAll();
    }

    public List<Autor> listarAutoresPorAnoNascimento(int ano) {
        return autorRepository.findByAnoNascimento(ano);
    }

}
