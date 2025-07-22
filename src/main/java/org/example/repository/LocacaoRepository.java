package org.example.repository;

import jakarta.persistence.EntityManager;
import org.example.model.JogoPlataforma;
import org.example.model.JogoPlataformaId;
import org.example.model.Locacao;

// Responsabilidade: Apenas acessar o banco de dados para a entidade Locacao e relacionadas.
public class LocacaoRepository {

    private final EntityManager em;

    public LocacaoRepository(EntityManager em) {
        this.em = em;
    }

    public JogoPlataforma buscarJogoPlataformaPorId(JogoPlataformaId id) {
        return em.find(JogoPlataforma.class, id);
    }

    public void salvar(Locacao locacao) {
        em.persist(locacao);
    }
}