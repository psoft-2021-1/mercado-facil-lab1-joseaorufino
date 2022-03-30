package com.ufcg.psoft.mercadofacil.components.entrega;

import com.ufcg.psoft.mercadofacil.model.Cliente;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;

@Component
public class Padrao implements Entrega {

    @Override
    public EntregaName getEntregaName() { return EntregaName.PADRAO; }

    @Override
    public BigDecimal calculaValorComEntrega(BigDecimal valorInicial, Cliente cliente) {
        BigDecimal taxa = new BigDecimal(cliente.getEndereco().length());
        BigDecimal valorFinal = valorInicial.add(taxa);

        return valorFinal;
    }

}
