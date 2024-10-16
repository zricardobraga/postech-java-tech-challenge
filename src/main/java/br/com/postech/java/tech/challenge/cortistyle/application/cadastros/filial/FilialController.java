package br.com.postech.java.tech.challenge.cortistyle.application.cadastros.filial;

import br.com.postech.java.tech.challenge.cortistyle.application.cadastros.filial.request.CadastrarFilialRequest;
import br.com.postech.java.tech.challenge.cortistyle.application.cadastros.filial.request.IncluirBarbeiroFilialRequest;
import br.com.postech.java.tech.challenge.cortistyle.application.cadastros.filial.response.BarbeiroFilialResponse;
import br.com.postech.java.tech.challenge.cortistyle.application.cadastros.filial.response.CadastrarFilialResponse;
import br.com.postech.java.tech.challenge.cortistyle.domain.cadastros.filial.service.CadastrarFilialService;
import br.com.postech.java.tech.challenge.cortistyle.domain.cadastros.filial.service.IncluirBarbeiroFilialService;
import br.com.postech.java.tech.challenge.cortistyle.domain.cadastros.filial.service.ListarBarbeirosFilialService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Filiais")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/filiais", produces = "application/json")
public class FilialController {

    private final CadastrarFilialService cadastrarFilialService;
    private final IncluirBarbeiroFilialService incluirBarbeiroFilialService;
    private final ListarBarbeirosFilialService listarBarbeirosFilialService;

    @PostMapping
    @Operation(summary = "Cadastrar nova filial")
    public ResponseEntity<CadastrarFilialResponse> cadastrar(@RequestBody @Valid CadastrarFilialRequest request) {
        log.info("Cadastrando nova filial pelo gestor: {}", "nomeGestor");
        CadastrarFilialResponse response = cadastrarFilialService.cadastrar(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/barbeiros")
    @Operation(summary = "Incluir barbeiro a uma filial")
    public ResponseEntity<List<BarbeiroFilialResponse>> incluirBarbeiro(@RequestBody @Valid
                                                                        IncluirBarbeiroFilialRequest request) {
        log.info("Incluindo barbeiro de id: {} a filial pelo gestor: {}", request.getBarbeiroId(),
                request.getFilialId());
        incluirBarbeiroFilialService.incluir(request);
        var response = listarBarbeirosFilialService.listarBarbeirosBy(request.getFilialId());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}/barbeiros")
    @Operation(summary = "Listar barbeiros de uma filial")
    public ResponseEntity<List<BarbeiroFilialResponse>> listarBarbeiros(@PathVariable Long id) {
        log.info("Listando barbeiros da filial de id: {}", id);
        List<BarbeiroFilialResponse> response = listarBarbeirosFilialService.listarBarbeirosBy(id);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
