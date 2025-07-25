package org.example.servico;

import jakarta.persistence.EntityManager;
import org.example.model.Jogo;
import org.example.model.JogoPlataforma;
import org.example.model.Plataforma;
import org.example.repository.JogoRepository;

import java.math.BigDecimal;


public class JogoServico {

    private final EntityManager manager;
    private final JogoRepository jogoRepository;

    public JogoServico(EntityManager em) {
        this.manager = em;
        this.jogoRepository = new JogoRepository(em);
    }

    public void cadastrarJogoComPlataforma(String tituloJogo, String nomePlataforma, BigDecimal precoDiario) {
        manager.getTransaction().begin();

        Plataforma plataforma = jogoRepository.buscarPlataformaPorNome(nomePlataforma);
        if (plataforma == null) {
            plataforma = new Plataforma(nomePlataforma);
            jogoRepository.salvarPlataforma(plataforma);
        }

        Jogo jogo = jogoRepository.buscarJogoPorTitulo(tituloJogo);
        if (jogo == null) {
            jogo = new Jogo(tituloJogo);
            jogoRepository.salvarJogo(jogo);
        }

        JogoPlataforma jogoPlataforma = new JogoPlataforma(jogo, plataforma, precoDiario);
        jogoRepository.salvarJogoPlataforma(jogoPlataforma);

        manager.getTransaction().commit();
    }
}