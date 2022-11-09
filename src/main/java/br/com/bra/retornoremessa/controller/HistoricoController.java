package br.com.bra.retornoremessa.controller;

import br.com.bra.retornoremessa.entity.Historico;
import br.com.bra.retornoremessa.service.HistoricoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/historico")
public class HistoricoController {
    private final HistoricoService historicoService;

    public HistoricoController(HistoricoService historicoService) {
        this.historicoService = historicoService;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Historico buscaPorId(@PathVariable(value = "id") Long id) throws Exception {
        return historicoService.buscaPorId(id);
    }
}