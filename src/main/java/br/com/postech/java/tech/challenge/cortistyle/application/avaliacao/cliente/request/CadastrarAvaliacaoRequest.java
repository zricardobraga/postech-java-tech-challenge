package br.com.postech.java.tech.challenge.cortistyle.application.avaliacao.cliente.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class CadastrarAvaliacaoRequest {
    @NotNull
    private Long agendamentoId;
    @NotNull
    private String comentario;
    @Size(min = 0, max =5)
    @NotNull
    private Integer nota;
}
