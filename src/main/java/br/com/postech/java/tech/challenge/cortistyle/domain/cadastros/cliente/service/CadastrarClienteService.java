package br.com.postech.java.tech.challenge.cortistyle.domain.cadastros.cliente.service;

import br.com.postech.java.tech.challenge.cortistyle.application.cadastros.cliente.request.CadastrarClienteRequest;
import br.com.postech.java.tech.challenge.cortistyle.domain.login.usuario.entity.Usuario;
import br.com.postech.java.tech.challenge.cortistyle.infrastructure.exceptions.PolicyException;
import br.com.postech.java.tech.challenge.cortistyle.infrastructure.repositories.login.usuario.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Base64;

@RequiredArgsConstructor
@Service
public class CadastrarClienteService {

    private final UsuarioRepository repository;

    public void cadastrar(CadastrarClienteRequest request) {
        if (repository.findByUsername(request.getUsuario()).isPresent()) {
            throw new PolicyException("Username inválido.");
        }

        Usuario cliente = request.toCliente();
        cliente.setPassword(Base64.getEncoder().encodeToString(cliente.getPassword().getBytes()));

        repository.save(cliente);
    }
}
