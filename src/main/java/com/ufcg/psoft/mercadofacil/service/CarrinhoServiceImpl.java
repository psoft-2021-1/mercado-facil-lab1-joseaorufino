package com.ufcg.psoft.mercadofacil.service;

import com.ufcg.psoft.mercadofacil.components.entrega.Entrega;
import com.ufcg.psoft.mercadofacil.components.produto.TipoProduto;
import com.ufcg.psoft.mercadofacil.components.produto.TipoProdutoFactory;
import com.ufcg.psoft.mercadofacil.components.produto.TipoProdutoName;
import com.ufcg.psoft.mercadofacil.model.*;
import com.ufcg.psoft.mercadofacil.repository.CarrinhoRepository;
import com.ufcg.psoft.mercadofacil.components.pagamento.FormaDePagamento;
import com.ufcg.psoft.mercadofacil.components.cliente.TipoDeCliente;
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
    @Autowired
    TipoProdutoFactory tipoProdutoFactory;

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
    public BigDecimal calculaValorTotalCarrinho(Cliente cliente, FormaDePagamento formaDePagamento, TipoDeCliente tipoDeCliente, Entrega entrega, TipoProdutoName tipoProdutoName) {
        BigDecimal valorComAcrescimoPagamento = formaDePagamento.calculaValorComAcrescimo(calculaValorInicialCarrinho(cliente));
        TipoProduto tipoEntregaProduto = tipoProdutoFactory.encontrarTipoProduto(tipoProdutoName);
        BigDecimal valorComAcrescimoProdutoEntrega =  tipoEntregaProduto.calculaValorComAcrescimo(valorComAcrescimoPagamento);
        BigDecimal valorComDesconto = tipoDeCliente.calculaValorComDesconto(valorComAcrescimoProdutoEntrega, cliente);

        return entrega.calculaValorComEntrega(valorComDesconto, cliente);
    }

    @Override
    public TipoProdutoName getEntregaProduto(Cliente cliente) {
        List<Produto> produtos = cliente.getCarrinho().getProdutos();
        TipoProdutoName tipoProdutoEntrega = TipoProdutoName.COMUM;

        for (Produto produto : produtos) {
            if (produto.getTipoProduto().equals(TipoProdutoName.REFRIGERACAO)) {
                return TipoProdutoName.REFRIGERACAO;
            } if (produto.getTipoProduto().equals(TipoProdutoName.FRAGIL)) {
                tipoProdutoEntrega = TipoProdutoName.FRAGIL;
            }
        }
        return tipoProdutoEntrega;
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