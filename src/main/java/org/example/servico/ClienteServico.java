package org.example.servico;

import jakarta.persistence.EntityManager;
import org.example.model.Cliente;
import org.example.repository.ClienteRepository;


public class ClienteServico {

    private final EntityManager em;
    private final ClienteRepository clienteRepository;

    public ClienteServico(EntityManager em) {
        this.em = em;
        this.clienteRepository = new ClienteRepository(em);
    }

    public void cadastrarCliente(Cliente cliente) {


        em.getTransaction().begin();
        clienteRepository.salvar(cliente);
        em.getTransaction().commit();
    }
}