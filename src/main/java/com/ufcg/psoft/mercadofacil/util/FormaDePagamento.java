package com.ufcg.psoft.mercadofacil.util;

import java.math.BigDecimal;

public interface FormaDePagamento {

    public FormaDePagamentoName getFormaDePagamentoName();

    public BigDecimal calculaValorComAcrescimo(BigDecimal valorInicial);

}
