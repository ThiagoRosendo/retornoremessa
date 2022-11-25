package br.com.bra.retornoremessa.controller;

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

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Pagamento buscaPorId(@PathVariable(value = "id") Long id) throws Exception {
        return pagamentoService.buscaPorId(id);
    }

    // CREATE PAGAMENTO
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pagamento salvar(@RequestBody Pagamento pagamento) {
        return pagamentoService.salvar(pagamento);
    }


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Pagamento> consultaTodos() {
        return pagamentoService.buscaTodos();
    }

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