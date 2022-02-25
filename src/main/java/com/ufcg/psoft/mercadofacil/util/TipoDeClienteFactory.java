package com.ufcg.psoft.mercadofacil.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class TipoDeClienteFactory {

    private Map<TipoDeClienteName, TipoDeCliente> tiposDeCliente;

    @Autowired
    public TipoDeClienteFactory(Set<TipoDeCliente> tipoDeClienteSet) {
        criarFormaDePagamento(tipoDeClienteSet);
    }

    public TipoDeCliente encontrarTipoDeCliente(TipoDeClienteName tipoDeCLienteName) {
        return tiposDeCliente.get(tipoDeCLienteName);
    }
    private void criarFormaDePagamento(Set<TipoDeCliente> tipoDeClienteSet) {
        tiposDeCliente = new HashMap<TipoDeClienteName, TipoDeCliente>();
        tipoDeClienteSet.forEach(
                tipoDeCliente -> tiposDeCliente.put(tipoDeCliente.getTipoDeCLienteName(), tipoDeCliente));
    }

}
