package com.ufcg.psoft.mercadofacil.service;

import com.ufcg.psoft.mercadofacil.model.*;
import com.ufcg.psoft.mercadofacil.repository.CarrinhoRepository;
import com.ufcg.psoft.mercadofacil.util.FormaDePagamento;
import com.ufcg.psoft.mercadofacil.util.TipoDeCliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CarrinhoServiceImpl implements CarrinhoService {

    @Autowired
    LoteService loteService;
    @Autowired
    ClienteService clienteService;
    @Autowired
    CarrinhoRepository carrinhoRepository;

    @Override
    public Carrinho getCarrinho(Cliente cliente) {
        return cliente.getCarrinho();
    }

    @Override
    public List<Produto> getProdutosCarrinho(Cliente cliente) {
        return cliente.getCarrinho().getProdutos();
    }

    @Override
    public Cliente addProdutoCarrinho(Cliente cliente, Produto produto) {
        Carrinho carrinho = cliente.getCarrinho();
        carrinho = verificaCarrinhoNull(carrinho, cliente);
        carrinho.addProduto(produto);
        carrinho.setCliente(cliente);
        this.salvarCarrinho(carrinho);
        Lote loteProduto = loteService.verificaLoteProduto(produto);

        if (!(loteProduto == null)) {
            loteProduto.setNumeroDeItens(loteProduto.getNumeroDeItens() - 1);
            loteService.salvarLote(loteProduto);
        }
        return cliente;
    }

    @Override
    public Cliente rmvProdutoCarrinho(Cliente cliente, Produto produto) {
        Carrinho carrinho = cliente.getCarrinho();
        verificaCarrinhoNull(carrinho, cliente);
        carrinho.rmvProduto(produto);
        this.salvarCarrinho(carrinho);
        Lote loteProduto = loteService.verificaLoteProduto(produto);

        if (!(loteProduto == null)) {
            loteProduto.setNumeroDeItens(loteProduto.getNumeroDeItens() + 1);
            loteService.salvarLote(loteProduto);
        }
        return cliente;
    }

    @Override
    public BigDecimal getValorTotalCarrinho(Cliente cliente, FormaDePagamento formaDePagamento, TipoDeCliente tipoDeCliente) {
        BigDecimal valorComAcrescimo = formaDePagamento.calculaValorComAcrescimo(calculaValorInicialCarrinho(cliente));

        return tipoDeCliente.calculaValorComDesconto(valorComAcrescimo, cliente);
    }

    private BigDecimal calculaValorInicialCarrinho(Cliente cliente) {
        List<Produto> produtos = cliente.getCarrinho().getProdutos();
        BigDecimal somaInicial = new BigDecimal(0);

        for (Produto produto : produtos) {
            somaInicial = somaInicial.add(produto.getPreco());
        }
        return somaInicial;
    }

    @Override
    public void limparCarrinho(Cliente cliente) {
        cliente.getCarrinho().limpar();
    }

    @Override
    public void salvarCarrinho(Carrinho carrinho) {
        carrinhoRepository.save(carrinho);
    }

    private Carrinho verificaCarrinhoNull(Carrinho carrinho, Cliente cliente) {
        if (carrinho == null) {
            carrinho = new Carrinho();
            carrinho.setCliente(cliente);
            cliente.setCarrinho(carrinho);
            clienteService.salvarClienteCadastrado(cliente);
            salvarCarrinho(carrinho);
        }
        return carrinho;
    }
}