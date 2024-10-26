package br.com.postech.java.tech.challenge.cortistyle.domain.cadastros.barbeiro.service;

import br.com.postech.java.tech.challenge.cortistyle.application.cadastros.barbeiro.request.CadastrarBarbeiroRequest;
import br.com.postech.java.tech.challenge.cortistyle.application.cadastros.barbeiro.response.CadastrarBarbeiroResponse;
import br.com.postech.java.tech.challenge.cortistyle.domain.login.usuario.entity.Usuario;
import br.com.postech.java.tech.challenge.cortistyle.infrastructure.enums.TipoUsuarioEnum;
import br.com.postech.java.tech.challenge.cortistyle.infrastructure.exceptions.PolicyException;
import br.com.postech.java.tech.challenge.cortistyle.infrastructure.repositories.login.usuario.UsuarioRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Optional;
import java.util.UUID;

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

        Usuario barbeiro = request.toBarbeiro();
        barbeiro.setUsername(this.generateUsernameBarbeiro(barbeiro.getNome()));
        String senha = UUID.randomUUID().toString();
        barbeiro.setPassword(Base64.getEncoder().encodeToString(senha.getBytes()));

        Optional<Usuario> byUsername;
        int cont = 1;

        do {
            byUsername = repository.findByUsername(barbeiro.getUsername());

            if (byUsername.isPresent()) {
                cont++;
                String[] usernameSplit = barbeiro.getUsername().split("@");
                String novoUsername = usernameSplit[0].concat(String.valueOf(cont));
                String email = "@".concat(usernameSplit[1]);

                barbeiro.setUsername(novoUsername.concat(email));
            }
        } while (byUsername.isPresent());


        var barbeiroCadastrado = repository.save(barbeiro);

        // TODO: SistemaExterno notifica barbeiro e envia informacoes do cadastro

        return new CadastrarBarbeiroResponse(barbeiroCadastrado.getUsername(), senha);
    }

    private String generateUsernameBarbeiro(String nomeBarbebeiro) {
        String[] nomeSobreNome = nomeBarbebeiro.split(" ");
        String nome = nomeSobreNome[0].trim().toLowerCase();

        String primeiroSobreNome = "";
        if (nomeSobreNome.length > 1) {
            primeiroSobreNome = Optional.ofNullable(nomeSobreNome[1]).orElse("").trim().toLowerCase();
        }

        String username = nome.substring(0, 1).concat(primeiroSobreNome).concat("@cortistyle.com.br");

        return username;
    }
}
