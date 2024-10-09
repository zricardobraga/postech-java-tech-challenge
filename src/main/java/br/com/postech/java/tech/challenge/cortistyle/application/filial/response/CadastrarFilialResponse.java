package br.com.postech.java.tech.challenge.cortistyle.application.filial.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@Getter
@AllArgsConstructor
@SuppressWarnings("serial")
public class CadastrarFilialResponse  implements Serializable {

    private String nome;

}
