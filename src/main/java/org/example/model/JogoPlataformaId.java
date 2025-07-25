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


    public Integer getJogo() {
        return jogo;
    }

    public Integer getPlataforma() {
        return plataforma;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        JogoPlataformaId that = (JogoPlataformaId) obj;
        return Objects.equals(jogo, that.jogo) && Objects.equals(plataforma, that.plataforma);
    }

    @Override
    public int hashCode() {
        return Objects.hash(jogo, plataforma);
    }
}