package com.ufcg.psoft.mercadofacil.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ErroCompra {

    static final String COMPRA_NAO_CADASTRADA = "Compra com id %s não está cadastrada";
    static final String FORMA_DE_PAGAMENTO_INDISPONIVEL = "Forma de pagamento %s não está disponível";
    static final String ENTREGA_INDISPONIVEL = "O tipo de entrega %s não está disponível";

    public static ResponseEntity<?> erroCompraNaoEnconrtrada(long id) {
        return new ResponseEntity<CustomErrorType>(new CustomErrorType(String.format(ErroCompra.COMPRA_NAO_CADASTRADA, id)),
                HttpStatus.NOT_FOUND);
    }

    public static ResponseEntity<?> erroFormaDePagamentoNaoDisponivel(String formaDePagamentoNome) {
        return new ResponseEntity<CustomErrorType>(new CustomErrorType(String.format(ErroCompra.FORMA_DE_PAGAMENTO_INDISPONIVEL, formaDePagamentoNome)),
                HttpStatus.NOT_IMPLEMENTED);
    }

    public static ResponseEntity<?> erroEntregaNaoDisponivel(String entregaName) {
        return new ResponseEntity<CustomErrorType>(new CustomErrorType(String.format(ErroCompra.ENTREGA_INDISPONIVEL, entregaName)),
                HttpStatus.NOT_IMPLEMENTED);
    }

}
