package org.example.testes;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.model.Cliente;
import org.example.servico.ClienteServico;

import java.util.Scanner;

public class ClienteTeste {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("locadoraJogos");
        EntityManager em = emf.createEntityManager();
        Scanner sc = new Scanner(System.in);
        ClienteServico clienteServico = new ClienteServico(em);

        System.out.println("--- Teste de Cadastro de Cliente ---");

        System.out.print("Nome: ");
        String nome = sc.nextLine().trim();

        System.out.print("Email: ");
        String email = sc.nextLine().trim();

        System.out.print("Telefone: ");
        String telefone = sc.nextLine().trim();

        System.out.print("Senha: ");
        String senha = sc.nextLine().trim();

        try {
            Cliente novoCliente = new Cliente();
            novoCliente.setNome(nome);
            novoCliente.setEmail(email);
            novoCliente.setTelefone(telefone);
            novoCliente.setSenha(senha);

            clienteServico.cadastrarCliente(novoCliente);
            System.out.println("\n✅ Cliente cadastrado com sucesso! ID: " + novoCliente.getId());

        } catch (Exception e) {
            System.err.println("\n❌ Erro ao cadastrar cliente: " + e.getMessage());
        } finally {
            em.close();
            emf.close();
            sc.close();
        }
    }
}