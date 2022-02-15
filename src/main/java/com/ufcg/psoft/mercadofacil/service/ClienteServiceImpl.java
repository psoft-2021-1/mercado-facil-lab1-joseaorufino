package com.ufcg.psoft.mercadofacil.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import com.ufcg.psoft.mercadofacil.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufcg.psoft.mercadofacil.DTO.ClienteDTO;
import com.ufcg.psoft.mercadofacil.repository.ClienteRepository;

@Service
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

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
	public String listarTiposDeCliente() {
		return new String(TipoDeCliente.NORMAL.toString() + "\n" +
				TipoDeCliente.ESPECIAL.toString() + "\n" + TipoDeCliente.PREMIUM.toString());
	}

	@Override
	public Cliente estabelecerTipoDeCliente(Cliente cliente, TipoDeCliente tipoDeCliente) {
		cliente.setTipoDeCliente(tipoDeCliente);

		return cliente;
	}

	@Override
	public TipoDeCliente getTipoDeClienteById(Long idTipoDeCliente) {
		for (TipoDeCliente tipoDeCliente : TipoDeCliente.values()) {
			if (idTipoDeCliente.equals(tipoDeCliente.getId()))
				return tipoDeCliente;
		}
		return null;
	}

	@Override
	public BigDecimal getDescontoByIdTipoDeCliente(Long idTipoDeCliente) {
		TipoDeCliente tipoDeCliente = getTipoDeClienteById(idTipoDeCliente);
		return tipoDeCliente.getDesconto();
	}


}