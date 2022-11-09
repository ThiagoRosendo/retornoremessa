package br.com.bra.retornoremessa.controller;

import br.com.bra.retornoremessa.entity.Pagamento;
import br.com.bra.retornoremessa.service.PagamentoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/pagamento")
public class PagamentoController {
    private final PagamentoService pagamentoService;

    public PagamentoController(PagamentoService pagamentoService) {
        this.pagamentoService = pagamentoService;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Pagamento buscaPorId(@PathVariable(value = "id") Long id) throws Exception {
        return pagamentoService.buscaPorId(id);
    }
}