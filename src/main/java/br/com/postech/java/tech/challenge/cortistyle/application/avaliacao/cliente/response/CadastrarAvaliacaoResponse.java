package br.com.postech.java.tech.challenge.cortistyle.application.avaliacao.cliente.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@Getter
@AllArgsConstructor
public class CadastrarAvaliacaoResponse {
    private Integer id;
    private String comentario;
}
