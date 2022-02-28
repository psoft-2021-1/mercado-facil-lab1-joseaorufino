package com.ufcg.psoft.mercadofacil.service;

import com.ufcg.psoft.mercadofacil.DTO.EntregaDTO;
import com.ufcg.psoft.mercadofacil.components.entrega.Entrega;
import com.ufcg.psoft.mercadofacil.components.entrega.EntregaFactory;
import com.ufcg.psoft.mercadofacil.components.entrega.EntregaName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EntregaServiceImpl implements EntregaService {

    @Autowired
    private EntregaFactory entregaFactory;

    @Override
    public List<EntregaDTO> listarTiposDeEntrega() {
        List<EntregaDTO> lista = new ArrayList<EntregaDTO>();

        for (EntregaName entrega : EntregaName.values()) {
            lista.add(new EntregaDTO(entrega));
        }
        return lista;
    }

    @Override
    public Entrega getEntregaByNome(String nomeEntrega) {
        for (EntregaName entrega : EntregaName.values()) {
            if (nomeEntrega.equals(entrega.toString()))
                return entregaFactory.encontrarEntrega(entrega);
        }
        return null;
    }

}
