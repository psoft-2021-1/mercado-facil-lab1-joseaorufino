package com.ufcg.psoft.mercadofacil.model;

import com.ufcg.psoft.mercadofacil.components.pagamento.FormaDePagamentoName;
import com.ufcg.psoft.mercadofacil.components.cliente.TipoDeClienteName;
import com.ufcg.psoft.mercadofacil.components.produto.TipoProdutoName;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Cliente cliente;

    @OneToMany
    private List<Produto> produtos;

    private BigDecimal valorTotal;

    @Enumerated(EnumType.STRING)
    private FormaDePagamentoName formaDePagamento;

    @Enumerated(EnumType.STRING)
    private TipoProdutoName tipoEntrega;

    private LocalDate data;

    private Compra() {}

    public Compra(Cliente cliente, BigDecimal valorTotal, List<Produto> produtos, FormaDePagamentoName formaDePagamento, TipoProdutoName tipoEntrega) {
        this.cliente = cliente;
        this.produtos = produtos;
        this.valorTotal = valorTotal;
        this.formaDePagamento = formaDePagamento;
        this.tipoEntrega = tipoEntrega;
        this.data = LocalDate.now();
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

    public FormaDePagamentoName getFormaDePagamento() { return formaDePagamento; }

    public TipoDeClienteName getTipoDeCliente() { return cliente.getTipoDeCliente(); }

    public TipoProdutoName getTipoEntrega() {
        return tipoEntrega;
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

}