package br.com.postech.java.tech.challenge.cortistyle.application.barbeiro.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
public class CadastrarHorarioBarbeiroRequest {

    @NotBlank
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private String horario;
    @NotNull
    private Long barbeiroId;

}
