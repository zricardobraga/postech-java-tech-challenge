package br.com.postech.java.tech.challenge.cortistyle.application.agendamento.servico.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class AgendarServicoRequest {

    @NotNull
    private Long filialId;
    @NotNull
    private Long clienteId;
    @NotNull
    private Long barbeiroId;
    @NotNull
    private Long servicoId;
    @NotNull
    private Long horarioBarbeiroId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private String data;

}
