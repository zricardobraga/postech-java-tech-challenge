package br.com.postech.java.tech.challenge.cortistyle.application.agendamento.barbeiro.reponse;

import br.com.postech.java.tech.challenge.cortistyle.domain.agendamento.servico.enums.StatusAgendamentoEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
@SuppressWarnings("serial")
public class BarbeiroAgendamentoResponse implements Serializable {

    private Long agendamentoId;
    private StatusAgendamentoEnum status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate data;
}
