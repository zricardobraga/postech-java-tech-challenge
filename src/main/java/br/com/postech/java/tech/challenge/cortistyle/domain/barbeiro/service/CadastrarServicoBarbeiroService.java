package br.com.postech.java.tech.challenge.cortistyle.domain.barbeiro.service;

import br.com.postech.java.tech.challenge.cortistyle.application.barbeiro.request.CadastrarServicoBarbeiroRequest;
import br.com.postech.java.tech.challenge.cortistyle.domain.barbeiro.entity.BarbeiroServico;
import br.com.postech.java.tech.challenge.cortistyle.infrastructure.enums.TipoUsuarioEnum;
import br.com.postech.java.tech.challenge.cortistyle.infrastructure.exceptions.PolicyException;
import br.com.postech.java.tech.challenge.cortistyle.infrastructure.repositories.barbeiro.ServicoBarbeiroRepository;
import br.com.postech.java.tech.challenge.cortistyle.infrastructure.repositories.servico.ServicoRepository;
import br.com.postech.java.tech.challenge.cortistyle.infrastructure.repositories.usuario.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CadastrarServicoBarbeiroService {

    private final ServicoBarbeiroRepository repository;
    private final UsuarioRepository usuarioRepository;
    private final ServicoRepository servicoRepository;

    public void cadastrar(CadastrarServicoBarbeiroRequest request) {
        var servico = servicoRepository.findById(request.getServicoId());
        var barbeiro =
                usuarioRepository.findByIdAndTpUsuario(request.getBarbeiroId(), TipoUsuarioEnum.BARBEIRO);

        if (!servico.isPresent()) {
            throw new PolicyException("Serviço não encontrado");
        }

        if (!barbeiro.isPresent()) {
            throw new PolicyException("Barbeiro não encontrado");
        }

        repository.save(new BarbeiroServico(null, request.getBarbeiroId(), servico.get()));
    }
}
