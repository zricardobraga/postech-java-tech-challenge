package br.com.postech.java.tech.challenge.cortistyle.application.login.login.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@Getter
@AllArgsConstructor
@SuppressWarnings("serial")
public class LoginResponse implements Serializable {

    private Long usuarioId;
    private String usuarioToken;

}
