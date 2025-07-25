package org.example.servico;

import jakarta.persistence.EntityManager;
import org.example.model.*;
import org.example.repository.LocacaoRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class LocacaoServico {

    private final EntityManager manager;
    private final LocacaoRepository locacaoRepository;


    public LocacaoServico(EntityManager em) {
        this.manager = em;
        this.locacaoRepository = new LocacaoRepository(em);
    }


    public Locacao realizarLocacao(Cliente cliente, List<ItemLocacao> itensParaLocar) {
        if (cliente == null || itensParaLocar == null || itensParaLocar.isEmpty()) {
            throw new IllegalArgumentException("Cliente e lista de itens não podem ser nulos ou vazios.");
        }

        manager.getTransaction().begin();

        try {
            Locacao locacao = new Locacao();
            locacao.setCliente(cliente);
            locacao.setData(LocalDate.now());

            for (ItemLocacao item : itensParaLocar) {
                JogoPlataformaId id = new JogoPlataformaId(
                        item.getJogoPlataforma().getJogo().getId(),
                        item.getJogoPlataforma().getPlataforma().getId()
                );

                JogoPlataforma jogo = locacaoRepository.buscarJogoPlataformaPorId(id);


                if (jogo == null) {

                    throw new IllegalArgumentException("O jogo com ID " + id.getJogo() + " não está disponível na plataforma com ID " + id.getPlataforma());
                }

                item.setLocacao(locacao);
                locacao.getItens().add(item);
            }

            locacaoRepository.salvar(locacao);
            manager.getTransaction().commit();

            System.out.println("Locação realizada com sucesso!");
            return locacao;

        } catch (Exception e) {
            if (manager.getTransaction().isActive()) {
                manager.getTransaction().rollback();
            }
            throw new RuntimeException("Erro ao realizar locação: " + e.getMessage(), e);
        }
    }

    public BigDecimal calcularPrecoTotal(Locacao locacao) {

        manager.getTransaction().begin();

        BigDecimal precoTotal = BigDecimal.ZERO;

        for (ItemLocacao item : locacao.getItens()) {
            JogoPlataformaId jpId = new JogoPlataformaId(
                    item.getJogoPlataforma().getJogo().getId(),
                    item.getJogoPlataforma().getPlataforma().getId()
            );


            JogoPlataforma jogoPlataComPreco = manager.find(JogoPlataforma.class, jpId);

            if (jogoPlataComPreco != null) {
                BigDecimal precoDiario = jogoPlataComPreco.getPrecoDiario();
                BigDecimal totalDias = new BigDecimal(item.getDias());
                precoTotal = precoTotal.add(precoDiario.multiply(totalDias));
            }
        }

        manager.getTransaction().commit();
        return precoTotal;
    }
}