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

        while (true) {
            System.out.println("\n---Cadastro de Cliente ---");
            System.out.println("Digite seus dados , ou 'sair' para encerrar o cadastro :    ");

            System.out.print("Nome: ");
            String nome = sc.nextLine().trim();
            if (nome.equalsIgnoreCase("sair")) {
                break;
            }

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
                System.out.println("\nCliente cadastrado com sucesso! ID: " + novoCliente.getId());

            } catch (Exception e) {
                System.err.println(" Erro ao cadastrar cliente: " + e.getMessage());
            }

            System.out.println("------------------------------------");
        }

        System.out.println("Encerrando...");
        em.close();
        emf.close();
        sc.close();
    }
}