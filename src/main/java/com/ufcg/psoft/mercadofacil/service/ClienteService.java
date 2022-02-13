package com.ufcg.psoft.mercadofacil.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import com.ufcg.psoft.mercadofacil.DTO.ClienteDTO;
import com.ufcg.psoft.mercadofacil.model.Cliente;
import com.ufcg.psoft.mercadofacil.model.Compra;
import com.ufcg.psoft.mercadofacil.model.Produto;
import com.ufcg.psoft.mercadofacil.model.TipoDeCliente;

public interface ClienteService {

	public Optional<Cliente> getClienteById(Long id);

	public Optional<Cliente> getClienteByCPF(Long cpf);

	public void removerClienteCadastrado(Cliente cliente);

	public void salvarClienteCadastrado(Cliente cliente);

	public List<Cliente> listarClientes();

	public Cliente criaCliente(ClienteDTO clienteDTO);

	public Cliente atualizaCliente(ClienteDTO clienteDTO, Cliente cliente);

	public List<Produto> getCarrinho(Cliente cliente);

	public Cliente addProdutoCarrinho(Cliente cliente, Produto produto);

	public Cliente rmvProdutoCarrinho(Cliente cliente, Produto produto);

	public List<Compra> getCompras(Cliente cliente);

	public void addCompra(Compra compra);

	public String listarTiposDeCliente();

	public Cliente estabelecerTipoDeCliente(Cliente cliente, TipoDeCliente tipoDeCliente);

	public TipoDeCliente getTipoDeClienteById(Long idTipoDeCliente);

	public BigDecimal getDescontoByIdTipoDeCliente(Long idTipoDeCLiente);



}