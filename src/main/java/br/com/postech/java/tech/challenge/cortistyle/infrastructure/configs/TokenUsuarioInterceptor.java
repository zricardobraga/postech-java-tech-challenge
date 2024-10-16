package br.com.postech.java.tech.challenge.cortistyle.infrastructure.configs;

import br.com.postech.java.tech.challenge.cortistyle.domain.usuario.entity.Usuario;
import br.com.postech.java.tech.challenge.cortistyle.infrastructure.repositories.usuario.UsuarioRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class TokenUsuarioInterceptor implements HandlerInterceptor {

    private final UsuarioRepository repository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String requestURI = request.getRequestURL().toString();

        if (requestURI.contains("swagger") || requestURI.contains("login")
                || requestURI.contains("logout") || requestURI.contains("api")) {
            return true;
        }

        String headerId = Optional.ofNullable(request.getHeader("client-id")).orElse("");
        log.info("Requisição interceptada usuario de id: {}", headerId);

        String token = Optional.ofNullable(request.getHeader("Authorization")).orElse("");
        if (headerId.isBlank() || token.isBlank()) {
            return false;
        }

        Optional<Usuario> usuario = repository.findById(Long.valueOf(headerId));
        if (!usuario.isPresent()) {
            log.info("usuario invalido", headerId);
            return false;
        }

        String usuarioToken = usuario.get().getToken();
        if (!token.equals(usuarioToken)) {
            log.info("Token invalido");
            return false;
        }

        return true;
    }
}