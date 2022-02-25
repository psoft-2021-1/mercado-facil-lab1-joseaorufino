package com.ufcg.psoft.mercadofacil.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.ufcg.psoft.mercadofacil.DTO.FormaDePagamentoDTO;
import com.ufcg.psoft.mercadofacil.DTO.TipoDeClienteDTO;
import com.ufcg.psoft.mercadofacil.model.*;
import com.ufcg.psoft.mercadofacil.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufcg.psoft.mercadofacil.DTO.ClienteDTO;
import com.ufcg.psoft.mercadofacil.repository.ClienteRepository;

@Service
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private TipoDeClienteFactory tipoDeClienteFactory;

	@Override
	public Optional<Cliente> getClienteById(Long id) { return clienteRepository.findById(id); }

	@Override
	public Optional<Cliente> getClienteByCPF(Long cpf) {
		return clienteRepository.findByCPF(cpf);
	}

	@Override
	public void removerClienteCadastrado(Cliente cliente) {
		clienteRepository.delete(cliente);
	}

	@Override
	public void salvarClienteCadastrado(Cliente cliente) {
		clienteRepository.save(cliente);
	}

	@Override
	public List<Cliente> listarClientes() {
		return clienteRepository.findAll();
	}

	@Override
	public Cliente criaCliente(ClienteDTO clienteDTO) {
		Cliente cliente = new Cliente(clienteDTO.getCPF(), clienteDTO.getNome(),
				clienteDTO.getIdade(), clienteDTO.getEndereco());
		Carrinho carrinho = new Carrinho();
		cliente.setCarrinho(carrinho);
		carrinho.setCliente(cliente);
		clienteRepository.save(cliente);
		return cliente;
	}

	@Override
	public Cliente atualizaCliente(ClienteDTO clienteDTO, Cliente cliente) {
		cliente.setIdade(clienteDTO.getIdade());
		cliente.setEndereco(clienteDTO.getEndereco());

		return cliente;
	}

	@Override
	public List<Compra> getCompras(Cliente cliente) {
		return cliente.getCompras();
	}

	@Override
	public void addCompra(Compra compra) {
		Cliente cliente = compra.getCliente();
		cliente.addCompra(compra);
	}

	@Override
	public List<TipoDeClienteDTO> listarTiposDeCliente() {
		List<TipoDeClienteDTO> lista = new ArrayList<TipoDeClienteDTO>();

		for (TipoDeClienteName tipoDeClienteName : TipoDeClienteName.values()) {
			lista.add(new TipoDeClienteDTO(tipoDeClienteName));
		}

		return lista;
	}

	@Override
	public Cliente estabelecerTipoDeCliente(Cliente cliente, TipoDeCliente tipoDeCliente) {
		cliente.setTipoDeCliente(tipoDeCliente.getTipoDeCLienteName());

		return cliente;
	}

	@Override
	public TipoDeCliente getTipoDeClienteByNome(String nomeTipoDeCliente) {
		for (TipoDeClienteName tipoDeClienteName : TipoDeClienteName.values()) {
			if (nomeTipoDeCliente.equals(tipoDeClienteName.toString()))
				return tipoDeClienteFactory.encontrarTipoDeCliente(tipoDeClienteName);
		}
		return null;
	}


}