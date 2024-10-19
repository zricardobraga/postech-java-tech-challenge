package br.com.postech.java.tech.challenge.cortistyle.application.login.usuario;

import br.com.postech.java.tech.challenge.cortistyle.application.login.usuario.request.LoginRequest;
import br.com.postech.java.tech.challenge.cortistyle.application.login.usuario.request.LogoutResquest;
import br.com.postech.java.tech.challenge.cortistyle.application.login.usuario.response.LoginResponse;
import br.com.postech.java.tech.challenge.cortistyle.domain.login.usuario.service.LoginUsuarioService;
import br.com.postech.java.tech.challenge.cortistyle.domain.login.usuario.service.LogoutUsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Login")
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(produces = "application/json")
public class LoginController {

    private final LoginUsuarioService loginUsuarioService;
    private final LogoutUsuarioService logoutUsuarioService;

    @Operation(summary = "Realizar login usuário")
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest request) {
        log.info("Login usuario: {}", request.getUsername());
        LoginResponse response = loginUsuarioService.login(request);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Realizar logout usuário")
    @PostMapping("/logout")
    public ResponseEntity<Void> login(@RequestBody @Valid LogoutResquest request) {
        logoutUsuarioService.logout(request);
        return ResponseEntity.ok().build();
    }
}
