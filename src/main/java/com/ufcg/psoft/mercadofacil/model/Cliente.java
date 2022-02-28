package com.ufcg.psoft.mercadofacil.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ufcg.psoft.mercadofacil.components.cliente.TipoDeClienteName;
import com.ufcg.psoft.mercadofacil.components.entrega.EntregaName;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long CPF;

	private String nome;

	private Integer idade;

	private String endereco;

	@OneToOne(mappedBy = "cliente", cascade = CascadeType.PERSIST)
	private Carrinho carrinho;

	@OneToMany
	@JsonIgnore
	private List<Compra> compras;

	@Enumerated(EnumType.STRING)
	private TipoDeClienteName tipoDeCliente;


	private Cliente() {}

	public Cliente(Long cpf, String nome, Integer idade, String endereco) {
		this.CPF = cpf;
		this.nome = nome;
		this.idade = idade;
		this.endereco = endereco;
		this.carrinho = new Carrinho();
		this.compras = new ArrayList<Compra>();
		this.tipoDeCliente = TipoDeClienteName.NORMAL;
	}

	public Long getId() {
		return id;
	}

	public Long getCpf() {
		return CPF;
	}

	public String getNome() {
		return nome;
	}

	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public Carrinho getCarrinho() { return this.carrinho; }

	public List<Compra> getCompras() { return this.compras; }

	public void addCompra(Compra compra) { this.compras.add(compra); }

	public TipoDeClienteName getTipoDeCliente() {
		return tipoDeCliente;
	}

	public void setTipoDeCliente(TipoDeClienteName tipoDeCliente) {
		this.tipoDeCliente = tipoDeCliente;
	}


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Cliente cliente = (Cliente) o;
		return id.equals(cliente.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	public void setCarrinho(Carrinho carrinho) { this.carrinho = carrinho; }

}