package com.ufcg.psoft.mercadofacil.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ErroCompra {

    static final String COMPRA_NAO_CADASTRADA = "Compra com id %s não está cadastrada";

    public static ResponseEntity<?> erroCompraNaoEnconrtrada(long id) {
        return new ResponseEntity<CustomErrorType>(new CustomErrorType(String.format(ErroCompra.COMPRA_NAO_CADASTRADA, id)),
                HttpStatus.NOT_FOUND);
    }
}
