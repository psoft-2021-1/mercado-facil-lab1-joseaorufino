package com.ufcg.psoft.mercadofacil.components.produto;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class Fragil implements TipoProduto {

    @Override
    public TipoProdutoName getTipoProdutoName() {
        return TipoProdutoName.FRAGIL;
    }

    @Override
    public BigDecimal calculaValorComAcrescimo(BigDecimal valorInicial) {
        BigDecimal acrescimo = valorInicial.multiply(new BigDecimal(0.15)); // 15% de aumento por ser Fragil
        BigDecimal valorFinal = valorInicial.add(acrescimo);

        return valorFinal;
    }

}
