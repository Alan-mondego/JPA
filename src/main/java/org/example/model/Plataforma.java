package org.example.model;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Plataforma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;

    @OneToMany(mappedBy = "plataforma")
    private List<JogoPlataforma> jogos = new ArrayList<>();

    public Plataforma(){

    }

    public Plataforma(String nome){
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<JogoPlataforma> getJogos() {
        return jogos;
    }

    public void setJogos(List<JogoPlataforma> jogos) {
        this.jogos = jogos;
    }
}



