package com.ufcg.psoft.mercadofacil.model;

import java.math.BigDecimal;

public enum FormaDePagamento {

    BOLETO(1, "1 - Boleto (+ 0%)",new BigDecimal(0.00)),
    PAYPAL(2, "2 - PayPal (+ 2%)",new BigDecimal(0.02)),
    CARTAO(3, "3 - Cartão de Crédito (+ 5%)",new BigDecimal(0.05));
    private long id;
    private String texto;
    private BigDecimal acrescimo;

    FormaDePagamento(long id, String texto, BigDecimal acrescimo) {
        this.id = id;
        this.texto = texto;
        this.acrescimo = acrescimo;
    }

    public long getId() { return id; }

    public BigDecimal getAcrescimo() {
        return acrescimo;
    }

    @Override
    public String toString() { return texto; }

}