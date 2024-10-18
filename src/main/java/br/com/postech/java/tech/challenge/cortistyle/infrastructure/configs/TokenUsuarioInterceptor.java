package br.com.postech.java.tech.challenge.cortistyle.infrastructure.configs;

import br.com.postech.java.tech.challenge.cortistyle.domain.login.usuario.entity.Usuario;
import br.com.postech.java.tech.challenge.cortistyle.infrastructure.exceptions.PolicyException;
import br.com.postech.java.tech.challenge.cortistyle.infrastructure.repositories.login.usuario.UsuarioRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

@Slf4j
@Component
@RequiredArgsConstructor
public class TokenUsuarioInterceptor implements HandlerInterceptor {

    public static final String NAO_AUTENTICADO = "Não autenticado";
    public static final String TOKEN_INVALIDO = "Token inválido";

    private final UsuarioRepository repository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String headerId = Optional.ofNullable(request.getHeader("client-id")).orElse("");
        log.info("Requisição interceptada usuario de id: {}", headerId);

        List<String> urisAutenticadas = List.of("/filiais", "/barbeiros", "/servicos");
        AtomicBoolean uriPrecisaEstarAutenticado = new AtomicBoolean(false);

        urisAutenticadas.forEach(uriAutenticada -> {
            if (request.getRequestURI().startsWith(uriAutenticada)) {
                uriPrecisaEstarAutenticado.set(true);
            }
        });

        if (uriPrecisaEstarAutenticado.get()) {

            String token = Optional.ofNullable(request.getHeader("Authorization")).orElse("");
            if (headerId.isBlank() || token.isBlank()) {
                throw new PolicyException(NAO_AUTENTICADO);
            }

            Optional<Usuario> usuario = repository.findById(Long.valueOf(headerId));
            if (!usuario.isPresent()) {
                log.info("usuario invalido", headerId);
                throw new PolicyException(NAO_AUTENTICADO);
            }

            String usuarioToken = usuario.get().getToken();
            if (!token.equals(usuarioToken)) {
                log.info(TOKEN_INVALIDO);
                throw new PolicyException(TOKEN_INVALIDO);
            }
        }

        return true;
    }
}