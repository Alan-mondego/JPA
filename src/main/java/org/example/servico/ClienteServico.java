package org.example.servico;

import jakarta.persistence.EntityManager;
import org.example.model.Cliente;
import org.example.repository.ClienteRepository;


public class ClienteServico {

    private final EntityManager manager;
    private final ClienteRepository clienteRepository;

    public ClienteServico(EntityManager em) {
        this.manager = em;
        this.clienteRepository = new ClienteRepository(em);
    }

    public void cadastrarCliente(Cliente cliente) {


        manager.getTransaction().begin();
        clienteRepository.salvar(cliente);
        manager.getTransaction().commit();
    }
}