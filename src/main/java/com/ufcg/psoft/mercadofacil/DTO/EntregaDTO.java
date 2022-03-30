package com.ufcg.psoft.mercadofacil.DTO;

import com.ufcg.psoft.mercadofacil.components.entrega.EntregaName;

public class EntregaDTO {

    private EntregaName EntregaName;

    public EntregaDTO() {}

    public EntregaDTO(EntregaName EntregaName) {
        this.EntregaName = EntregaName;
    }

    public EntregaName getEntrega() {
        return EntregaName;
    }

    public void setEntrega(EntregaName EntregaName) {
        this.EntregaName = EntregaName;
    }

}
