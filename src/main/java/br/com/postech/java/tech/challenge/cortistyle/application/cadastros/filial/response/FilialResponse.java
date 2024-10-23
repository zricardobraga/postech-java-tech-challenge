package br.com.postech.java.tech.challenge.cortistyle.application.cadastros.filial.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@Getter
@AllArgsConstructor
@SuppressWarnings("serial")
public class FilialResponse implements Serializable {

    private Long id;
    private String nome;

}
