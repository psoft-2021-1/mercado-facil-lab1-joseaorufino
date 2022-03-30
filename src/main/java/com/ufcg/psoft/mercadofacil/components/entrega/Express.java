package com.ufcg.psoft.mercadofacil.components.entrega;

import com.ufcg.psoft.mercadofacil.model.Cliente;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;

@Component
public class Express implements Entrega {

    @Override
    public EntregaName getEntregaName() { return EntregaName.EXPRESS; }

    @Override
    public BigDecimal calculaValorComEntrega(BigDecimal valorInicial, Cliente cliente) {
        BigDecimal endereco = new BigDecimal(cliente.getEndereco().length());
        BigDecimal taxa = endereco.multiply(new BigDecimal(2.0)); // Express 2x mais cara TODO REMOVE COMMENT
        BigDecimal valorFinal = valorInicial.add(taxa);

        return valorFinal;
    }

}
