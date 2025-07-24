package org.example.servico;

import jakarta.persistence.EntityManager;
import org.example.model.*;
import org.example.repository.LocacaoRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


public class LocacaoServico {

    private final EntityManager em;
    private final LocacaoRepository locacaoRepository;

    public LocacaoServico(EntityManager em) {
        this.em = em;
        this.locacaoRepository = new LocacaoRepository(em);
    }

    public Locacao realizarLocacao(Cliente cliente, List<ItemLocacao> itensParaLocar) {
        if (cliente == null || itensParaLocar == null || itensParaLocar.isEmpty()) {
            throw new IllegalArgumentException("Cliente e lista de itens não podem ser nulos ou vazios.");
        }

        em.getTransaction().begin();

        Locacao locacao = new Locacao();
        locacao.setCliente(cliente);
        locacao.setData(LocalDate.now());

        for (ItemLocacao item : itensParaLocar) {
            JogoPlataformaId id = new JogoPlataformaId(
                    item.getJogoPlataforma().getJogo().getId(),
                    item.getJogoPlataforma().getPlataforma().getId()
            );


            JogoPlataforma jp = locacaoRepository.buscarJogoPlataformaPorId(id);
            if (jp == null) {
                em.getTransaction().rollback();
                throw new IllegalArgumentException("O jogo não está disponível na plataforma especificada.");
            }


            item.setLocacao(locacao);
            locacao.getItens().add(item);
        }

        locacaoRepository.salvar(locacao);
        em.getTransaction().commit();


        System.out.println("Locação realizada com sucesso!");

        return locacao;
    }
}