package com.ufcg.psoft.mercadofacil.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Cliente pagador;

    @OneToOne
    private Compra compra;

    private FormasDePagamento formaPagamento;

    private BigDecimal valor;

    private LocalDate data;

    private Pagamento() {}

    public Pagamento(Cliente pagador, Compra compra, FormasDePagamento formaPagamento, BigDecimal valor) {
        this.pagador = pagador;
        this.valor = valor;
        this.compra = compra;
        this.formaPagamento = formaPagamento;
        this.data = LocalDate.now();
    }

    public Long getId() {
        return id;
    }

    public Cliente getPagador() {
        return pagador;
    }

    public Compra getCompra() {
        return compra;
    }

    public FormasDePagamento getFormaPagamento() {
        return formaPagamento;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public LocalDate getData() {
        return data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pagamento pagamento = (Pagamento) o;
        return id.equals(pagamento.id) && pagador.equals(pagamento.pagador) && compra.equals(pagamento.compra);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, pagador, compra);
    }
}
