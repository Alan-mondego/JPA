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
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("locadoraJogos");
        EntityManager em = emf.createEntityManager();
        Scanner sc = new Scanner(System.in);
        LocacaoServico locacaoServico = new LocacaoServico(em);

        System.out.println("--- Teste de Realização de Locação ---");
        System.out.println("IMPORTANTE: Você precisa ter cadastrado clientes e jogos anteriormente para este teste funcionar.");

        while (true) {
            System.out.print("\nDigite o ID do Cliente para a locação (ou 0 para sair): ");
            int clienteId = sc.nextInt();
            if (clienteId == 0) {
                break;
            }

            try {
                Cliente clienteParaLocacao = em.find(Cliente.class, clienteId);
                if (clienteParaLocacao == null) {
                    System.err.println("Erro: Cliente com ID " + clienteId + " não encontrado. Tente novamente.");
                    continue;
                }

                List<ItemLocacao> itensParaAlugar = new ArrayList<>();

                System.out.print("Digite o ID do Jogo: ");
                Integer jogoId = sc.nextInt();
                Jogo jogo = em.find(Jogo.class, jogoId);
                if (jogo == null) {
                    System.err.println("Erro: Jogo com ID " + jogoId + " não encontrado. Tente novamente.");
                    continue;
                }

                System.out.print("Digite o ID da Plataforma: ");
                Integer plataformaId = sc.nextInt();
                Plataforma plataforma = em.find(Plataforma.class, plataformaId);
                if (plataforma == null) {
                    System.err.println("Erro: Plataforma com ID " + plataformaId + " não encontrada. Tente novamente.");
                    continue;
                }

                System.out.print("Por quantos dias deseja alugar? ");
                int dias = sc.nextInt();

                ItemLocacao item = new ItemLocacao();
                item.setJogoPlataforma(new JogoPlataforma(jogo, plataforma, null));
                item.setDias(dias);
                item.setQuantidade(1);
                itensParaAlugar.add(item);


                Locacao locacaoSalva = locacaoServico.realizarLocacao(clienteParaLocacao, itensParaAlugar);

                BigDecimal precoFinal = BigDecimal.ZERO;
                for (ItemLocacao itemSalvo : locacaoSalva.getItens()) {

                    JogoPlataformaId jpId = new JogoPlataformaId(
                            itemSalvo.getJogoPlataforma().getJogo().getId(),
                            itemSalvo.getJogoPlataforma().getPlataforma().getId()
                    );
                    JogoPlataforma jpComPreco = em.find(JogoPlataforma.class, jpId);

                    BigDecimal precoDiario = jpComPreco.getPrecoDiario();
                    BigDecimal totalDias = new BigDecimal(itemSalvo.getDias());
                    precoFinal = precoFinal.add(precoDiario.multiply(totalDias));
                }


                System.out.println("\nLocação para o cliente '" + clienteParaLocacao.getNome() + "' realizada com sucesso!");
                System.out.println(" VALOR TOTAL DA LOCAÇÃO: R$ " + precoFinal);

            } catch (Exception e) {
                System.err.println("\n Erro ao realizar locação: " + e.getMessage());
            }
            System.out.println("------------------------------------");
        }

        System.out.println("Encerrando...");
        em.close();
        emf.close();
        sc.close();
    }
}