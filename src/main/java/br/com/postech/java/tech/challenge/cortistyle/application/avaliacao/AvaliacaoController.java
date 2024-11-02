package br.com.postech.java.tech.challenge.cortistyle.application.avaliacao;

import br.com.postech.java.tech.challenge.cortistyle.application.avaliacao.cliente.request.CadastrarAvaliacaoRequest;
import br.com.postech.java.tech.challenge.cortistyle.domain.avaliacao.service.AvaliacaoService;
import br.com.postech.java.tech.challenge.cortistyle.infrastructure.exceptions.PolicyException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Avaliacoes")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/avaliacoes", produces = "application/json")
public class AvaliacaoController {

    private final AvaliacaoService avaliacaoService;

    @Operation(summary = "Cadastrar avaliacao do servico")
    @ResponseBody
    @PostMapping
    public ResponseEntity<String> cadastrarAvaliacao(@RequestBody @Valid CadastrarAvaliacaoRequest request) {
        try {
            avaliacaoService.avaliar(request);
            return ResponseEntity.status(HttpStatus.CREATED).body("Avaliação cadastrada com sucesso.");
        } catch (PolicyException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao cadastrar avaliação.");
        }
    }
}
