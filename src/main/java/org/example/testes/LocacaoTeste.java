package org.example.testes;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.model.*;
import org.example.servico.LocacaoServico;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LocacaoTeste {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("locadoraJogos");
        EntityManager em = emf.createEntityManager();
        Scanner sc = new Scanner(System.in);
        LocacaoServico locacaoServico = new LocacaoServico(em);

        System.out.println("--- Teste de Realização de Locação ---");

        try {
            System.out.print("Digite o ID do Cliente: ");
            Long clienteId = sc.nextLong();
            Cliente cliente = em.find(Cliente.class, clienteId);
            if (cliente == null) {
                System.err.println("Erro: Cliente com ID " + clienteId + " não encontrado.");
                return;
            }

            List<ItemLocacao> itensParaAlugar = new ArrayList<>();
            char continuar = 's';

            while (continuar == 's' || continuar == 'S') {
                System.out.print("Digite o ID do Jogo: ");
                Integer jogoId = sc.nextInt();
                Jogo jogo = em.find(Jogo.class, jogoId);
                if (jogo == null) {
                    System.err.println("Aviso: Jogo com ID " + jogoId + " não encontrado.");
                    continue;
                }

                System.out.print("Digite o ID da Plataforma: ");
                Integer plataformaId = sc.nextInt();
                Plataforma plataforma = em.find(Plataforma.class, plataformaId);
                if (plataforma == null) {
                    System.err.println("Aviso: Plataforma com ID " + plataformaId + " não encontrada.");
                    continue;
                }

                System.out.print("Por quantos dias deseja alugar? ");
                int dias = sc.nextInt();

                ItemLocacao item = new ItemLocacao();
                item.setJogoPlataforma(new JogoPlataforma(jogo, plataforma, null));
                item.setDias(dias);
                item.setQuantidade(1);
                itensParaAlugar.add(item);

                System.out.print("Deseja adicionar outro jogo? (s/n): ");
                continuar = sc.next().charAt(0);
            }

            if (!itensParaAlugar.isEmpty()) {
                locacaoServico.realizarLocacao(cliente, itensParaAlugar);
                System.out.println("\n Locação para o cliente '" + cliente.getNome() + "' realizada com sucesso!");
            } else {
                System.out.println("Nenhum item válido foi adicionado. Locação cancelada.");
            }

        } catch (Exception e) {
            System.err.println("\n Erro ao realizar locação: " + e.getMessage());
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
            sc.close();
        }
    }
}