package br.com.postech.java.tech.challenge.cortistyle.domain.login.login.service;

import br.com.postech.java.tech.challenge.cortistyle.application.login.login.request.LogoutResquest;
import br.com.postech.java.tech.challenge.cortistyle.domain.usuario.entity.Usuario;
import br.com.postech.java.tech.challenge.cortistyle.infrastructure.repositories.usuario.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LogoutUsuarioService {

    private final UsuarioRepository repository;

    public void logout(LogoutResquest request) {
        Optional<Usuario> usuario = repository.findByToken(request.getUsuarioToken());

        if (usuario.isPresent()) {
            usuario.get().setToken(null);
            repository.save(usuario.get());
        }
    }
}
