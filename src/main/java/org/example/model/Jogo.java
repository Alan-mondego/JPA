package org.example.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Jogo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String titulo;

    @OneToMany(mappedBy = "jogo")
    private List<JogoPlataforma> plataformas = new ArrayList<>();

   public Jogo(){

   }

    public Jogo(String titulo){
        this.titulo = titulo;
    }

public Integer getId() {
        return id;
    }

  protected  void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<JogoPlataforma> getPlataformas() {
        return plataformas;
    }

    public void setPlataformas(List<JogoPlataforma> plataformas) {
        this.plataformas = plataformas;
    }
}
