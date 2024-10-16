package br.com.postech.java.tech.challenge.cortistyle.application.login.login.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class LoginRequest {

    @NotBlank
    private String username;
    @NotBlank
    private String password;

}
