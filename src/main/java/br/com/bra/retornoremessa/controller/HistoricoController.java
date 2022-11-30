package br.com.bra.retornoremessa.controller;

import br.com.bra.retornoremessa.entity.Beneficiario;
import br.com.bra.retornoremessa.entity.Historico;
import br.com.bra.retornoremessa.service.HistoricoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/historico")
public class HistoricoController {
    private final HistoricoService historicoService;

    public HistoricoController(HistoricoService historicoService) {
        this.historicoService = historicoService;
    }

    // CREATE HISTORICO
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Historico salvar(@RequestBody Historico historico) {
        return historicoService.salvar(historico);
    }

    // READ HISTORICO
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Historico getBoleto(@PathVariable(value = "id") Long id) throws Exception {
        return historicoService.getHistorico(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Historico> getAllHistoricos() throws Exception {
        return historicoService.getAllHistoricos();
    }

    // UPDATE HISTORICO
    @PatchMapping("/{id}/status/{status}")
    @ResponseStatus(HttpStatus.PARTIAL_CONTENT)
    public Historico alteraStatus(@PathVariable(value = "id") Long id,
                                  @PathVariable(value = "status") String status) throws Exception {
        return historicoService.alteraStatus(id, status);
    }


    // DELETE HISTORICO
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String delete(@PathVariable(value = "id") Long id) throws Exception {
        return historicoService.delete(id);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String deleteAll() throws Exception {
        return historicoService.deleteAll();
    }
}