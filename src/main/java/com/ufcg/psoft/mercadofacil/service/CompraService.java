package com.ufcg.psoft.mercadofacil.service;

import com.ufcg.psoft.mercadofacil.model.Cliente;
import com.ufcg.psoft.mercadofacil.model.Compra;

import java.util.List;
import java.util.Optional;

public interface CompraService {

    public Optional<Compra> getCompraById(Long id);

    public void salvarCompraCadastrada(Compra compra);

    public List<Compra> listarCompras(Cliente cliente);

}
