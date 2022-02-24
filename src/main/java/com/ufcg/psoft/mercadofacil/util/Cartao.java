package com.ufcg.psoft.mercadofacil.util;

import org.springframework.stereotype.Component;
import java.math.BigDecimal;

@Component
public class Cartao implements FormaDePagamento {
    @Override
    public FormaDePagamentoName getFormaDePagamentoName() {
        return FormaDePagamentoName.CARTAO;
    }

    @Override
    public BigDecimal calculaValorTotalCarrinho(BigDecimal valorInicial) {
        BigDecimal incremento = valorInicial.multiply(new BigDecimal(0.05));
        BigDecimal valorFinal = valorInicial.add(incremento);

        return valorFinal;
    }
}
