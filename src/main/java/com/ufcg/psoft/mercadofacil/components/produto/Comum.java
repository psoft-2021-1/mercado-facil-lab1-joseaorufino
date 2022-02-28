package com.ufcg.psoft.mercadofacil.components.produto;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class Comum implements TipoProduto {

    @Override
    public TipoProdutoName getTipoProdutoName() {
        return TipoProdutoName.COMUM;
    }

    @Override
    public BigDecimal calculaValorComAcrescimo(BigDecimal valorInicial) {
        return valorInicial;
    }

}
