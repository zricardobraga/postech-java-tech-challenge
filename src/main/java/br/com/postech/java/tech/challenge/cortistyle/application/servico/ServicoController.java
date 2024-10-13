package br.com.postech.java.tech.challenge.cortistyle.application.servico;

import br.com.postech.java.tech.challenge.cortistyle.application.servico.request.CadastrarServicoRequest;
import br.com.postech.java.tech.challenge.cortistyle.application.servico.response.CadastrarServicoResponse;
import br.com.postech.java.tech.challenge.cortistyle.domain.servico.service.CadastrarServicoService;
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

@Tag(name = "Servicos")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/servicos", produces = "application/json")
public class ServicoController {

    private final CadastrarServicoService cadastrarServicoService;

    @PostMapping
    @Operation(summary = "Cadastrar novo serviço")
    public ResponseEntity<CadastrarServicoResponse> cadastrar(@RequestBody @Valid CadastrarServicoRequest request) {
        log.info("Cadastrando novo servico: {}", request.getDescricao());
        CadastrarServicoResponse response = cadastrarServicoService.cadastrar(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}
