package br.com.postech.java.tech.challenge.cortistyle.application.login.login;

import br.com.postech.java.tech.challenge.cortistyle.application.login.login.request.LoginRequest;
import br.com.postech.java.tech.challenge.cortistyle.application.login.login.response.LoginResponse;
import br.com.postech.java.tech.challenge.cortistyle.domain.login.login.service.LoginUsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/login", produces = "application/json")
public class LoginController {

    private final LoginUsuarioService loginUsuarioService;

    @PostMapping
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest request) {
        log.info("Logando usuario: {}", request.getUsername());
        LoginResponse response = loginUsuarioService.login(request);
        return ResponseEntity.ok(response);
    }
}
