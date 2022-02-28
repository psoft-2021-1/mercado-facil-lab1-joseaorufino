package com.ufcg.psoft.mercadofacil.components.cliente;

import com.ufcg.psoft.mercadofacil.model.Cliente;
import com.ufcg.psoft.mercadofacil.model.Produto;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.util.List;

@Component
public class ClienteEspecial implements TipoDeCliente {

    @Override
    public TipoDeClienteName getTipoDeCLienteName() { return TipoDeClienteName.ESPECIAL; }

    @Override
    public BigDecimal calculaValorComDesconto(BigDecimal valorInicial, Cliente cliente) {
        List<Produto> produtos = cliente.getCarrinho().getProdutos();

        if (produtos.size() > 10) {
            BigDecimal decremento = valorInicial.multiply(new BigDecimal(0.10));
            BigDecimal valorFinal = valorInicial.subtract(decremento);

            return valorFinal;
        }
        return valorInicial;
    }
}
