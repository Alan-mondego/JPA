package org.example.servico;

import jakarta.persistence.EntityManager;
import org.example.model.*;
import org.example.repository.LocacaoRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

// Responsabilidade: Orquestrar a lógica de negócio para realizar uma locação.
public class LocacaoServico {

    private final EntityManager em;
    private final LocacaoRepository locacaoRepository;

    public LocacaoServico(EntityManager em) {
        this.em = em;
        this.locacaoRepository = new LocacaoRepository(em);
    }

    public Locacao realizarLocacao(Cliente cliente, List<ItemLocacao> itensParaLocar) {
        // Lógica de negócio: validações
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

            // Lógica de negócio: verificar se o jogo existe no catálogo
            JogoPlataforma jp = locacaoRepository.buscarJogoPlataformaPorId(id);
            if (jp == null) {
                em.getTransaction().rollback(); // Importante: desfazer a transação
                throw new IllegalArgumentException("O jogo não está disponível na plataforma especificada.");
            }

            // Lógica de negócio: associar item à locação
            item.setLocacao(locacao);
            locacao.getItens().add(item);
        }

        locacaoRepository.salvar(locacao);
        em.getTransaction().commit();

        // Lógica de negócio: cálculo do preço (poderia ser feito aqui, mas já está na sua entidade)
        System.out.println("Locação realizada com sucesso!");

        return locacao;
    }
}