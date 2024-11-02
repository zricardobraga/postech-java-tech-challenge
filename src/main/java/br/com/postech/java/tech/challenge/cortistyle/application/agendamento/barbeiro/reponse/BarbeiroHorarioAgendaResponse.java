package br.com.postech.java.tech.challenge.cortistyle.application.agendamento.barbeiro.reponse;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDate;

@AllArgsConstructor
@Getter
@SuppressWarnings("serial")
public class BarbeiroHorarioAgendaResponse implements Serializable {

    private Long id;
    private String horario;
    private Boolean agendado;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate data;

}
