package br.com.postech.java.tech.challenge.cortistyle.application.cadastros.barbeiro.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@Getter
@AllArgsConstructor
@SuppressWarnings("serial")
public class HorarioBarbeiroResponse implements Serializable {

    private Long id;
    private String horario;

}
