package br.com.bra.retornoremessa.controller;

import br.com.bra.retornoremessa.entity.Beneficiario;
import br.com.bra.retornoremessa.entity.Pagador;
import br.com.bra.retornoremessa.service.PagadorService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/pagador")
public class PagadorController {
    private final PagadorService pagadorService;

    public PagadorController(PagadorService pagadorService) {
        this.pagadorService = pagadorService;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Pagador buscaPorId(@PathVariable(value = "id") Long id) throws Exception {
        return pagadorService.buscaPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pagador salvar(@RequestBody Pagador pagador) {
        return pagadorService.salvar(pagador);
    }

}