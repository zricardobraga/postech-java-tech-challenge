package br.com.postech.java.tech.challenge.cortistyle.domain.filial.service;

import br.com.postech.java.tech.challenge.cortistyle.application.filial.request.IncluirBarbeiroFilialRequest;
import br.com.postech.java.tech.challenge.cortistyle.domain.filial.entity.BarbeiroFilial;
import br.com.postech.java.tech.challenge.cortistyle.domain.filial.entity.Filial;
import br.com.postech.java.tech.challenge.cortistyle.domain.usuario.entity.Usuario;
import br.com.postech.java.tech.challenge.cortistyle.infrastructure.enums.TipoUsuarioEnum;
import br.com.postech.java.tech.challenge.cortistyle.infrastructure.exceptions.PolicyException;
import br.com.postech.java.tech.challenge.cortistyle.infrastructure.repositories.filial.FilialBarbeiroRepository;
import br.com.postech.java.tech.challenge.cortistyle.infrastructure.repositories.filial.FilialRepository;
import br.com.postech.java.tech.challenge.cortistyle.infrastructure.repositories.usuario.UsuarioRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class IncluirBarbeiroFilialService {

    private final FilialRepository filialRepository;
    private final UsuarioRepository usuarioRepository;
    private final FilialBarbeiroRepository filialBarbeiroRepository;

    public void incluir(@Valid IncluirBarbeiroFilialRequest request) {
        Optional<Usuario> gestor =
                usuarioRepository.findByIdAndTpUsuario(Long.valueOf(request.getGestorId()), TipoUsuarioEnum.GESTOR);

        if (!gestor.isPresent()) {
            throw new PolicyException("Gestor inválido");
        }

        Optional<Filial> filial = filialRepository.findById(request.getFilialId());
        if (!filial.isPresent()) {
            throw new PolicyException("Filial inválida");
        }

        Optional<Usuario> barbeiro =
                usuarioRepository.findByIdAndTpUsuario(request.getBarbeiroId(), TipoUsuarioEnum.BARBEIRO);
        if (!barbeiro.isPresent()) {
            throw new PolicyException("Barbeiro inválido");
        }

        Optional<BarbeiroFilial> barbeiroFilialExistente =
                filialBarbeiroRepository.findByBarbeiroId(request.getBarbeiroId());

        if (barbeiroFilialExistente.isPresent()) {
            throw new PolicyException("Barbeiro já cadastrado");
        }

        BarbeiroFilial novoBarbeiroFilial = request.toFilialBarbeiro();
        novoBarbeiroFilial.setBarbeiro(barbeiro.get());

        filialBarbeiroRepository.save(novoBarbeiroFilial);
    }
}
