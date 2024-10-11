package br.com.postech.java.tech.challenge.cortistyle.application.barbeiro;

import br.com.postech.java.tech.challenge.cortistyle.application.barbeiro.request.CadastrarBarbeiroRequest;
import br.com.postech.java.tech.challenge.cortistyle.application.barbeiro.response.CadastrarBarbeiroResponse;
import br.com.postech.java.tech.challenge.cortistyle.domain.barbeiro.service.CadastrarBarbeiroService;
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

@Tag(name = "Barbeiros")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/barbeiros", produces = "application/json")
public class BarbeiroController {

    private final CadastrarBarbeiroService cadastrarBarbeiroService;

    @Operation(summary = "Cadastrar novo barbeiro")
    @PostMapping
    public ResponseEntity<CadastrarBarbeiroResponse> cadastrar(@RequestBody @Valid CadastrarBarbeiroRequest request) {
        log.info("Cadastrando novo barbeiro para a filial de id: {}", request.getFilialId());
        CadastrarBarbeiroResponse response = cadastrarBarbeiroService.cadastrar(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
