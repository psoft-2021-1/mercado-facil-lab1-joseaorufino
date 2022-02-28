package com.ufcg.psoft.mercadofacil.controller;

import java.util.List;
import java.util.Optional;
import com.ufcg.psoft.mercadofacil.DTO.TipoDeClienteDTO;
import com.ufcg.psoft.mercadofacil.service.CarrinhoService;
import com.ufcg.psoft.mercadofacil.components.cliente.TipoDeCliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import com.ufcg.psoft.mercadofacil.DTO.ClienteDTO;
import com.ufcg.psoft.mercadofacil.model.Cliente;
import com.ufcg.psoft.mercadofacil.service.ClienteService;
import com.ufcg.psoft.mercadofacil.util.ErroCliente;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ClienteApiController {

	@Autowired
	ClienteService clienteService;
	@Autowired
	CarrinhoService carrinhoService;

	@RequestMapping(value = "/clientes", method = RequestMethod.GET)
	public ResponseEntity<?> listarClientes() {

		List<Cliente> clientes = clienteService.listarClientes();

		if (clientes.isEmpty()) {
			return ErroCliente.erroSemClientesCadastrados();
		}

		return new ResponseEntity<List<Cliente>>(clientes, HttpStatus.OK);
	}

	@RequestMapping(value = "/cliente/", method = RequestMethod.POST)
	public ResponseEntity<?> criarCliente(@RequestBody ClienteDTO clienteDTO, UriComponentsBuilder ucBuilder) {

		Optional<Cliente> clienteOp = clienteService.getClienteByCPF(clienteDTO.getCPF());

		if (!clienteOp.isEmpty()) {
			return ErroCliente.erroClienteJaCadastrado(clienteDTO);
		}

		Cliente cliente = clienteService.criaCliente(clienteDTO);
		clienteService.salvarClienteCadastrado(cliente);
		carrinhoService.salvarCarrinho(cliente.getCarrinho());

		return new ResponseEntity<Cliente>(cliente, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/cliente/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> consultarCliente(@PathVariable("id") long id) {

		Optional<Cliente> clienteOp = clienteService.getClienteById(id);

		if (!clienteOp.isPresent()) {
			return ErroCliente.erroClienteNaoEnconrtrado(id);
		}

		return new ResponseEntity<Cliente>(clienteOp.get(), HttpStatus.OK);
	}

	@RequestMapping(value = "/cliente/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> atualizarCliente(@PathVariable("id") long id, @RequestBody ClienteDTO clienteDTO) {

		Optional<Cliente> clienteOp = clienteService.getClienteById(id);

		if (!clienteOp.isPresent()) {
			return ErroCliente.erroClienteNaoEnconrtrado(id);
		}

		Cliente cliente = clienteOp.get();

		clienteService.atualizaCliente(clienteDTO, cliente);
		clienteService.salvarClienteCadastrado(cliente);
		carrinhoService.salvarCarrinho(cliente.getCarrinho());

		return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
	}

	@RequestMapping(value = "/cliente/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> removerCliente(@PathVariable("id") long id) {

		Optional<Cliente> clienteOp = clienteService.getClienteById(id);

		if (!clienteOp.isPresent()) {
			return ErroCliente.erroClienteNaoEnconrtrado(id);
		}

		clienteService.removerClienteCadastrado(clienteOp.get());

		return new ResponseEntity<Cliente>(HttpStatus.OK);
	}

	@RequestMapping(value = "/cliente/tiposDeCliente", method = RequestMethod.GET)
	public ResponseEntity<?> listarTiposDeCliente() {

		List<TipoDeClienteDTO> tiposDeCliente = clienteService.listarTiposDeCliente();

		return new ResponseEntity<List<TipoDeClienteDTO>>(tiposDeCliente, HttpStatus.OK);
	}

	@RequestMapping(value = "/cliente/{idCliente}/{tipoDeClienteNome}", method = RequestMethod.PUT)
	public ResponseEntity<?> estabelecerTipoDeCliente(@PathVariable("idCliente") long idCliente, @PathVariable("tipoDeClienteNome") String tipoDeClienteNome) {

		Optional<Cliente> clienteOp = clienteService.getClienteById(idCliente);
		TipoDeCliente tipoDeCliente = clienteService.getTipoDeClienteByNome(tipoDeClienteNome);

		if (!clienteOp.isPresent()) {
			return ErroCliente.erroClienteNaoEnconrtrado(idCliente);
		}

		if (tipoDeCliente == null) {
			return ErroCliente.erroTipoDeClienteNaoDisponivel(tipoDeClienteNome);
		}

		Cliente cliente = clienteOp.get();

		clienteService.estabelecerTipoDeCliente(cliente, tipoDeCliente);
		clienteService.salvarClienteCadastrado(cliente);

		return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
	}

}