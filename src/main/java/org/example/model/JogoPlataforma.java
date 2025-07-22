package org.example.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@IdClass(JogoPlataformaId.class)
public class JogoPlataforma {
    @Id
    @ManyToOne
    @JoinColumn(name = "jogo_id")
    private Jogo jogo;

    @Id
    @ManyToOne
    @JoinColumn(name = "plataforma_id")
    private Plataforma plataforma;

    private BigDecimal precoDiario;


    public JogoPlataforma(){

    }

    public JogoPlataforma(Jogo jogo, Plataforma plataforma, BigDecimal precoDiario) {

        this.jogo = jogo;
        this.plataforma = plataforma;
        this.precoDiario = precoDiario;
    }

    public Jogo getJogo() {
        return jogo;
    }

    public void setJogo(Jogo jogo) {
        this.jogo = jogo;
    }

    public Plataforma getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(Plataforma plataforma) {
        this.plataforma = plataforma;
    }

    public BigDecimal getPrecoDiario() {
        return precoDiario;
    }

    public void setPrecoDiario(BigDecimal precoDiario) {
        this.precoDiario = precoDiario;
    }
}
