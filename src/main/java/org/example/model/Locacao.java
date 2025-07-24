package org.example.model;


import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Locacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDate data;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @OneToMany(mappedBy = "locacao", cascade = CascadeType.ALL)
    private List<ItemLocacao> itens = new ArrayList<>();

    public  Integer getId() {
        return id;
    }

    protected void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<ItemLocacao> getItens() {
        return itens;
    }

    public void setItens(List<ItemLocacao> itens) {
        this.itens = itens;
    }
}

