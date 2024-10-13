package br.com.postech.java.tech.challenge.cortistyle.application.filial;

import br.com.postech.java.tech.challenge.cortistyle.application.filial.request.CadastrarFilialRequest;
import br.com.postech.java.tech.challenge.cortistyle.application.filial.response.CadastrarFilialResponse;
import br.com.postech.java.tech.challenge.cortistyle.domain.filial.service.CadastrarFilialService;
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

@Tag(name = "Filiais")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/filiais", produces = "application/json")
public class FilialController {

    private final CadastrarFilialService cadastrarFilialService;

    @PostMapping
    @Operation(summary = "Cadastrar nova filial")
    public ResponseEntity<CadastrarFilialResponse> cadastrar(@RequestBody @Valid CadastrarFilialRequest request) {
        log.info("Cadastrando nova filial pelo gestor: {}", "nomeGestor");
        CadastrarFilialResponse response = cadastrarFilialService.cadastrar(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    //TODO: criar serviço incluir barbeiro a filial.
    //TODO: lista barbeiros filial
}
