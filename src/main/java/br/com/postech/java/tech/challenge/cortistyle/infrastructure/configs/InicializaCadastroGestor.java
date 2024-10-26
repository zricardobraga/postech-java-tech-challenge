package br.com.postech.java.tech.challenge.cortistyle.infrastructure.configs;

import br.com.postech.java.tech.challenge.cortistyle.domain.login.usuario.entity.Usuario;
import br.com.postech.java.tech.challenge.cortistyle.infrastructure.enums.TipoUsuarioEnum;
import br.com.postech.java.tech.challenge.cortistyle.infrastructure.repositories.login.usuario.UsuarioRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

import java.util.Base64;

@RequiredArgsConstructor
@Configuration
public class InicializaCadastroGestor {

    private final UsuarioRepository repository;

    @PostConstruct
    void cadastraGestor() {
        String pass = Base64.getEncoder().encodeToString("123".getBytes());
        var gestor = new Usuario(null, "Gestor X", "cpf.teste.cpf-te", "admin", pass, "token", TipoUsuarioEnum.GESTOR
                , null);
        repository.save(gestor);
    }
}
