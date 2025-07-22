package org.example.servico;

import jakarta.persistence.EntityManager;
import org.example.model.Cliente;
import org.example.repository.ClienteRepository;

// Responsabilidade: Orquestrar a lógica de negócio para cadastrar um cliente.
public class ClienteServico {

    private final EntityManager em;
    private final ClienteRepository clienteRepository;

    public ClienteServico(EntityManager em) {
        this.em = em;
        this.clienteRepository = new ClienteRepository(em);
    }

    public void cadastrarCliente(Cliente cliente) {
        // Lógica de negócio: Validar dados (se necessário) antes de salvar.
        // Ex: if (cliente.getEmail() == null) { throw new Exception(...) }

        em.getTransaction().begin();
        clienteRepository.salvar(cliente);
        em.getTransaction().commit();
    }
}