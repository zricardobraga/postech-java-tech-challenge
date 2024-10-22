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
        String token = Optional.ofNullable(request.getHeader("token")).orElse("");

        List<String> urisAutenticadas = List.of("/filiais", "/barbeiros", "/servicos", "/agendamentos");
        AtomicBoolean uriPrecisaEstarAutenticado = new AtomicBoolean(false);

        urisAutenticadas.forEach(uriAutenticada -> {
            if (request.getRequestURI().startsWith(uriAutenticada)) {
                uriPrecisaEstarAutenticado.set(true);
            }
        });

        if (uriPrecisaEstarAutenticado.get()) {

            if (headerId.isBlank() || token.isBlank()) {
                throw new PolicyException("Propriedade Header não encontrada");
            }

            Optional<Usuario> usuario = repository.findById(Long.valueOf(headerId));
            if (!usuario.isPresent()) {
                throw new PolicyException(NAO_AUTENTICADO);
            }

            String usuarioToken = usuario.get().getToken();
            if (usuarioToken == null || !token.equals(usuarioToken)) {
                throw new PolicyException(TOKEN_INVALIDO);
            }

            //TODO: Autorizacao rotas gestor, barbeiro e cliente
        }

        return true;
    }
}