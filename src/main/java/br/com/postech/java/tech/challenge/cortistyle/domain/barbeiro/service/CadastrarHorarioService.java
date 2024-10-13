package br.com.postech.java.tech.challenge.cortistyle.domain.barbeiro.service;

import br.com.postech.java.tech.challenge.cortistyle.application.barbeiro.request.CadastrarHorarioBarbeiroRequest;
import br.com.postech.java.tech.challenge.cortistyle.application.barbeiro.response.CadastroHorarioBarbeiroResponse;
import br.com.postech.java.tech.challenge.cortistyle.domain.barbeiro.entity.BarbeiroHorario;
import br.com.postech.java.tech.challenge.cortistyle.domain.usuario.entity.Usuario;
import br.com.postech.java.tech.challenge.cortistyle.infrastructure.enums.TipoUsuarioEnum;
import br.com.postech.java.tech.challenge.cortistyle.infrastructure.exceptions.PolicyException;
import br.com.postech.java.tech.challenge.cortistyle.infrastructure.repositories.barbeiro.BarbeiroHorarioRepository;
import br.com.postech.java.tech.challenge.cortistyle.infrastructure.repositories.usuario.UsuarioRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DateTimeException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CadastrarHorarioService {

    private final BarbeiroHorarioRepository repository;
    private final UsuarioRepository usuarioRepository;

    public List<CadastroHorarioBarbeiroResponse> cadastrar(@Valid CadastrarHorarioBarbeiroRequest request) {

        try {
            DateTimeFormatter df = DateTimeFormatter.ofPattern("HH:mm");
            LocalTime.parse(request.getHorario(), df);
        } catch (DateTimeException ex) {
            throw new PolicyException("Horário fora do padrão HH:mm");
        }

        Optional<Usuario> barbeiro =
                usuarioRepository.findByIdAndTpUsuario(request.getBarbeiroId(), TipoUsuarioEnum.BARBEIRO);
        if (!barbeiro.isPresent()) {
            throw new PolicyException("Barbeiro inválido");
        }

        Optional<BarbeiroHorario> horarioBarbeiroExistente =
                repository.findByBarbeiroIdAndHorario(request.getBarbeiroId(), request.getHorario());
        if (horarioBarbeiroExistente.isPresent()) {
            throw new PolicyException("Horário já cadastrado ao barbeiro.");
        }

        var novoHorarioBarbeiro = new BarbeiroHorario(null, request.getBarbeiroId(),
                request.getHorario(), Boolean.FALSE);

        repository.save(novoHorarioBarbeiro);

        List<CadastroHorarioBarbeiroResponse> horariosDoBarbeiro =
                repository.findAllByBarbeiroId(request.getBarbeiroId())
                        .stream()
                        .map(horario -> new CadastroHorarioBarbeiroResponse(horario.getHorario(),
                                horario.getAgendado()))
                        .collect(Collectors.toList());

        return horariosDoBarbeiro;
    }
}
