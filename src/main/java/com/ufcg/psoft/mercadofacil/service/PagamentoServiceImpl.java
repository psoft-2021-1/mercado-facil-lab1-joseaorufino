package com.ufcg.psoft.mercadofacil.service;

import com.ufcg.psoft.mercadofacil.DTO.FormaDePagamentoDTO;
import com.ufcg.psoft.mercadofacil.util.FormaDePagamento;
import com.ufcg.psoft.mercadofacil.util.FormaDePagamentoFactory;
import com.ufcg.psoft.mercadofacil.util.FormaDePagamentoName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class PagamentoServiceImpl implements PagamentoService {

    @Autowired
    private FormaDePagamentoFactory formaDePagamentoFactory;

    @Override
    public List<FormaDePagamentoDTO> listarFormasDePagamento() {
        List<FormaDePagamentoDTO> lista = new ArrayList<FormaDePagamentoDTO>();

        for (FormaDePagamentoName formaDePagamento : FormaDePagamentoName.values()) {
            lista.add(new FormaDePagamentoDTO(formaDePagamento));
        }

        return lista;
    }

    @Override
    public FormaDePagamento getFormaDePagamentoByNome(String nomeFormaDePagamento) {
        for (FormaDePagamentoName formaDePagamento : FormaDePagamentoName.values()) {
            if (nomeFormaDePagamento.equals(formaDePagamento.toString()))
                return formaDePagamentoFactory.encontrarFormaDePagamento(formaDePagamento);
        }
        return null;
    }

}