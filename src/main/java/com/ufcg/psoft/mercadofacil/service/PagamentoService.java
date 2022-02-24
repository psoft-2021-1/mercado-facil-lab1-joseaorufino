package com.ufcg.psoft.mercadofacil.service;

import com.ufcg.psoft.mercadofacil.DTO.FormaDePagamentoDTO;
import com.ufcg.psoft.mercadofacil.util.FormaDePagamento;
import com.ufcg.psoft.mercadofacil.util.FormaDePagamentoName;

import java.math.BigDecimal;
import java.util.List;

public interface PagamentoService {

    public List<FormaDePagamentoDTO> listarFormasDePagamento();

    public FormaDePagamento getFormaDePagamentoByNome(String nomeFormaDePagamento);

}