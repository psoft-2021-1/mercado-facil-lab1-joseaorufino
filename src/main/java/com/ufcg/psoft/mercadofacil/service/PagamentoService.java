package com.ufcg.psoft.mercadofacil.service;

import com.ufcg.psoft.mercadofacil.DTO.FormaDePagamentoDTO;
import com.ufcg.psoft.mercadofacil.components.pagamento.FormaDePagamento;
import java.util.List;

public interface PagamentoService {

    public List<FormaDePagamentoDTO> listarFormasDePagamento();

    public FormaDePagamento getFormaDePagamentoByNome(String nomeFormaDePagamento);

}