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

        System.out.println("--- Teste de Cadastro de Jogo ---");

        System.out.print("Título do Jogo: ");
        String titulo = sc.nextLine().trim();

        System.out.print("Nome da Plataforma (Ex: PS5, PC): ");
        String plataforma = sc.nextLine().trim();

        System.out.print("Preço da diária (Ex: 15.50): ");
        BigDecimal preco = sc.nextBigDecimal();

        try {
            jogoServico.cadastrarJogoComPlataforma(titulo, plataforma, preco);
            System.out.println("\nJogo cadastrado na plataforma com sucesso!");

        } catch (Exception e) {
            System.err.println("\nErro ao cadastrar jogo: " + e.getMessage());
        } finally {
            em.close();
            emf.close();
            sc.close();
        }
    }
}