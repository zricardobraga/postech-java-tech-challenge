package br.com.postech.java.tech.challenge.cortistyle.application.agendamento.barbeiro.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class ListarAgendamentoBarbeiroRequest {

    @NotNull
    private LocalDate data;
    @NotNull
    private Long barbeiroId;

}
