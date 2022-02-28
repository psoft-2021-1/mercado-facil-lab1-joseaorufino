package com.ufcg.psoft.mercadofacil.components.entrega;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class EntregaFactory {

    private Map<EntregaName, Entrega> tiposDeEntrega;

    @Autowired
    public EntregaFactory(Set<Entrega> entregaSet) {
        criarEntrega(entregaSet);
    }

    public Entrega encontrarEntrega(EntregaName entregaName) {
        return tiposDeEntrega.get(entregaName);
    }
    private void criarEntrega(Set<Entrega> entregaSet) {
        tiposDeEntrega = new HashMap<EntregaName, Entrega>();
        entregaSet.forEach(
                entrega -> tiposDeEntrega.put(entrega.getEntregaName(), entrega));
    }

}
