package br.com.postech.java.tech.challenge.cortistyle.application.agendamento.servico.request;

import lombok.Getter;

@Getter
public class ConfirmarAgendamentoRequest {

    private Boolean confirmar;
    private Long agendamentoId;
    private Long usuarioId;

}
