package com.ufcg.psoft.mercadofacil.model;

// import javax.persistence.Entity;

//TODO @Entity
public enum FormasDePagamento {

    BOLETO(1, "1 - Boleto (+0%)",0),
    PAYPAL(2, "2 - PayPal (+2%)",2),
    CARTAO(3, "3 - Cartão de Crédito (+5%)",5);
    private long id;
    private String texto;
    private int acrescimo;

    FormasDePagamento(long id, String texto, int acrescimo) {
        this.id = id;
        this.texto = texto;
        this.acrescimo = acrescimo;
    }

    public long getId() { return id; }

    public int getAcrescimo() {
        return acrescimo;
    }

    @Override
    public String toString() { return texto; }

}
