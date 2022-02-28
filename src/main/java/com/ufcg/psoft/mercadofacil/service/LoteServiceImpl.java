package com.ufcg.psoft.mercadofacil.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufcg.psoft.mercadofacil.model.Lote;
import com.ufcg.psoft.mercadofacil.model.Produto;
import com.ufcg.psoft.mercadofacil.repository.LoteRepository;

@Service
public class LoteServiceImpl implements LoteService {

	@Autowired
	private LoteRepository loteRepository;

	@Override
	public List<Lote> listarLotes() {
		return loteRepository.findAll();
	}

	@Override
	public void salvarLote(Lote lote) {
		loteRepository.save(lote);
	}

	@Override
	public Lote criaLote(int numItens, Produto produto) {
		Lote lote = new Lote(produto, numItens);
		return lote;
	}

	@Override
	public Lote verificaLoteProduto(Produto produto) {
		List<Lote> lotes = listarLotes();

		for (Lote lote : lotes) {
			if (lote.getProduto().equals(produto)) {
				return lote;
			}
		}
		return null;
	}
}