package org.example.repository;

import jakarta.persistence.EntityManager;
import org.example.model.Cliente;

// Responsabilidade: Apenas acessar o banco de dados para a entidade Cliente.
public class ClienteRepository {

    private final EntityManager em;

    public ClienteRepository(EntityManager em) {
        this.em = em;
    }

    public void salvar(Cliente cliente) {
        em.persist(cliente);
    }

    public Cliente buscarPorId(Long id) {
        return em.find(Cliente.class, id);
    }
}