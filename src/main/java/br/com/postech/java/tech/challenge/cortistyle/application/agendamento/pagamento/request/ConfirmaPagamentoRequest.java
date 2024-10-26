package br.com.postech.java.tech.challenge.cortistyle.application.agendamento.pagamento.request;

import lombok.Getter;

@Getter
public class ConfirmaPagamentoRequest {

    private Long agendamentoId;
    private Long barbeiroId;

}
