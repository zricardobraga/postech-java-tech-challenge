package br.com.postech.java.tech.challenge.cortistyle.domain.barbeiro.service;

import br.com.postech.java.tech.challenge.cortistyle.application.barbeiro.request.CadastrarBarbeiroRequest;
import br.com.postech.java.tech.challenge.cortistyle.application.barbeiro.response.CadastrarBarbeiroResponse;
import br.com.postech.java.tech.challenge.cortistyle.domain.usuario.entity.Usuario;
import br.com.postech.java.tech.challenge.cortistyle.infrastructure.enums.TipoUsuarioEnum;
import br.com.postech.java.tech.challenge.cortistyle.infrastructure.exceptions.PolicyException;
import br.com.postech.java.tech.challenge.cortistyle.infrastructure.repositories.usuario.UsuarioRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CadastrarBarbeiroService {

    private final UsuarioRepository repository;

    public CadastrarBarbeiroResponse cadastrar(@Valid CadastrarBarbeiroRequest request) {
        Optional<Usuario> gestor =
                repository.findByIdAndTpUsuario(Long.valueOf(request.getGestorId()), TipoUsuarioEnum.GESTOR);

        if (!gestor.isPresent()) {
            throw new PolicyException("Gestor inválido para cadastro de barbeiros.");
        }

        var barbeiroCadastrado = repository.save(request.toBarbeiro());

        // TODO: cria usuario e senha de login
        // TODO: SistemaExterno notifica barbeiro

        return new CadastrarBarbeiroResponse(barbeiroCadastrado.getNome());
    }
}
