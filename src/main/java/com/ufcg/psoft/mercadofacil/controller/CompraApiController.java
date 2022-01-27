package com.ufcg.psoft.mercadofacil.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.ufcg.psoft.mercadofacil.model.Compra;
import com.ufcg.psoft.mercadofacil.model.Produto;
import com.ufcg.psoft.mercadofacil.service.CarrinhoService;
import com.ufcg.psoft.mercadofacil.service.CompraService;
import com.ufcg.psoft.mercadofacil.util.ErroCompra;
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
public class CompraApiController {

    @Autowired
    ClienteService clienteService;
    @Autowired
    CarrinhoService carrinhoService;
    @Autowired
    CompraService compraService;

    @RequestMapping(value = "/compras/{idCliente}/finalizar", method = RequestMethod.POST)
    public ResponseEntity<?> finalizarCompra(@PathVariable("idCliente") long id) {

        Optional<Cliente> clienteOp = clienteService.getClienteById(id);

        if (!clienteOp.isPresent()) {
            return ErroCliente.erroClienteNaoEnconrtrado(id);
        }

        Cliente cliente = clienteOp.get();

        if (cliente.getCarrinho().isEmpty()) {
            return ErroCliente.erroSemProdutosNoCarrinho();
        }
        List<Produto> carrinho = new ArrayList<Produto>(carrinhoService.getCarrinho(clienteOp.get()));
        BigDecimal valorTotal = carrinhoService.getValorTotalCarrinho(cliente);

        Compra compra = new Compra(cliente, valorTotal, carrinho);
        compraService.salvarCompraCadastrada(new Compra(cliente, valorTotal, carrinho));
        carrinhoService.limparCarrinho(cliente);

        return new ResponseEntity<>(compra, HttpStatus.OK);
    }

    @RequestMapping(value = "/compras/{idCliente}", method = RequestMethod.GET)
    public ResponseEntity<?> listarCompras(@PathVariable("idCliente") long id) {

        Optional<Cliente> clienteOp = clienteService.getClienteById(id);

        if (!clienteOp.isPresent()) {
            return ErroCliente.erroClienteNaoEnconrtrado(id);
        }

        List<Compra> compras = compraService.listarCompras(clienteOp.get());

        return new ResponseEntity<List<Compra>>(compras, HttpStatus.OK);
    }

    @RequestMapping(value = "/compras/{idCliente}/{idCompra}", method = RequestMethod.GET)
    public ResponseEntity<?> listarCompra(@PathVariable("idCliente") long idCliente, @PathVariable("idCompra") long idCompra) {

        Optional<Cliente> clienteOp = clienteService.getClienteById(idCliente);
        Optional<Compra> compraOp = compraService.getCompraById(idCompra);

        if (!clienteOp.isPresent()) {
            return ErroCliente.erroClienteNaoEnconrtrado(idCliente);
        }

        if (!compraOp.isPresent()) {
            return ErroCompra.erroCompraNaoEnconrtrada(idCompra);
        }

        return new ResponseEntity<Compra>(compraOp.get(), HttpStatus.OK);
    }

//    @RequestMapping(value = "/carrinho/{idCliente}/add", method = RequestMethod.PUT)
//    public ResponseEntity<?> addProdutoCarrinho(@PathVariable("idCliente") long id, @RequestBody int idProduto) {
//
//        Optional<Cliente> clienteOp = clienteService.getClienteById(id);
//
//        if (!clienteOp.isPresent()) {
//            return ErroCliente.erroClienteNaoEnconrtrado(id);
//        }
//
//        Optional<Produto> optionalProduto = produtoService.getProdutoById(idProduto);
//
//        if (!optionalProduto.isPresent()) {
//            return ErroProduto.erroProdutoNaoEnconrtrado(id);
//        }
//
//        Produto produto = optionalProduto.get();
//        Cliente cliente = clienteOp.get();
//
//        clienteService.salvarClienteCadastrado(carrinhoService.addProdutoCarrinho(cliente, produto));
//
//        return new ResponseEntity<>(produto, HttpStatus.OK);
//    }
//
//    @RequestMapping(value = "/carrinho/{idCliente}/rmv", method = RequestMethod.PUT)
//    public ResponseEntity<?> rmvProdutoCarrinho(@PathVariable("idCliente") long id, @RequestBody int idProduto) {
//
//        Optional<Cliente> clienteOp = clienteService.getClienteById(id);
//
//        if (!clienteOp.isPresent()) {
//            return ErroCliente.erroClienteNaoEnconrtrado(id);
//        }
//
//        Optional<Produto> optionalProduto = produtoService.getProdutoById(idProduto);
//
//        if (!optionalProduto.isPresent()) {
//            return ErroProduto.erroProdutoNaoEnconrtrado(id);
//        }
//
//        Produto produto = optionalProduto.get();
//        Cliente cliente = clienteOp.get();
//
//        clienteService.salvarClienteCadastrado(carrinhoService.rmvProdutoCarrinho(cliente, produto));
//
//        return new ResponseEntity<>(produto, HttpStatus.OK);
//    }
//
//    @RequestMapping(value = "/carrinho/{idCliente}/finalizar", method = RequestMethod.POST)
//    public ResponseEntity<?> fecharCarrinho(@PathVariable("idCliente") long id) {
//
//        Optional<Cliente> clienteOp = clienteService.getClienteById(id);
//
//        if (!clienteOp.isPresent()) {
//            return ErroCliente.erroClienteNaoEnconrtrado(id);
//        }
//
//        Cliente cliente = clienteOp.get();
//
//        if (cliente.getCarrinho().isEmpty()) {
//            return ErroCliente.erroSemProdutosNoCarrinho();
//        }
//        List<Produto> carrinho = new ArrayList<Produto>(carrinhoService.getCarrinho(clienteOp.get()));
//        BigDecimal valorTotal = carrinhoService.getValorTotalCarrinho(cliente);
//
//        Compra compra = new Compra(cliente, valorTotal, carrinho);
//        compraService.salvarCompraCadastrada(compra);
//
//        return new ResponseEntity<>(compra, HttpStatus.OK);
//    }
}