package com.ufcg.psoft.mercadofacil.service;

import com.ufcg.psoft.mercadofacil.model.Cliente;
import com.ufcg.psoft.mercadofacil.model.Lote;
import com.ufcg.psoft.mercadofacil.model.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CarrinhoServiceImpl implements CarrinhoService {

    @Autowired
    LoteService loteService;

    public List<Produto> getCarrinho(Cliente cliente) {
        return cliente.getCarrinho();
    }

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

    public BigDecimal getValorTotalCarrinho(Cliente cliente) {
        List<Produto> produtos = cliente.getCarrinho();
        BigDecimal soma = BigDecimal.valueOf(0);
        for (Produto produto : produtos) {
            soma.add(produto.getPreco());
        }
        return soma;
    }

    public void limparCarrinho(Cliente cliente) {
        cliente.limparCarrinho();
    }
}
