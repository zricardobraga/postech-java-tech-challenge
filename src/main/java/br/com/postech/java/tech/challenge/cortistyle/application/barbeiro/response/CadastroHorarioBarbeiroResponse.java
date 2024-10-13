package br.com.postech.java.tech.challenge.cortistyle.application.barbeiro.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@Getter
@AllArgsConstructor
@SuppressWarnings("serial")
public class CadastroHorarioBarbeiroResponse implements Serializable {

    private String horario;
    private Boolean agendado;
}
