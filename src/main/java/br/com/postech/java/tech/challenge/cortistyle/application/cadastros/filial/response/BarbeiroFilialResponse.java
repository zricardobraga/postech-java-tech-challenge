package br.com.postech.java.tech.challenge.cortistyle.application.cadastros.filial.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("serial")
public class BarbeiroFilialResponse implements Serializable {

    private Long barbeiroId;
    private String barbeiroNome;
}
