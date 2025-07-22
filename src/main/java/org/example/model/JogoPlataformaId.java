package org.example.model;

import java.io.Serializable;
import java.util.Objects;


public class JogoPlataformaId implements Serializable {
    private Integer jogo;
    private Integer plataforma;


    public JogoPlataformaId() {}


    public JogoPlataformaId(Integer jogo, Integer plataforma) {
        this.jogo = jogo;
        this.plataforma = plataforma;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JogoPlataformaId that = (JogoPlataformaId) o;
        return Objects.equals(jogo, that.jogo) && Objects.equals(plataforma, that.plataforma);
    }

    @Override
    public int hashCode() {
        return Objects.hash(jogo, plataforma);
    }
}