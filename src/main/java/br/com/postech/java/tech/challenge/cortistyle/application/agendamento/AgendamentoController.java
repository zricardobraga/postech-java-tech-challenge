package br.com.postech.java.tech.challenge.cortistyle.application.agendamento;

import br.com.postech.java.tech.challenge.cortistyle.application.agendamento.barbeiro.reponse.BarbeiroAgendamentoResponse;
import br.com.postech.java.tech.challenge.cortistyle.application.agendamento.barbeiro.reponse.BarbeiroHorarioAgendaResponse;
import br.com.postech.java.tech.challenge.cortistyle.application.agendamento.barbeiro.request.ListarAgendamentoBarbeiroRequest;
import br.com.postech.java.tech.challenge.cortistyle.application.agendamento.pagamento.request.ConfirmaPagamentoRequest;
import br.com.postech.java.tech.challenge.cortistyle.application.agendamento.servico.request.AgendarServicoRequest;
import br.com.postech.java.tech.challenge.cortistyle.application.agendamento.servico.request.ConfirmarAgendamentoRequest;
import br.com.postech.java.tech.challenge.cortistyle.domain.agendamento.barbeiro.service.ListarAgendamentosService;
import br.com.postech.java.tech.challenge.cortistyle.domain.agendamento.pagamento.service.ConfirmarServicoRealizadoService;
import br.com.postech.java.tech.challenge.cortistyle.domain.agendamento.servico.enums.StatusAgendamentoEnum;
import br.com.postech.java.tech.challenge.cortistyle.domain.agendamento.servico.service.AgendarServicoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Tag(name = "Agendamentos")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/agendamentos", produces = "application/json")
public class AgendamentoController {

    private final AgendarServicoService agendarServicoService;
    private final ListarAgendamentosService listarAgendamentosService;
    private final ConfirmarServicoRealizadoService confirmarServicoRealizadoService;

    @Operation(summary = "Cadastrar novo agendamento")
    @PostMapping
    public ResponseEntity<Void> agendar(@RequestBody @Valid AgendarServicoRequest request) {
        log.info("Cadastrando novo agendamento: {}", request.toString());
        agendarServicoService.agendar(request);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @Operation(summary = "Confirmar/Cancelar agendamento")
    @PutMapping
    public ResponseEntity<StatusAgendamentoEnum> confirmar(@RequestBody @Valid ConfirmarAgendamentoRequest request) {
        log.info("Confimar novo agendamento");
        StatusAgendamentoEnum response = agendarServicoService.confirmar(request);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @Operation(summary = "Confirma servico agendado realizado")
    @PostMapping("/confirmar/servico")
    public ResponseEntity<Void> confirmarServicoRealizado(@RequestBody @Valid ConfirmaPagamentoRequest request) {
        log.info("Confirmando servico realizado para agendamento de id: {}", request.getAgendamentoId());
        confirmarServicoRealizadoService.confirmar(request);
        return new ResponseEntity(HttpStatus.OK);
    }

    @Operation(summary = "Listar horarios barbeiro para agendamento")
    @PostMapping("/barbeiros/horarios")
    public ResponseEntity<ArrayList<BarbeiroHorarioAgendaResponse>> listarHorarios(@RequestBody @Valid
                                                                                   ListarAgendamentoBarbeiroRequest
                                                                                           request) {
        log.info("Listando horários para agendamento servico do barbeiro de id: {}", request.getBarbeiroId());
        ArrayList<BarbeiroHorarioAgendaResponse> response = listarAgendamentosService.listarHorarios(request);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @Operation(summary = "Listar agendamentos barbeiro")
    @PostMapping("/barbeiros")
    public ResponseEntity<ArrayList<BarbeiroAgendamentoResponse>> listarAgendamentos(@RequestBody @Valid
                                                                                     ListarAgendamentoBarbeiroRequest
                                                                                             request) {
        log.info("Listando agendamentos do barbeiro de id: {}", request.getBarbeiroId());
        ArrayList<BarbeiroAgendamentoResponse> response = listarAgendamentosService.listarAgendamentos(request);
        return new ResponseEntity(response, HttpStatus.OK);
    }
}
