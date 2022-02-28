package com.ufcg.psoft.mercadofacil.DTO;

import com.ufcg.psoft.mercadofacil.components.pagamento.FormaDePagamentoName;

public class FormaDePagamentoDTO {

    private FormaDePagamentoName formaDePagamento;

    public FormaDePagamentoDTO() {}

    public FormaDePagamentoDTO(FormaDePagamentoName formaDePagamento) {
        this.formaDePagamento = formaDePagamento;
    }

    public FormaDePagamentoName getFormaDePagamento() {
        return formaDePagamento;
    }

    public void setFormaDePagamento(FormaDePagamentoName formaDePagamento) {
        this.formaDePagamento = formaDePagamento;
    }

}
