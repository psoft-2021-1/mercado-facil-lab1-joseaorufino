package com.ufcg.psoft.mercadofacil.model;

import java.math.BigDecimal;

public enum TipoDeCliente {

    NORMAL(10, "10 - Normal (- 0 %)",new BigDecimal(0.00)),
    ESPECIAL(20, "20 - Especial (- 10% para compras com mais de 10 itens no carrinho)",new BigDecimal(0.10)),
    PREMIUM(30, "30 - Premium (- 10% para compras com mais de 5 itens no carrinho)",new BigDecimal(0.10));
    private long id;
    private String texto;
    private BigDecimal desconto;

    TipoDeCliente(long id, String texto, BigDecimal desconto) {
        this.id = id;
        this.texto = texto;
        this.desconto = desconto;
    }

    public long getId() { return id; }

    public BigDecimal getDesconto() { return desconto; }

    @Override
    public String toString() { return texto; }

}