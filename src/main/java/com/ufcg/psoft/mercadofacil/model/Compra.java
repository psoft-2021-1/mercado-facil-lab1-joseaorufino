package com.ufcg.psoft.mercadofacil.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Cliente cliente;

    private BigDecimal valorTotal;

    private LocalDate data;

    @OneToMany
    private List<Produto> produtos;

    private Compra() {}

    public Compra(Cliente cliente, BigDecimal valorTotal, List<Produto> produtos) {
        this.cliente = cliente;
        this.valorTotal = valorTotal;
        this.produtos = produtos;
        this.data = LocalDate.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Compra compra = (Compra) o;
        return id.equals(compra.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Long getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public BigDecimal getValorTotal() { return valorTotal; }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public LocalDate getData() { return  data; }

}
