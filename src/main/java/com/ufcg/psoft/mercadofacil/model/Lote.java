package com.ufcg.psoft.mercadofacil.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.Objects;

@Entity
public class Lote {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@OneToOne
    private Produto produto;
    private int numeroDeItens;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lote lote = (Lote) o;
        return id.equals(lote.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    private Lote() { }
    
    public Lote(Produto produto, int numeroDeItens) {
        this.produto = produto;
        this.numeroDeItens = numeroDeItens;
    }

    public Long getId() {
        return id;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getNumeroDeItens() {
        return numeroDeItens;
    }

    public void setNumeroDeItens(int numeroDeItens) {
        this.numeroDeItens = numeroDeItens;
    }

    @Override
    public String toString() {
        return "Lote{" +
                "id=" + id +
                ", produto=" + produto.getId() +
                ", numeroDeItens=" + numeroDeItens + '\'' +
                '}';
    }
}
