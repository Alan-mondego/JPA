package org.example.testes;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.model.Cliente;
import org.example.servico.ClienteServico;

import java.util.Scanner;

public class ClienteTeste {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("locadoraJogos");
        EntityManager manager = factory.createEntityManager();
        Scanner leitura = new Scanner(System.in);
        ClienteServico cliServico = new ClienteServico(manager);

        while (true) {
            System.out.println("\n---Cadastro de Cliente ---");
            System.out.println("Digite seus dados , ou 'sair' para encerrar o cadastro :    ");

            System.out.print("Nome: ");
            String nome = leitura.nextLine().trim();
            if (nome.equalsIgnoreCase("sair")) {
                break;
            }

            System.out.print("Email: ");
            String email = leitura.nextLine().trim();

            System.out.print("Telefone: ");
            String telefone = leitura.nextLine().trim();

            System.out.print("Senha: ");
            String senha = leitura.nextLine().trim();

            try {
                Cliente novoCliente = new Cliente();
                novoCliente.setNome(nome);
                novoCliente.setEmail(email);
                novoCliente.setTelefone(telefone);
                novoCliente.setSenha(senha);

                cliServico.cadastrarCliente(novoCliente);
                System.out.println("\nCliente cadastrado com sucesso! ID: " + novoCliente.getId());

            } catch (Exception e) {
                System.err.println(" Erro ao cadastrar cliente: " + e.getMessage());
            }

            System.out.println("------------------------------------");
        }

        System.out.println("Encerrando...");
        manager.close();
        factory.close();
        leitura.close();
    }
}