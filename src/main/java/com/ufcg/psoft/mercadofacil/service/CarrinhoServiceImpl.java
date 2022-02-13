package com.ufcg.psoft.mercadofacil.service;

import com.ufcg.psoft.mercadofacil.model.Cliente;
import com.ufcg.psoft.mercadofacil.model.Lote;
import com.ufcg.psoft.mercadofacil.model.Produto;
import com.ufcg.psoft.mercadofacil.model.TipoDeCliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CarrinhoServiceImpl implements CarrinhoService {

    @Autowired
    LoteService loteService;

    @Override
    public List<Produto> getCarrinho(Cliente cliente) {
        return cliente.getCarrinho();
    }

    @Override
    public Cliente addProdutoCarrinho(Cliente cliente, Produto produto) {
        cliente.addProdutoCarrinho(produto);
        List<Lote> lotes = loteService.listarLotes();
        Lote loteProduto = null;

        for (Lote lote : lotes) {
            if (lote.getProduto().equals(produto)) {
                loteProduto = lote;
                break;
            }
        }
        if (!(loteProduto == null)) {
            loteProduto.setNumeroDeItens(loteProduto.getNumeroDeItens() - 1);
            loteService.salvarLote(loteProduto);
        }
        return cliente;
    }

    @Override
    public Cliente rmvProdutoCarrinho(Cliente cliente, Produto produto) {
        cliente.rmvProdutoCarrinho(produto);
        List<Lote> lotes = loteService.listarLotes();
        Lote loteProduto = null;

        for (Lote lote : lotes) {
            if (lote.getProduto().equals(produto)) {
                loteProduto = lote;
                break;
            }
        }
        if (!(loteProduto == null)) {
            loteProduto.setNumeroDeItens(loteProduto.getNumeroDeItens() + 1);
            loteService.salvarLote(loteProduto);
        }
        return cliente;
    }

    @Override
    public BigDecimal getValorTotalCarrinho(Cliente cliente, BigDecimal acrescimo) {
        List<Produto> produtos = cliente.getCarrinho();
        BigDecimal soma = new BigDecimal(0);
        BigDecimal decremento = new BigDecimal(0);

        for (Produto produto : produtos) {
            soma = soma.add(produto.getPreco());
        }
        BigDecimal incremento = soma.multiply(acrescimo);
        if (cliente.getTipoDeCliente().equals(TipoDeCliente.ESPECIAL) && produtos.size() > 10) {
            decremento = soma.multiply(cliente.getDesconto());
        }
        if (cliente.getTipoDeCliente().equals(TipoDeCliente.PREMIUM) && produtos.size() > 5) {
            decremento = soma.multiply(cliente.getDesconto());
        }
        soma = soma.add(incremento);
        soma = soma.subtract(decremento);
        return soma;
    }

    @Override
    public void limparCarrinho(Cliente cliente) {
        cliente.limparCarrinho();
    }
}