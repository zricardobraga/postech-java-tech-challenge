package br.com.postech.java.tech.challenge.cortistyle.application.filial.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CadastrarFilialRequest {

    @NotBlank
    private String gestorId;
    @NotBlank
    private String nome;
    @NotBlank
    private String cnpj;

}
