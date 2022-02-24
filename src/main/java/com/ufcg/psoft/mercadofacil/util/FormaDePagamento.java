package com.ufcg.psoft.mercadofacil.util;

import java.math.BigDecimal;

public interface FormaDePagamento {

    FormaDePagamentoName getFormaDePagamentoName();

    public BigDecimal calculaValorTotalCarrinho(BigDecimal valorInicial);

}
