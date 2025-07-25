package org.example.repository;

import jakarta.persistence.EntityManager;
import org.example.model.Jogo;
import org.example.model.JogoPlataforma;
import org.example.model.Plataforma;

public class JogoRepository {

    private final EntityManager manager;

    public JogoRepository(EntityManager em) {
        this.manager = em;
    }

    public Plataforma buscarPlataformaPorNome(String nome) {
        return manager.createQuery("SELECT p FROM Plataforma p WHERE p.nome = :nome", Plataforma.class)
                .setParameter("nome", nome)
                .getResultStream()
                .findFirst()
                .orElse(null);
    }

    public Jogo buscarJogoPorTitulo(String titulo) {
        return manager.createQuery("SELECT j FROM Jogo j WHERE j.titulo = :titulo", Jogo.class)
                .setParameter("titulo", titulo)
                .getResultStream()
                .findFirst()
                .orElse(null);
    }

    public void salvarPlataforma(Plataforma plataforma) {
        manager.persist(plataforma);
    }

    public void salvarJogo(Jogo jogo) {
        manager.persist(jogo);
    }

    public void salvarJogoPlataforma(JogoPlataforma jogoPlataforma) {
        manager.persist(jogoPlataforma);
    }
}