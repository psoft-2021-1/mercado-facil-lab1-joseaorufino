package com.ufcg.psoft.mercadofacil.util;

import org.springframework.stereotype.Component;
import java.math.BigDecimal;

@Component
public class Boleto implements FormaDePagamento {

    @Override
    public FormaDePagamentoName getFormaDePagamentoName() {
        return FormaDePagamentoName.BOLETO;
    }

    @Override
    public BigDecimal calculaValorComAcrescimo(BigDecimal valorInicial) {
        BigDecimal incremento = valorInicial.multiply(new BigDecimal(0.00));
        BigDecimal valorFinal = valorInicial.add(incremento);

        return valorFinal;
    }
}
