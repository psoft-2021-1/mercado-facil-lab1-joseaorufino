package com.ufcg.psoft.mercadofacil.service;

import com.ufcg.psoft.mercadofacil.model.Cliente;
import com.ufcg.psoft.mercadofacil.model.Compra;
import com.ufcg.psoft.mercadofacil.repository.CompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CompraServiceImpl implements CompraService {

    @Autowired
    CompraRepository compraRepository;

    @Override
    public Optional<Compra> getCompraById(Long id) {
        return compraRepository.findById(id);
    }

    @Override
    public void salvarCompraCadastrada(Compra compra) {
        compraRepository.save(compra);
    }

    @Override
    public List<Compra> listarCompras(Cliente cliente) {
        List<Compra> compras = new ArrayList<Compra>();
        for (Compra compra : compraRepository.findAll()) {
            if (compra.getCliente().equals(cliente)) {
                compras.add(compra);
            }
        }
        return compras;
    }
}