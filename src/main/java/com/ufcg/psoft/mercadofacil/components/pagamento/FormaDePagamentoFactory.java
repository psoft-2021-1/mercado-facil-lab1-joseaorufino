package com.ufcg.psoft.mercadofacil.components.pagamento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class FormaDePagamentoFactory {

    private Map<FormaDePagamentoName, FormaDePagamento> formasDePagamento;

    @Autowired
    public FormaDePagamentoFactory(Set<FormaDePagamento> formaDePagamentoSet) {
        criarFormaDePagamento(formaDePagamentoSet);
    }

    public FormaDePagamento encontrarFormaDePagamento(FormaDePagamentoName formaDePagamentoName) {
        return formasDePagamento.get(formaDePagamentoName);
    }
    private void criarFormaDePagamento(Set<FormaDePagamento> formaDePagamentoSet) {
        formasDePagamento = new HashMap<FormaDePagamentoName, FormaDePagamento>();
        formaDePagamentoSet.forEach(
                formaDePagamento -> formasDePagamento.put(formaDePagamento.getFormaDePagamentoName(), formaDePagamento));
    }

}
