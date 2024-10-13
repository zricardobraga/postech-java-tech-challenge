package br.com.postech.java.tech.challenge.cortistyle.application.servico.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@Getter
@AllArgsConstructor
@SuppressWarnings("serial")
public class CadastrarServicoResponse implements Serializable {

    private String descricao;

}
