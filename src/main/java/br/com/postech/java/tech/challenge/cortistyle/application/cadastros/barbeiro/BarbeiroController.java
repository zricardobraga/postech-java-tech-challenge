package br.com.postech.java.tech.challenge.cortistyle.application.cadastros.barbeiro;

import br.com.postech.java.tech.challenge.cortistyle.application.cadastros.barbeiro.request.CadastrarBarbeiroRequest;
import br.com.postech.java.tech.challenge.cortistyle.application.cadastros.barbeiro.request.CadastrarHorarioBarbeiroRequest;
import br.com.postech.java.tech.challenge.cortistyle.application.cadastros.barbeiro.request.CadastrarServicoBarbeiroRequest;
import br.com.postech.java.tech.challenge.cortistyle.application.cadastros.barbeiro.response.CadastrarBarbeiroResponse;
import br.com.postech.java.tech.challenge.cortistyle.application.cadastros.barbeiro.response.HorarioBarbeiroResponse;
import br.com.postech.java.tech.challenge.cortistyle.application.cadastros.barbeiro.response.ServicoBarbeiroResponse;
import br.com.postech.java.tech.challenge.cortistyle.domain.cadastros.barbeiro.service.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Tag(name = "Cadastros")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/barbeiros", produces = "application/json")
public class BarbeiroController {

    private final CadastrarBarbeiroService cadastrarBarbeiroService;
    private final CadastrarHorarioService cadastrarHorarioService;
    private final CadastrarServicoBarbeiroService cadastrarServicoBarbeiroService;
    private final ListarServicoBarbeiroService listarServicosBarbeiroService;
    private final ListarHorariosBarbeiroService listarHorariosBarbeiroService;

    @Operation(summary = "Cadastrar novo barbeiro")
    @PostMapping
    public ResponseEntity<CadastrarBarbeiroResponse> cadastrar(@RequestBody @Valid CadastrarBarbeiroRequest request) {
        log.info("Cadastrando novo barbeiro");
        CadastrarBarbeiroResponse response = cadastrarBarbeiroService.cadastrar(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(summary = "Cadastrar horário")
    @PostMapping("/horarios")
    public ResponseEntity<List<HorarioBarbeiroResponse>> cadastrarHorario(@RequestBody @Valid
                                                                          CadastrarHorarioBarbeiroRequest request) {
        log.info("Cadastrando novo horario para o barbeiro de id: {}", request.getBarbeiroId());
        List<HorarioBarbeiroResponse> response = cadastrarHorarioService.cadastrar(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(summary = "Cadastrar serviço barbeiro")
    @PostMapping("/servicos")
    public ResponseEntity<List<ServicoBarbeiroResponse>> cadastrarServico(@RequestBody @Valid
                                                                          CadastrarServicoBarbeiroRequest request) {
        log.info("Cadastrando novo serviço para o barbeiro de id: {}", request.getBarbeiroId());
        cadastrarServicoBarbeiroService.cadastrar(request);
        ArrayList<ServicoBarbeiroResponse> response = listarServicosBarbeiroService.listar(request.getBarbeiroId());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(summary = "Listar serviços barbeiro")
    @GetMapping("/{id}/servicos")
    public ResponseEntity<List<ServicoBarbeiroResponse>> listarServicos(@PathVariable Long id) {
        log.info("Listando servicos para o barbeiro de id: {}", id);
        ArrayList<ServicoBarbeiroResponse> response = listarServicosBarbeiroService.listar(id);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Listar horários barbeiro")
    @GetMapping("/{id}/horarios")
    public ResponseEntity<List<HorarioBarbeiroResponse>> listarHorarios(@PathVariable Long id) {
        log.info("Listando horarios para o barbeiro de id: {}", id);
        ArrayList<HorarioBarbeiroResponse> response = listarHorariosBarbeiroService.listar(id);
        return ResponseEntity.ok(response);
    }
}
