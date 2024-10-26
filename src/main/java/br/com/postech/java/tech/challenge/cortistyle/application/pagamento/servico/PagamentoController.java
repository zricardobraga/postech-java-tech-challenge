package br.com.postech.java.tech.challenge.cortistyle.application.pagamento.servico;

import br.com.postech.java.tech.challenge.cortistyle.application.pagamento.servico.request.PagamentoBarbeiroRequest;
import br.com.postech.java.tech.challenge.cortistyle.application.pagamento.servico.response.PagamentoBarbeiroResponse;
import br.com.postech.java.tech.challenge.cortistyle.domain.pagamento.servico.service.ListaPagamentoBarbeiroService;
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

import java.util.ArrayList;

@Tag(name = "Pagamentos")
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/pagamentos", produces = "application/json")
public class PagamentoController {

    private final ListaPagamentoBarbeiroService listaPagamentoBarbeiroService;

    @Operation(summary = "Lista status pagamentos servico barbeiro")
    @PostMapping("/barbeiros")
    public ResponseEntity<Void> confirmarServicoRealizado(@RequestBody @Valid PagamentoBarbeiroRequest request) {
        log.info("Lista status pagamentos para serviço do barbeiro de id: {}", request.getBarbeiroId());
        ArrayList<PagamentoBarbeiroResponse> response = listaPagamentoBarbeiroService.listar(request);
        return new ResponseEntity(response, HttpStatus.OK);
    }
}
