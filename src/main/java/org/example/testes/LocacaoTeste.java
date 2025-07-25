package org.example.testes;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.model.*;
import org.example.servico.LocacaoServico;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LocacaoTeste {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("locadoraJogos");
        EntityManager manager = factory.createEntityManager();
        Scanner leitura = new Scanner(System.in);
        LocacaoServico locacaoServico = new LocacaoServico(manager);

        System.out.println("---  Realização de Locação ---");
        System.out.println("Somente clientes cadastrados podem realizar locação !");

        while (true) {
            System.out.print("\nDigite o ID do Cliente para a locação , ou digite 'sair' para encerrar : ");
            String input = leitura.nextLine().trim();
            if (input.equalsIgnoreCase("sair")) {
                break;
            }

            try {
                int clienteId = Integer.parseInt(input);
                Cliente clienteParaLocacao = manager.find(Cliente.class, clienteId);
                if (clienteParaLocacao == null) {
                    System.err.println("Erro: Cliente com ID " + clienteId + " não encontrado. Tente novamente.");
                    continue;
                }

                List<ItemLocacao> itensParaAlugar = new ArrayList<>();

                System.out.print("Digite o ID do Jogo: ");
                int jogoId = Integer.parseInt(leitura.nextLine().trim());
                Jogo jogo = manager.find(Jogo.class, jogoId);

                System.out.print("Digite o ID da Plataforma: ");
                int plataformaId = Integer.parseInt(leitura.nextLine().trim());
                Plataforma plataforma = manager.find(Plataforma.class, plataformaId);

                if (jogo == null || plataforma == null) {
                    System.err.println("Erro: Jogo ou Plataforma não encontrado. Tente novamente.");
                    continue;
                }

                System.out.print("Por quantos dias deseja alugar? ");
                int dias = Integer.parseInt(leitura.nextLine().trim());


                ItemLocacao item = new ItemLocacao();
                item.setJogoPlataforma(new JogoPlataforma(jogo, plataforma, null));
                item.setDias(dias);
                item.setQuantidade(1);
                itensParaAlugar.add(item);


                Locacao locacaoSalva = locacaoServico.realizarLocacao(clienteParaLocacao, itensParaAlugar);


                BigDecimal precoFinal = locacaoServico.calcularPrecoTotal(locacaoSalva);

                System.out.println("\nLocação para o cliente '" + clienteParaLocacao.getNome() + "' realizada com sucesso!");
                System.out.println("ID da Locação: " + locacaoSalva.getId());
                System.out.println("VALOR TOTAL DA LOCAÇÃO: R$ " + precoFinal);

            } catch (NumberFormatException e) {
                System.err.println("Erro: Por favor, digite um número de ID válido.");
            } catch (Exception e) {
                System.err.println("\n--- OCORREU UM ERRO ---");
                System.err.println("Mensagem: " + e.getMessage());

            }
            System.out.println("------------------------------------");
        }

        System.out.println("Encerrando...");
        manager.close();
        factory.close();
        leitura.close();
    }
}