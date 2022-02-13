package com.ufcg.psoft.mercadofacil.service;

import com.ufcg.psoft.mercadofacil.model.FormaDePagamento;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PagamentoServiceImpl implements PagamentoService {

    @Override
    public String listarFormasDePagamento() {
        return new String(FormaDePagamento.BOLETO.toString() + "\n" +
                FormaDePagamento.PAYPAL.toString() + "\n" + FormaDePagamento.CARTAO.toString());
    }

    @Override
    public FormaDePagamento getFormaDePagamentoById(Long idFormaDePagamento) {
        for (FormaDePagamento formaDePagamento : FormaDePagamento.values()) {
            if (idFormaDePagamento.equals(formaDePagamento.getId()))
                return formaDePagamento;
        }
        return null;
    }

    @Override
    public BigDecimal getAcrescimo(Long idFormaDePagamento) {
        FormaDePagamento formaDePagamento = getFormaDePagamentoById(idFormaDePagamento);
        return formaDePagamento.getAcrescimo();
    }

}