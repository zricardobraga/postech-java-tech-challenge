package br.com.postech.java.tech.challenge.cortistyle.domain.barbeiro.service;

import br.com.postech.java.tech.challenge.cortistyle.application.barbeiro.request.CadastrarBarbeiroRequest;
import br.com.postech.java.tech.challenge.cortistyle.application.barbeiro.response.CadastrarBarbeiroResponse;
import br.com.postech.java.tech.challenge.cortistyle.infrastructure.exceptions.PolicyException;
import br.com.postech.java.tech.challenge.cortistyle.infrastructure.repositories.usuario.UsuarioRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CadastrarBarbeiroService {

    private final UsuarioRepository repository;

    public CadastrarBarbeiroResponse cadastrar(@Valid CadastrarBarbeiroRequest request) {
        //TODO: identificar gestor por seu id e validá-lo.
        //TODO: criar serviço validar inclusão do barbeiro a filial válida.
        //TODO: filial tera barbeiros e um gestor, filial lista barbeiros
        //TODO: barbeiro cadastrar seus serviços
        //TODO: barbeiro cadastrar horarios
        boolean cadastroInvalido = false;

        if (cadastroInvalido) {
            String politicaNaoAceita = "msg sobre parte invalidada no cadastro";
            throw new PolicyException(politicaNaoAceita);
        }

        var barbeiroCadastrado = repository.save(request.toBarbeiro());

        // TODO: cria usuario e senha de login
        // TODO: SistemaExterno notifica barbeiro

        return new CadastrarBarbeiroResponse(barbeiroCadastrado.getNome());
    }
}
