package br.com.postech.java.tech.challenge.cortistyle.application.cadastros.cliente;

import br.com.postech.java.tech.challenge.cortistyle.application.cadastros.cliente.request.CadastrarClienteRequest;
import br.com.postech.java.tech.challenge.cortistyle.application.cadastros.cliente.response.CadastrarClienteResponse;
import br.com.postech.java.tech.challenge.cortistyle.domain.cadastros.cliente.service.CadastrarClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Cadastros")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/clientes", produces = "application/json")
public class ClienteController {

    private final CadastrarClienteService cadastrarClienteService;

    @Operation(summary = "Cadastrar novo cliente")
    @PostMapping
    public ResponseEntity<CadastrarClienteResponse> cadastrar(@RequestBody @Valid CadastrarClienteRequest request) {
        log.info("Cadastrando novo cliente de nome: {}", request.getNome());
        cadastrarClienteService.cadastrar(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
