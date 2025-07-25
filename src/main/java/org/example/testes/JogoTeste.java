package org.example.testes;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.servico.JogoServico;

import java.math.BigDecimal;
import java.util.Scanner;

public class JogoTeste {
    public static void main(String[] args) {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("locadoraJogos");
        EntityManager manager = factory.createEntityManager();
        Scanner leitura = new Scanner(System.in);
        JogoServico jogoServico = new JogoServico(manager);

        while (true) {
            System.out.println("\n--- Cadastro de Jogo ---");
            System.out.println("Digite os dados do jogo, ou 'sair' para encerrar o cadastro:");

            System.out.print("Título do Jogo: ");
            String titulo = leitura.nextLine().trim();
            if (titulo.equalsIgnoreCase("sair")) {
                break;
            }

            System.out.print("Nome da Plataforma: ");
            String plataforma = leitura.nextLine().trim();

            System.out.print("Preço da diária: ");
            BigDecimal preco = leitura.nextBigDecimal();
            leitura.nextLine(); // Limpa o buffer do scanner após ler um número

            try {
                jogoServico.cadastrarJogoComPlataforma(titulo, plataforma, preco);
                System.out.println("\nJogo cadastrado na plataforma com sucesso!");

            } catch (Exception e) {
                System.err.println("\nErro ao cadastrar jogo: " + e.getMessage());
                e.printStackTrace();
            }
            System.out.println("------------------------------------");
        }

        System.out.println("Encerrando...");
        manager.close();
        factory.close();
        leitura.close();
    }
}