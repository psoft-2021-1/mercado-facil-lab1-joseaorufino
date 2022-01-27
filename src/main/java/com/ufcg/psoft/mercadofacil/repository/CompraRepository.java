package com.ufcg.psoft.mercadofacil.repository;

import java.util.Optional;

import com.ufcg.psoft.mercadofacil.model.Compra;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompraRepository extends JpaRepository<Compra, Long>{

    Optional<Compra> findById(long id);
}
