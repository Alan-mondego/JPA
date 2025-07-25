package org.example.repository;

import jakarta.persistence.EntityManager;
import org.example.model.JogoPlataforma;
import org.example.model.JogoPlataformaId;
import org.example.model.Locacao;


public class LocacaoRepository {

    private final EntityManager manager;

    public LocacaoRepository(EntityManager em) {
        this.manager = em;
    }

    public JogoPlataforma buscarJogoPlataformaPorId(JogoPlataformaId id) {
        return manager.find(JogoPlataforma.class, id);
    }

    public void salvar(Locacao locacao) {
        manager.persist(locacao);
    }
}