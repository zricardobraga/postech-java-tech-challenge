package br.com.postech.java.tech.challenge.cortistyle.domain.cadastros.servico.service;

import br.com.postech.java.tech.challenge.cortistyle.application.cadastros.servico.request.CadastrarServicoRequest;
import br.com.postech.java.tech.challenge.cortistyle.application.cadastros.servico.response.CadastrarServicoResponse;
import br.com.postech.java.tech.challenge.cortistyle.domain.cadastros.servico.entity.Servico;
import br.com.postech.java.tech.challenge.cortistyle.infrastructure.exceptions.PolicyException;
import br.com.postech.java.tech.challenge.cortistyle.infrastructure.repositories.cadastros.servico.ServicoRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CadastrarServicoService {

    private final ServicoRepository repository;

    public CadastrarServicoResponse cadastrar(@Valid CadastrarServicoRequest request) {
        Optional<Servico> byDescricao = repository.findByDescricao(request.getDescricao());
        if (!byDescricao.isEmpty()) {
            throw new PolicyException("Serviço já cadastrado");
        }
        var servicoCadastrado = repository.save(request.toServico());
        return new CadastrarServicoResponse(servicoCadastrado.getDescricao());
    }
}
