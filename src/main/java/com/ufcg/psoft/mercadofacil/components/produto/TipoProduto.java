package com.ufcg.psoft.mercadofacil.components.produto;

import java.math.BigDecimal;

public interface TipoProduto {

    public TipoProdutoName getTipoProdutoName();

    public BigDecimal calculaValorComAcrescimo(BigDecimal valorInicial);

}
