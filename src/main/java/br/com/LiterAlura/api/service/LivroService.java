package br.com.LiterAlura.api.service;

import br.com.LiterAlura.api.autor.Autor;
import br.com.LiterAlura.api.livro.Livro;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;
import java.util.stream.Collectors;

public class LivroService {
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence-unit");

    public void salvarLivros(List<Livro> livros) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        for (Livro livro : livros) {
            em.persist(livro);
        }
        em.getTransaction().commit();
        em.close();
    }

    public List<Livro> listarLivros() {
        EntityManager em = emf.createEntityManager();
        List<Livro> livros = em.createQuery("SELECT l FROM Livro l", Livro.class).getResultList();
        em.close();
        return livros;
    }

    public List<String> listarAutores() {
        EntityManager em = emf.createEntityManager();
        List<Autor> autores = em.createQuery("SELECT DISTINCT a FROM Autor a", Autor.class).getResultList();
        em.close();
        return autores.stream().map(Autor::getNome).collect(Collectors.toList());
    }

    public List<String> listarAutoresPorAno(int ano) {
        EntityManager em = emf.createEntityManager();
        List<Autor> autores = em.createQuery("SELECT a FROM Autor a WHERE a.anoNascimento = :ano", Autor.class)
                .setParameter("ano", ano)
                .getResultList();
        em.close();
        return autores.stream().map(Autor::getNome).collect(Collectors.toList());
    }
}
