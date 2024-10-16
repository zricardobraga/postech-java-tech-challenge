package br.com.postech.java.tech.challenge.cortistyle.application.cadastros.barbeiro.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@SuppressWarnings("serial")
public class CadastroServicoBarbeiroResponse implements Serializable {

    private Long servicoId;
    private String serviço;
    private BigDecimal valor;

}
