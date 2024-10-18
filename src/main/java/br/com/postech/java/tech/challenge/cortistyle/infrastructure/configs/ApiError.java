package br.com.postech.java.tech.challenge.cortistyle.infrastructure.configs;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApiError {

    private Integer codeStatus;
    private String message;

}
