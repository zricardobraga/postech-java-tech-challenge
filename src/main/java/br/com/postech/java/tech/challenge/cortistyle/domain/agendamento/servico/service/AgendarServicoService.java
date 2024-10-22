package br.com.postech.java.tech.challenge.cortistyle.domain.agendamento.servico.service;

import br.com.postech.java.tech.challenge.cortistyle.application.agendamento.servico.request.AgendarServicoRequest;
import br.com.postech.java.tech.challenge.cortistyle.application.agendamento.servico.request.ConfirmarAgendamentoRequest;
import br.com.postech.java.tech.challenge.cortistyle.domain.agendamento.servico.entity.AgendamentoServico;
import br.com.postech.java.tech.challenge.cortistyle.domain.agendamento.servico.enums.StatusAgendamentoEnum;
import br.com.postech.java.tech.challenge.cortistyle.domain.cadastros.filial.entity.BarbeiroFilial;
import br.com.postech.java.tech.challenge.cortistyle.domain.login.usuario.entity.Usuario;
import br.com.postech.java.tech.challenge.cortistyle.infrastructure.enums.TipoUsuarioEnum;
import br.com.postech.java.tech.challenge.cortistyle.infrastructure.exceptions.PolicyException;
import br.com.postech.java.tech.challenge.cortistyle.infrastructure.repositories.agendamento.AgendamentoServicoRepository;
import br.com.postech.java.tech.challenge.cortistyle.infrastructure.repositories.cadastros.barbeiro.BarbeiroHorarioRepository;
import br.com.postech.java.tech.challenge.cortistyle.infrastructure.repositories.cadastros.barbeiro.ServicoBarbeiroRepository;
import br.com.postech.java.tech.challenge.cortistyle.infrastructure.repositories.cadastros.filial.FilialBarbeiroRepository;
import br.com.postech.java.tech.challenge.cortistyle.infrastructure.repositories.login.usuario.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AgendarServicoService {

    private final AgendamentoServicoRepository repository;
    private final UsuarioRepository usuarioRepository;
    private final FilialBarbeiroRepository filialBarbeiroRepository;
    private final ServicoBarbeiroRepository servicoBarbeiroRepository;
    private final BarbeiroHorarioRepository barbeiroHorarioRepository;

    public void agendar(AgendarServicoRequest request) {

        var cliente = usuarioRepository.findByIdAndTpUsuario(request.getClienteId(), TipoUsuarioEnum.CLIENTE);
        if (!cliente.isPresent()) {
            throw new PolicyException("Cliente não encontrado.");
        }

        List<BarbeiroFilial> barbeirosFilialList = filialBarbeiroRepository.findAllByFilialId(request.getFilialId());

        var filialEncontrada = barbeirosFilialList.stream()
                .map(BarbeiroFilial::getFilial)
                .findFirst();

        if (filialEncontrada.isEmpty()) {
            throw new PolicyException("Filial, não encontrada.");
        }

        var barbeirosDaFilial = barbeirosFilialList
                .stream()
                .map(BarbeiroFilial::getBarbeiro)
                .collect(Collectors.toList());

        if (barbeirosDaFilial.isEmpty()) {
            throw new PolicyException("Filial, não há barbeiros cadastrados");
        }

        var barbeiroEncontrado = barbeirosDaFilial.stream()
                .filter(barbeiro -> request.getBarbeiroId().equals(barbeiro.getId()))
                .findFirst();

        if (!barbeiroEncontrado.isPresent()) {
            throw new PolicyException("Barbeiro não encontrado na filial de id " + request.getFilialId());
        }

        var servicosBarbeiro =
                servicoBarbeiroRepository.findAllByBarbeiroId(barbeiroEncontrado.get().getId());

        var servicoEncontrado = servicosBarbeiro.stream()
                .filter(servico -> request.getServicoId().equals(servico.getId()))
                .findFirst();

        if (!servicoEncontrado.isPresent()) {
            throw new PolicyException("Servico não encontrado para barbeiro de id " + request.getBarbeiroId());
        }

        var horarioBarbeiro = barbeiroHorarioRepository.findById(request.getHorarioBarbeiroId());
        if (!horarioBarbeiro.isPresent()) {
            throw new PolicyException("Horário não encontrado para barbeiro de id " + request.getBarbeiroId());
        }

        var horarioJaAgendado =
                repository.findByDataEqualsAndHorarioBarbeiroBarbeiroId(LocalDate.now(), horarioBarbeiro.get().getId());
        if (horarioJaAgendado.isPresent()) {
            throw new PolicyException("Horário já agendado.");
        }

        AgendamentoServico agendamento = new AgendamentoServico();
        agendamento.setData(LocalDate.now());
        agendamento.setStatus(StatusAgendamentoEnum.AGUARDANDO_CONFIRMACAO);
        agendamento.setCliente(cliente.get());
        agendamento.setBarbeiro(barbeiroEncontrado.get());
        agendamento.setFilial(filialEncontrada.get());
        agendamento.setServico(servicoEncontrado.get());
        agendamento.setHorarioBarbeiro(horarioBarbeiro.get());

        repository.save(agendamento);
        // TODO: Notifica barbeiro para confirmar agendamento
    }

    public StatusAgendamentoEnum confirmar(ConfirmarAgendamentoRequest request) {

        Optional<AgendamentoServico> agendamento = repository.findById(request.getAgendamentoId());

        if (!agendamento.isPresent()) {
            throw new PolicyException("Agendamento não encontrado");
        }

        Optional<Usuario> usuario = usuarioRepository.findById(request.getUsuarioId());

        if (!usuario.isPresent()) {
            throw new PolicyException("Usuário não encontrado");
        }

        TipoUsuarioEnum tpUsuario = usuario.get().getTpUsuario();
        StatusAgendamentoEnum statusAgendamentoEnum;

        if (TipoUsuarioEnum.CLIENTE.equals(tpUsuario)) {
            // Cliente só cancela agendamento e solicita agendamento
            statusAgendamentoEnum = StatusAgendamentoEnum.CANCELADO;
        } else {
            statusAgendamentoEnum =
                    request.getConfirmar() ? StatusAgendamentoEnum.CONFIRMADO : StatusAgendamentoEnum.CANCELADO;
        }

        // atualiza status agendamento
        agendamento.get().setStatus(statusAgendamentoEnum);
        repository.save(agendamento.get());

        if (TipoUsuarioEnum.CLIENTE.equals(tpUsuario)) {
            // TODO:Notifica barbeiro sobre cancelamento agendamento
        } else {
            // TODO: Notifica cliente sobre confirmacao agendamento
        }

        return statusAgendamentoEnum;
    }
}
