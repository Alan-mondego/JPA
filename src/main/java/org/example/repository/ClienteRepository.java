package org.example.repository;

import jakarta.persistence.EntityManager;
import org.example.model.Cliente;


public class ClienteRepository {

    private final EntityManager manager;

    public ClienteRepository(EntityManager em) {
        this.manager = em;
    }

    public void salvar(Cliente cliente) {
        manager.persist(cliente);
    }

    public Cliente buscarPorId(Long id) {
        return manager.find(Cliente.class, id);
    }
}