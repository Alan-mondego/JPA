package org.example.model;

import jakarta.persistence.*;

@Entity
public class ItemLocacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "jogo_id", referencedColumnName = "jogo_id"),
            @JoinColumn(name = "plataforma_id", referencedColumnName = "plataforma_id")
    })
    private JogoPlataforma jogoPlataforma;


    private Integer dias;
    private Integer quantidade;

    @ManyToOne
    @JoinColumn(name = "locacao_id")
    private Locacao locacao;


    public  Integer getId() {
        return id;
    }

   protected void setId(Integer id) {
        this.id = id;
    }

    public JogoPlataforma getJogoPlataforma() {
        return jogoPlataforma;
    }

    public void setJogoPlataforma(JogoPlataforma jogoPlataforma) {
        this.jogoPlataforma = jogoPlataforma;
    }

    public Integer getDias() {
        return dias;
    }

    public void setDias(Integer dias) {
        this.dias = dias;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Locacao getLocacao() {
        return locacao;
    }

    public void setLocacao(Locacao locacao) {
        this.locacao = locacao;
    }
}