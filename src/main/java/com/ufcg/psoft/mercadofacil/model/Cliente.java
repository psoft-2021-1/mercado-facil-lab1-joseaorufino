package com.ufcg.psoft.mercadofacil.model;

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

	@OneToMany
	private List<Produto> carrinho;

	@OneToMany
	private List<Compra> compras;

	private Cliente() {}

	public Cliente(Long cpf, String nome, Integer idade, String endereco) {
		this.CPF = cpf;
		this.nome = nome;
		this.idade = idade;
		this.endereco = endereco;
		this.carrinho = new ArrayList<Produto>();
		this.compras = new ArrayList<Compra>();
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

	public List<Produto> getCarrinho() { return this.carrinho; }

	public void addProdutoCarrinho(Produto produto) { this.carrinho.add(produto); }

	public void rmvProdutoCarrinho(Produto produto) { this.carrinho.remove(produto); }

	public void limparCarrinho() { this.carrinho = new ArrayList<Produto>(); }

	public List<Compra> getCompras() { return this.compras; }

	public void addCompra(Compra compra) { this.compras.add(compra); }

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

}
