package br.com.postech.java.tech.challenge.cortistyle.infrastructure.configs;

import br.com.postech.java.tech.challenge.cortistyle.domain.usuario.entity.Usuario;
import br.com.postech.java.tech.challenge.cortistyle.infrastructure.enums.TipoUsuarioEnum;
import br.com.postech.java.tech.challenge.cortistyle.infrastructure.repositories.usuario.UsuarioRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class InicializaCadastroGestor {

    private final UsuarioRepository repository;

    @PostConstruct
    void cadastraGestor() {
        var gestor = new Usuario(null, "Gestor X", "cpf.teste.cpf-te", TipoUsuarioEnum.GESTOR);
        repository.save(gestor);
    }
}
