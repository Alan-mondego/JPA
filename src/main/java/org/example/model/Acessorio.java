package org.example.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Acessorio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;

    @ManyToMany(mappedBy = "acessorios")
    private List<Console> consoles = new ArrayList<>();

    public  Integer getId() {
        return id;
    }

    protected void setId(Integer id) {
        this.id = id;
    }

    public List<Console> getConsoles() {
        return consoles;
    }

    public void setConsoles(List<Console> consoles) {
        this.consoles = consoles;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}

