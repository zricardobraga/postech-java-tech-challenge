package br.com.postech.java.tech.challenge.cortistyle.domain.filial.service;

import br.com.postech.java.tech.challenge.cortistyle.application.filial.request.CadastrarFilialRequest;
import br.com.postech.java.tech.challenge.cortistyle.application.filial.response.CadastrarFilialResponse;
import br.com.postech.java.tech.challenge.cortistyle.domain.usuario.entity.Usuario;
import br.com.postech.java.tech.challenge.cortistyle.infrastructure.enums.TipoUsuarioEnum;
import br.com.postech.java.tech.challenge.cortistyle.infrastructure.exceptions.PolicyException;
import br.com.postech.java.tech.challenge.cortistyle.infrastructure.repositories.filial.FilialRepository;
import br.com.postech.java.tech.challenge.cortistyle.infrastructure.repositories.usuario.UsuarioRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CadastrarFilialService {

    private final FilialRepository repository;
    private final UsuarioRepository usuarioRepository;

    public CadastrarFilialResponse cadastrar(@Valid CadastrarFilialRequest request) {
        Optional<Usuario> gestor =
                usuarioRepository.findByIdAndTpUsuario(Long.valueOf(request.getGestorId()), TipoUsuarioEnum.GESTOR);

        if (!gestor.isPresent()) {
            throw new PolicyException("Gestor inválido para cadastro de filiais.");
        }

        var filialCadastrada = repository.save(request.toFilial());

        return new CadastrarFilialResponse(filialCadastrada.getNome());
    }
}
