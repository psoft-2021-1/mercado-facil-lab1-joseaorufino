package com.ufcg.psoft.mercadofacil.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Carrinho {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @MapsId @JsonIgnore
    private Cliente cliente;

    @OneToMany
    private List<Produto> produtosCarrinho;

    public Carrinho() {
        this.produtosCarrinho = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public Cliente getCliente() { return cliente; }

    public List<Produto> getProdutos() { return this.produtosCarrinho; }

    public void addProduto(Produto produto) { this.produtosCarrinho.add(produto); }

    public void rmvProduto(Produto produto) { this.produtosCarrinho.remove(produto); }

    public void limpar() { this.produtosCarrinho = new ArrayList<Produto>(); }

    public void setCliente(Cliente cliente) { this.cliente = cliente; }
}
