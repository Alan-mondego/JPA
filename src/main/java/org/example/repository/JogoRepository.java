package org.example.repository;

import jakarta.persistence.EntityManager;
import org.example.model.Jogo;
import org.example.model.JogoPlataforma;
import org.example.model.Plataforma;

// Responsabilidade: Apenas acessar o banco de dados para a entidade Jogo e relacionadas.
public class JogoRepository {

    private final EntityManager em;

    public JogoRepository(EntityManager em) {
        this.em = em;
    }

    public Plataforma buscarPlataformaPorNome(String nome) {
        return em.createQuery("SELECT p FROM Plataforma p WHERE p.nome = :nome", Plataforma.class)
                .setParameter("nome", nome)
                .getResultStream()
                .findFirst()
                .orElse(null);
    }

    public Jogo buscarJogoPorTitulo(String titulo) {
        return em.createQuery("SELECT j FROM Jogo j WHERE j.titulo = :titulo", Jogo.class)
                .setParameter("titulo", titulo)
                .getResultStream()
                .findFirst()
                .orElse(null);
    }

    public void salvarPlataforma(Plataforma plataforma) {
        em.persist(plataforma);
    }

    public void salvarJogo(Jogo jogo) {
        em.persist(jogo);
    }

    public void salvarJogoPlataforma(JogoPlataforma jogoPlataforma) {
        em.persist(jogoPlataforma);
    }
}