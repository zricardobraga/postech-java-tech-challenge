package br.com.postech.java.tech.challenge.cortistyle.application.barbeiro.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CadastrarServicoBarbeiroRequest {

    @NotNull
    private Long servicoId;
    @NotNull
    private Long barbeiroId;

}
