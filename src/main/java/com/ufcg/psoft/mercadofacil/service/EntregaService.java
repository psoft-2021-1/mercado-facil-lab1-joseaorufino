package com.ufcg.psoft.mercadofacil.service;

import com.ufcg.psoft.mercadofacil.DTO.EntregaDTO;
import com.ufcg.psoft.mercadofacil.components.entrega.Entrega;
import com.ufcg.psoft.mercadofacil.model.Cliente;

import java.math.BigDecimal;
import java.util.List;

public interface EntregaService {

    public List<EntregaDTO> listarTiposDeEntrega();

    public Entrega getEntregaByNome(String nomeEntrega);

//    public BigDecimal calculaEntrega(BigDecimal valorInicial, Cliente cliente);
// TODO REMOVE
}
