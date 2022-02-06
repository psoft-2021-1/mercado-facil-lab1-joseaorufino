package com.ufcg.psoft.mercadofacil.service;

import com.ufcg.psoft.mercadofacil.model.Cliente;
import com.ufcg.psoft.mercadofacil.model.Produto;

import java.math.BigDecimal;
import java.util.List;

public interface CarrinhoService {

    public List<Produto> getCarrinho(Cliente cliente);

    public Cliente addProdutoCarrinho(Cliente cliente, Produto produto);

    public Cliente rmvProdutoCarrinho(Cliente cliente, Produto produto);

    public BigDecimal getValorTotalCarrinho(Cliente cliente, BigDecimal acrescimo);

    public void limparCarrinho(Cliente cliente);
}