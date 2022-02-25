package com.ufcg.psoft.mercadofacil.DTO;

import com.ufcg.psoft.mercadofacil.util.TipoDeClienteName;

public class TipoDeClienteDTO {

    private TipoDeClienteName tipoDeClienteName;

    public TipoDeClienteDTO() {}

    public TipoDeClienteDTO(TipoDeClienteName tipoDeClienteName) {
        this.tipoDeClienteName = tipoDeClienteName;
    }

    public TipoDeClienteName getTipoDeCliente() {
        return tipoDeClienteName;
    }

    public void setTipoDeCliente(TipoDeClienteName tipoDeClienteName) {
        this.tipoDeClienteName = tipoDeClienteName;
    }

}
