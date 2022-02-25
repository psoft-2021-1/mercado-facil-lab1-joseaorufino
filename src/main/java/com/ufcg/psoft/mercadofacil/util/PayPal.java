package com.ufcg.psoft.mercadofacil.util;

import org.springframework.stereotype.Component;
import java.math.BigDecimal;

@Component
public class PayPal implements FormaDePagamento {

    @Override
    public FormaDePagamentoName getFormaDePagamentoName() {
        return FormaDePagamentoName.PAYPAL;
    }

    @Override
    public BigDecimal calculaValorComAcrescimo(BigDecimal valorInicial) {
        BigDecimal incremento = valorInicial.multiply(new BigDecimal(0.02));
        BigDecimal valorFinal = valorInicial.add(incremento);

        return valorFinal;
    }
}
