package com.ufcg.psoft.mercadofacil.service;

import com.ufcg.psoft.mercadofacil.model.Carrinho;
import com.ufcg.psoft.mercadofacil.model.Cliente;
import com.ufcg.psoft.mercadofacil.model.Produto;

import java.math.BigDecimal;
import java.util.List;

public interface CarrinhoService {

    public Carrinho getCarrinho(Cliente cliente);

    public List<Produto> getProdutosCarrinho(Cliente cliente);

    public Cliente addProdutoCarrinho(Cliente cliente, Produto produto);

    public Cliente rmvProdutoCarrinho(Cliente cliente, Produto produto);

    public BigDecimal getValorTotalCarrinho(Cliente cliente, BigDecimal acrescimo);

    public void limparCarrinho(Cliente cliente);

    public void salvarCarrinho(Carrinho carrinho);
}