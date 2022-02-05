package com.ufcg.psoft.mercadofacil.service;

import com.ufcg.psoft.mercadofacil.model.FormasDePagamento;
import org.springframework.stereotype.Service;

@Service
public class PagamentoServiceImpl implements PagamentoService {

    @Override
    public String listarFormasDePagamento() {
        return new String(FormasDePagamento.BOLETO.toString() + "\n" +
                FormasDePagamento.PAYPAL.toString() + "\n" + FormasDePagamento.CARTAO.toString());
    }

    @Override
    public FormasDePagamento getFormaDePagamentoById(Long idFormaDePagamento) {
        for (FormasDePagamento formaDePagamento : FormasDePagamento.values()) {
            if (idFormaDePagamento.equals(formaDePagamento.getId()))
                return formaDePagamento;
        }
        return null;
    }

}
