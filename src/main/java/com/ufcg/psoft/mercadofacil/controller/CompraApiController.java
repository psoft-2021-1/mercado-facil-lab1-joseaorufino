package com.ufcg.psoft.mercadofacil.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import com.ufcg.psoft.mercadofacil.DTO.EntregaDTO;
import com.ufcg.psoft.mercadofacil.DTO.FormaDePagamentoDTO;
import com.ufcg.psoft.mercadofacil.components.entrega.Entrega;
import com.ufcg.psoft.mercadofacil.components.produto.TipoProduto;
import com.ufcg.psoft.mercadofacil.components.produto.TipoProdutoName;
import com.ufcg.psoft.mercadofacil.model.Compra;
import com.ufcg.psoft.mercadofacil.service.*;
import com.ufcg.psoft.mercadofacil.util.*;
import com.ufcg.psoft.mercadofacil.model.Produto;
import com.ufcg.psoft.mercadofacil.components.cliente.TipoDeCliente;
import com.ufcg.psoft.mercadofacil.components.pagamento.FormaDePagamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.ufcg.psoft.mercadofacil.model.Cliente;

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
    @Autowired
    PagamentoService pagamentoService;
    @Autowired
    EntregaService entregaService;

    @RequestMapping(value = "/compras/{idCliente}/{formaDePagamentoNome}/{entregaNome}/finalizar", method = RequestMethod.POST)
    public ResponseEntity<?> finalizarCompra(@PathVariable("idCliente") long idCliente, @PathVariable("formaDePagamentoNome") String formaDePagamentoNome,
            @PathVariable("entregaNome") String entregaNome) {

        Optional<Cliente> clienteOp = clienteService.getClienteById(idCliente);
        FormaDePagamento formaDePagamento = pagamentoService.getFormaDePagamentoByNome(formaDePagamentoNome);
        Entrega entrega = entregaService.getEntregaByNome(entregaNome);

        if (!clienteOp.isPresent()) {
            return ErroCliente.erroClienteNaoEnconrtrado(idCliente);
        }
        if (formaDePagamento == null) {
            return ErroCompra.erroFormaDePagamentoNaoDisponivel(formaDePagamentoNome);
        }
        if (entrega == null) {
            return ErroCompra.erroEntregaNaoDisponivel(entregaNome);
        }

        Cliente cliente = clienteOp.get();
        TipoDeCliente tipoDeCliente = clienteService.getTipoDeClienteByNome(cliente.getTipoDeCliente().toString());

        if (cliente.getCarrinho().getProdutos().isEmpty()) {
            return ErroCliente.erroSemProdutosNoCarrinho();
        }
        List<Produto> produtosCarrinho = List.copyOf(carrinhoService.getProdutosCarrinho(cliente));
        TipoProdutoName tipoEntregaProduto = carrinhoService.getEntregaProduto(cliente);
        BigDecimal valorTotal = carrinhoService.calculaValorTotalCarrinho(cliente, formaDePagamento, tipoDeCliente, entrega, tipoEntregaProduto);

        Compra compra = new Compra(cliente, valorTotal, produtosCarrinho, formaDePagamento.getFormaDePagamentoName(), tipoEntregaProduto);
        compraService.salvarCompraCadastrada(compra);
        carrinhoService.limparCarrinho(cliente);
        clienteService.salvarClienteCadastrado(cliente);
        carrinhoService.salvarCarrinho(cliente.getCarrinho());

        return new ResponseEntity<Compra>(compra, HttpStatus.OK);
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

    @RequestMapping(value = "/compras/formasPagamento", method = RequestMethod.GET)
    public ResponseEntity<?> listarFormasDePagamento() {

        List<FormaDePagamentoDTO> formas = pagamentoService.listarFormasDePagamento();

        return new ResponseEntity<List<FormaDePagamentoDTO>>(formas, HttpStatus.OK);
    }

    @RequestMapping(value = "/compras/entregas", method = RequestMethod.GET)
    public ResponseEntity<?> listarTiposDeEntrega() {

        List<EntregaDTO> tiposDeEntrega = entregaService.listarTiposDeEntrega();

        return new ResponseEntity<List<EntregaDTO>>(tiposDeEntrega, HttpStatus.OK);
    }

}