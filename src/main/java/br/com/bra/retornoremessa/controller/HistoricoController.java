package br.com.bra.retornoremessa.controller;

import br.com.bra.retornoremessa.entity.Boleto;
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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Historico salvar(@RequestBody Historico historico) {
        return historicoService.salvar(historico);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String deleta(@PathVariable(value = "id") Long id) throws Exception {
        return historicoService.delete(id);
    }

    @PatchMapping("/{id}/status/{status}")
    @ResponseStatus(HttpStatus.PARTIAL_CONTENT)
    public Historico alteraStatus(@PathVariable(value = "id") Long id,
                                   @PathVariable(value = "status") String status) throws Exception {
        return historicoService.alteraStatus(id, status);
    }
}