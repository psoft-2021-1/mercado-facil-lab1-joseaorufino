package com.ufcg.psoft.mercadofacil.service;

import com.ufcg.psoft.mercadofacil.model.FormasDePagamento;

public interface PagamentoService {

    public String listarFormasDePagamento();

    public FormasDePagamento getFormaDePagamentoById(Long idFormaDePagamento);
}
