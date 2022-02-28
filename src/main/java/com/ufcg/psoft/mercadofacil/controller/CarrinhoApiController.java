package com.ufcg.psoft.mercadofacil.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.ufcg.psoft.mercadofacil.model.Carrinho;
import com.ufcg.psoft.mercadofacil.model.Compra;
import com.ufcg.psoft.mercadofacil.model.Produto;
import com.ufcg.psoft.mercadofacil.service.CarrinhoService;
import com.ufcg.psoft.mercadofacil.service.CompraService;
import com.ufcg.psoft.mercadofacil.service.ProdutoService;
import com.ufcg.psoft.mercadofacil.util.ErroProduto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.ufcg.psoft.mercadofacil.model.Cliente;
import com.ufcg.psoft.mercadofacil.service.ClienteService;
import com.ufcg.psoft.mercadofacil.util.ErroCliente;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class CarrinhoApiController {

    @Autowired
    ClienteService clienteService;
    @Autowired
    CarrinhoService carrinhoService;
    @Autowired
    ProdutoService produtoService;

    @RequestMapping(value = "/carrinho/{idCliente}", method = RequestMethod.GET)
    public ResponseEntity<?> listarCarrinho(@PathVariable("idCliente") long id) {

        Optional<Cliente> clienteOp = clienteService.getClienteById(id);

        if (!clienteOp.isPresent()) {
            return ErroCliente.erroClienteNaoEnconrtrado(id);
        }
        Carrinho carrinho = carrinhoService.getCarrinho(clienteOp.get());

        return new ResponseEntity<Carrinho>(carrinho, HttpStatus.OK);
    }

    @RequestMapping(value = "/carrinho/{idCliente}/add", method = RequestMethod.PUT)
    public ResponseEntity<?> addProdutoCarrinho(@PathVariable("idCliente") long id, @RequestBody int idProduto) {

        Optional<Cliente> clienteOp = clienteService.getClienteById(id);

        if (!clienteOp.isPresent()) {
            return ErroCliente.erroClienteNaoEnconrtrado(id);
        }

        Optional<Produto> optionalProduto = produtoService.getProdutoById(idProduto);

        if (!optionalProduto.isPresent()) {
            return ErroProduto.erroProdutoNaoEnconrtrado(id);
        }

        Produto produto = optionalProduto.get();

        if (!produto.isDisponivel()) {
            return ErroProduto.erroProdutoIndisponivel(produto);
        }
        Cliente cliente = clienteOp.get();

        clienteService.salvarClienteCadastrado(carrinhoService.addProdutoCarrinho(cliente, produto));
        carrinhoService.salvarCarrinho(cliente.getCarrinho());

        return new ResponseEntity<>(produto, HttpStatus.OK);
    }

    @RequestMapping(value = "/carrinho/{idCliente}/rmv", method = RequestMethod.PUT)
    public ResponseEntity<?> rmvProdutoCarrinho(@PathVariable("idCliente") long id, @RequestBody int idProduto) {

        Optional<Cliente> clienteOp = clienteService.getClienteById(id);

        if (!clienteOp.isPresent()) {
            return ErroCliente.erroClienteNaoEnconrtrado(id);
        }

        Optional<Produto> optionalProduto = produtoService.getProdutoById(idProduto);

        if (!optionalProduto.isPresent()) {
            return ErroProduto.erroProdutoNaoEnconrtrado(id);
        }

        Produto produto = optionalProduto.get();
        Cliente cliente = clienteOp.get();

        clienteService.salvarClienteCadastrado(carrinhoService.rmvProdutoCarrinho(cliente, produto));
        carrinhoService.salvarCarrinho(cliente.getCarrinho());

        return new ResponseEntity<>(produto, HttpStatus.OK);
    }

}