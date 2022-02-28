package com.ufcg.psoft.mercadofacil.components.entrega;

import com.ufcg.psoft.mercadofacil.model.Cliente;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;

@Component
public class Retirada implements Entrega {

    @Override
    public EntregaName getEntregaName() { return EntregaName.RETIRADA; }

    @Override
    public BigDecimal calculaValorComEntrega(BigDecimal valorInicial, Cliente cliente) {
        return new BigDecimal(0);
    }

}
