package com.ufcg.psoft.mercadofacil.components.produto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class TipoProdutoFactory {

    private Map<TipoProdutoName, TipoProduto> tiposDeProduto;

    @Autowired
    public TipoProdutoFactory(Set<TipoProduto> tipoDeProdutoSet) {
        criarTipoProduto(tipoDeProdutoSet);
    }

    public TipoProduto encontrarTipoProduto(TipoProdutoName tipoDeProdutoName) {
        return tiposDeProduto.get(tipoDeProdutoName);
    }
    private void criarTipoProduto(Set<TipoProduto> tipoDeProdutoSet) {
        tiposDeProduto = new HashMap<TipoProdutoName, TipoProduto>();
        tipoDeProdutoSet.forEach(
                tipoDeProduto -> tiposDeProduto.put(tipoDeProduto.getTipoProdutoName(), tipoDeProduto));
    }

}
