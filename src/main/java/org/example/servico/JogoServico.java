package org.example.servico;

import jakarta.persistence.EntityManager;
import org.example.model.Jogo;
import org.example.model.JogoPlataforma;
import org.example.model.Plataforma;
import org.example.repository.JogoRepository;

import java.math.BigDecimal;

// Responsabilidade: Orquestrar a lógica de negócio para cadastrar um jogo.
public class JogoServico {

    private final EntityManager em;
    private final JogoRepository jogoRepository;

    public JogoServico(EntityManager em) {
        this.em = em;
        this.jogoRepository = new JogoRepository(em);
    }

    public void cadastrarJogoComPlataforma(String tituloJogo, String nomePlataforma, BigDecimal precoDiario) {
        em.getTransaction().begin();

        // Lógica de negócio: "Buscar ou criar"
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

        em.getTransaction().commit();
    }
}