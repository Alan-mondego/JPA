package org.example.testes;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.servico.JogoServico;

import java.math.BigDecimal;
import java.util.Scanner;

public class JogoTeste {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("locadoraJogos");
        EntityManager em = emf.createEntityManager();
        Scanner sc = new Scanner(System.in);
        JogoServico jogoServico = new JogoServico(em);

        while (true) {
            System.out.println("\n---Cadastro de Jogo ---");
            System.out.println("Digite os dados do jogo , ou 'sair' para encerrar o cadastro :    ");

            System.out.print("Título do Jogo: ");
            String titulo = sc.nextLine().trim();
            if (titulo.equalsIgnoreCase("sair")) {
                break;
            }

            System.out.print("Nome da Plataforma :  ");
            String plataforma = sc.nextLine().trim();

            System.out.print("Preço da diária : ");
            BigDecimal preco = sc.nextBigDecimal();
            sc.nextLine();

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
        em.close();
        emf.close();
        sc.close();
    }
}