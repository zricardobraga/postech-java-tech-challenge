package br.com.postech.java.tech.challenge.cortistyle.infrastructure.configs;

import br.com.postech.java.tech.challenge.cortistyle.infrastructure.exceptions.PolicyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(PolicyException.class)
    public ApiError handlePolicyException(PolicyException exception) {
        return new ApiError(400, exception.getMessage());
    }
}
