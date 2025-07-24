package org.example.model;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Console {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;
    private BigDecimal precoPorHora;

    @ManyToMany
    @JoinTable(
            name = "console_acessorio",
            joinColumns = @JoinColumn(name = "console_id"),
            inverseJoinColumns = @JoinColumn(name = "acessorio_id")
    )
    private List<Acessorio> acessorios = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    protected  void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getPrecoPorHora() {
        return precoPorHora;
    }

    public void setPrecoPorHora(BigDecimal precoPorHora) {
        this.precoPorHora = precoPorHora;
    }

    public List<Acessorio> getAcessorios() {
        return acessorios;
    }

    public void setAcessorios(List<Acessorio> acessorios) {
        this.acessorios = acessorios;
    }
}

