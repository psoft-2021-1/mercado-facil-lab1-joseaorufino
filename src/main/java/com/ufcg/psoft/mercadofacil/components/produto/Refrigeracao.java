package com.ufcg.psoft.mercadofacil.components.produto;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class Refrigeracao implements TipoProduto {

    @Override
    public TipoProdutoName getTipoProdutoName() {
        return TipoProdutoName.REFRIGERACAO;
    }

    @Override
    public BigDecimal calculaValorComAcrescimo(BigDecimal valorInicial) {
        BigDecimal acrescimo = valorInicial.multiply(new BigDecimal(1.25)); // 25% de aumento por ser Refrigeracao
        BigDecimal valorFinal = valorInicial.add(acrescimo);

        return valorFinal;
    }

}
