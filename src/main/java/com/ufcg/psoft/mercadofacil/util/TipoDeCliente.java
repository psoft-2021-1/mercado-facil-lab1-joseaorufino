package com.ufcg.psoft.mercadofacil.util;

import com.ufcg.psoft.mercadofacil.model.Cliente;

import java.math.BigDecimal;

public interface TipoDeCliente {

    public TipoDeClienteName getTipoDeCLienteName();

    public BigDecimal calculaValorComDesconto(BigDecimal valorInicial, Cliente cliente);

}
