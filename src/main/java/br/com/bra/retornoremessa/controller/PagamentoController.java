package br.com.bra.retornoremessa.controller;

import br.com.bra.retornoremessa.entity.Boleto;
import br.com.bra.retornoremessa.entity.Pagamento;
import br.com.bra.retornoremessa.service.PagamentoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/pagamento")
public class PagamentoController {
    private final PagamentoService pagamentoService;

    public PagamentoController(PagamentoService pagamentoService) {
        this.pagamentoService = pagamentoService;
    }

    // CREATE PAGAMENTO
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pagamento salvar(@RequestBody Pagamento pagamento) {
        return pagamentoService.salvar(pagamento);
    }

    // READ PAGAMENTO
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Pagamento getPagamento(@PathVariable(value = "id") Long id) throws Exception {
        return pagamentoService.getPagamento(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Pagamento> getAllPagamentos() {
        return pagamentoService.getAllPagamentos();
    }

    // UPDATE PAGAMENTO

    @PatchMapping("/{id}/valor/{valor}")
    @ResponseStatus(HttpStatus.PARTIAL_CONTENT)
    public Pagamento alterarValor(@PathVariable(value = "id") Long id,
                                    @PathVariable(value = "valor") String valor) throws Exception {
        return pagamentoService.alterarValorPgto(id, valor);
    }

    // DELETE PAGAMENTO
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String delete(@PathVariable(value = "id") Long id) throws Exception {
        return pagamentoService.delete(id);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String deleteAll() throws Exception {
        return pagamentoService.deleteAll();
    }
}