package br.com.postech.java.tech.challenge.cortistyle.application.cadastros.barbeiro.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@SuppressWarnings("serial")
public class ServicoBarbeiroResponse implements Serializable {

    private Long servicoId;
    private String servico;
    private BigDecimal valor;

}
