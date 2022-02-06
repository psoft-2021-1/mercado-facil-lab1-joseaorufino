package com.ufcg.psoft.mercadofacil.service;

import com.ufcg.psoft.mercadofacil.model.FormaDePagamento;

import java.math.BigDecimal;

public interface PagamentoService {

    public String listarFormasDePagamento();

    public FormaDePagamento getFormaDePagamentoById(Long idFormaDePagamento);

    public BigDecimal getAcrescimo(Long idFormaDePagamento);

}