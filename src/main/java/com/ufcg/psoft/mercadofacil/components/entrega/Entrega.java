package com.ufcg.psoft.mercadofacil.components.entrega;

import com.ufcg.psoft.mercadofacil.model.Cliente;

import java.math.BigDecimal;

public interface Entrega {

    public EntregaName getEntregaName();

    public BigDecimal calculaValorComEntrega(BigDecimal valorInicial, Cliente cliente);

}
