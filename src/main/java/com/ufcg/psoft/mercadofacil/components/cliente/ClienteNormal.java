package com.ufcg.psoft.mercadofacil.components.cliente;

import com.ufcg.psoft.mercadofacil.model.Cliente;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;

@Component
public class ClienteNormal implements TipoDeCliente {

    @Override
    public TipoDeClienteName getTipoDeCLienteName() { return TipoDeClienteName.NORMAL; }

    @Override
    public BigDecimal calculaValorComDesconto(BigDecimal valorInicial, Cliente cliente) {
        return valorInicial;
    }
}
