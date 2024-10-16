package br.com.postech.java.tech.challenge.cortistyle.domain.login.login.service;

import br.com.postech.java.tech.challenge.cortistyle.application.login.login.request.LoginRequest;
import br.com.postech.java.tech.challenge.cortistyle.application.login.login.response.LoginResponse;
import br.com.postech.java.tech.challenge.cortistyle.domain.usuario.entity.Usuario;
import br.com.postech.java.tech.challenge.cortistyle.infrastructure.exceptions.PolicyException;
import br.com.postech.java.tech.challenge.cortistyle.infrastructure.repositories.usuario.UsuarioRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LoginUsuarioService {

    private final UsuarioRepository repository;

    public LoginResponse login(@Valid LoginRequest request) {
        Optional<Usuario> usuario = repository.findByUsernameAndPassword(request.getUsername(),
                Base64.getEncoder().encodeToString(request.getPassword().getBytes()));

        if (!usuario.isPresent()) {
            throw new PolicyException("Dados inválidos");
        }

        usuario.get().setToken(UUID.randomUUID().toString());

        repository.save(usuario.get());

        return new LoginResponse(usuario.get().getId(), usuario.get().getToken());
    }
}
